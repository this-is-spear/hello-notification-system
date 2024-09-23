package hello.tis.scheduler

import hello.tis.property.ChunkSizeProperties
import hello.tis.service.SendService
import hello.tis.service.StandbyService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class SendScheduler(
    private val standbyService: StandbyService,
    private val sendService: SendService,
    private val chunkSizeProperties: ChunkSizeProperties,
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Scheduled(cron = "* * * * * *", scheduler = "sendScheduler1")
    fun send1() {
        log.info("Sending notifications1")
        val pendingNotifications = pop()
        sendService.send(pendingNotifications)
    }

    @Scheduled(cron = "* * * * * *", scheduler = "sendScheduler2")
    fun send2() {
        log.info("Sending notifications2")
        val pendingNotifications = pop()
        sendService.send(pendingNotifications)
    }

    private fun pop() = synchronized(this) {
        standbyService.popPendingNotifications(chunkSizeProperties.sendSize)
    }
}
