package hello.tis.config

import hello.tis.infra.SendClient
import hello.tis.property.SenderServerProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory


@Configuration
class RestClientConfiguration {
    @Bean
    fun sendClient(
        senderServerProperties: SenderServerProperties,
    ): SendClient {
        val webClient = WebClient.builder()
            .baseUrl(senderServerProperties.baseUrl)
            .build()

        val webClientAdapter = WebClientAdapter.create(webClient)
        val factory = HttpServiceProxyFactory.builderFor(webClientAdapter).build()
        return factory.createClient(SendClient::class.java)
    }
}
