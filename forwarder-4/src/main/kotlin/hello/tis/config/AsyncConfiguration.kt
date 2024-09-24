package hello.tis.config

import java.util.concurrent.Executor
import java.util.concurrent.ThreadPoolExecutor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor


@EnableAsync
@Configuration
class AsyncConfiguration {
    @Bean
    fun asyncSendExecutor(): Executor {
        return ThreadPoolTaskExecutor().apply {
            corePoolSize = 300
            queueCapacity = 200
            setRejectedExecutionHandler(ThreadPoolExecutor.CallerRunsPolicy())
            setWaitForTasksToCompleteOnShutdown(true)
            setAwaitTerminationSeconds(60)
            setThreadNamePrefix("sender")
            initialize()
        }
    }
}
