package hello.tis.scheduler

import hello.tis.property.ChunkSizeProperties
import hello.tis.service.FailbackService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class FailbackScheduler(
    private val failbackService: FailbackService,
    private val chunkSizeProperties: ChunkSizeProperties,
) {
    @Scheduled(fixedDelay = 60000)
    fun failback() {
        failbackService.failback(chunkSizeProperties.failbackSize)
    }
}
