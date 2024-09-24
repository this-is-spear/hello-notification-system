package hello.tis.scheduler

import hello.tis.property.ChunkSizeProperties
import hello.tis.service.AsynchronousService
import hello.tis.service.SendService
import hello.tis.service.StandbyService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class SendScheduler(
    private val standbyService: StandbyService,
    private val asynchronousService: AsynchronousService,
    private val sendService: SendService,
    private val chunkSizeProperties: ChunkSizeProperties,
) {
    @Scheduled(cron = "* * * * * *")
    fun send() {
        val pendingNotifications = standbyService.popPendingNotifications(chunkSizeProperties.sendSize)
        for (pendingNotification in pendingNotifications) {
            asynchronousService.async {
                sendService.send(pendingNotification)
            }
        }
    }
}
