package hello.tis.service

import hello.tis.controller.RequestNotification
import hello.tis.domain.PendingNotification
import hello.tis.repository.PendingNotificationRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class StandbyService(
    private val pendingNotificationRepository: PendingNotificationRepository,
) {
    @Transactional
    fun pushPendingNotification(requestNotification: RequestNotification) {
        val pendingNotification = PendingNotification(
            requestNotification.channel,
            requestNotification.channelKey,
            requestNotification.title,
            requestNotification.content,
        )
        pendingNotificationRepository.save(pendingNotification)
    }

    @Transactional
    fun popPendingNotifications(chunkSize: Int): List<PendingNotification> {
        val pageRequest = PageRequest.ofSize(chunkSize)
        val pendingNotifications = pendingNotificationRepository.findAll(pageRequest).toList()
        pendingNotificationRepository.deleteAll(pendingNotifications)
        return pendingNotifications
    }
}
