package hello.tis.service

import hello.tis.domain.PendingNotification
import hello.tis.repository.FailedNotificationRepository
import hello.tis.repository.PendingNotificationRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class FailbackService(
    private val failedNotificationRepository: FailedNotificationRepository,
    private val pendingNotificationRepository: PendingNotificationRepository,
) {
    fun failback(chunkSize: Int) {
        val pageRequest = PageRequest.ofSize(chunkSize)
        val failedNotifications = failedNotificationRepository.findAll(pageRequest).toList()
        val pendingNotifications = failedNotifications.map {
            PendingNotification(it.channel, it.channelKey, it.title, it.content)
        }

        failedNotificationRepository.deleteAll(failedNotifications)
        pendingNotificationRepository.saveAll(pendingNotifications)
    }
}
