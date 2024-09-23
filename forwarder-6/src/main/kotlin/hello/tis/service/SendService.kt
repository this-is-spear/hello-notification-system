package hello.tis.service

import hello.tis.domain.CompletedNotification
import hello.tis.domain.FailedNotification
import hello.tis.domain.PendingNotification
import hello.tis.domain.SendResult
import hello.tis.domain.SendingNotification
import hello.tis.infra.SendClient
import hello.tis.infra.SendResponse
import hello.tis.repository.CompletedNotificationRepository
import hello.tis.repository.FailedNotificationRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class SendService(
    private val completedNotificationRepository: CompletedNotificationRepository,
    private val failedNotificationRepository: FailedNotificationRepository,
    private val sendClient: SendClient,
) {
    fun send(pendingNotifications: List<PendingNotification>) =
        Flux.fromIterable(pendingNotifications)
            .flatMap { notification ->
                sendClient
                    .send(
                        notification.channel,
                        notification.channelKey,
                        notification.title,
                        notification.content
                    ).map {
                        SendingNotification(
                            notification.channel,
                            notification.channelKey,
                            notification.title,
                            notification.content,
                            if (it == SendResponse.RESPONSE_SUCCESS) SendResult.SUCCESS else SendResult.FAIL
                        )
                    }.onErrorReturn(
                        SendingNotification(
                            notification.channel,
                            notification.channelKey,
                            notification.title,
                            notification.content,
                            SendResult.ERROR
                        )
                    )
            }
            .map { notification ->
                when (notification.sendResult) {
                    SendResult.SUCCESS, SendResult.FAIL ->
                        completedNotificationRepository.save(
                            CompletedNotification(
                                notification.channel,
                                notification.channelKey,
                                notification.title,
                                notification.content,
                                notification.sendResult
                            )
                        )

                    SendResult.ERROR ->
                        failedNotificationRepository.save(
                            FailedNotification(
                                notification.channel,
                                notification.channelKey,
                                notification.title,
                                notification.content
                            )
                        )
                }
            }
            .subscribe()
}
