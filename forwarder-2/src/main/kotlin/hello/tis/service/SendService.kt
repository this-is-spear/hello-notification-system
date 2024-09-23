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
import org.springframework.stereotype.Service

@Service
class SendService(
    private val completedNotificationRepository: CompletedNotificationRepository,
    private val failedNotificationRepository: FailedNotificationRepository,
    private val sendClient: SendClient,
) {
    fun send(pendingNotification: PendingNotification) {
        val sendingNotification = try {
            val response = sendClient.send(
                pendingNotification.channel,
                pendingNotification.channelKey,
                pendingNotification.title,
                pendingNotification.content
            )

            SendingNotification(
                pendingNotification.channel,
                pendingNotification.channelKey,
                pendingNotification.title,
                pendingNotification.content,
                if (response == SendResponse.RESPONSE_SUCCESS) SendResult.SUCCESS else SendResult.FAIL,
            )
        } catch (e: Exception) {
            SendingNotification(
                pendingNotification.channel,
                pendingNotification.channelKey,
                pendingNotification.title,
                pendingNotification.content,
                SendResult.ERROR,
            )
        }

        when (sendingNotification.sendResult) {
            SendResult.SUCCESS, SendResult.FAIL -> completedNotificationRepository.save(
                CompletedNotification(
                    sendingNotification.channel,
                    sendingNotification.channelKey,
                    sendingNotification.title,
                    sendingNotification.content,
                    sendingNotification.sendResult,
                )
            )

            SendResult.ERROR -> failedNotificationRepository.save(
                FailedNotification(
                    sendingNotification.channel,
                    sendingNotification.channelKey,
                    sendingNotification.title,
                    sendingNotification.content,
                )
            )
        }
    }
}
