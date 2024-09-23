package hello.tis.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler


@Configuration
@EnableScheduling
class SchedulingConfiguration {

    @Bean
    fun sendScheduler1(): ThreadPoolTaskScheduler =
        ThreadPoolTaskScheduler().apply {
            poolSize = 1
            initialize()
        }

    @Bean
    fun sendScheduler2(): ThreadPoolTaskScheduler =
        ThreadPoolTaskScheduler().apply {
            poolSize = 1
            initialize()
        }
}
