package hello.tis.hello.tis

import hello.tis.SendClient
import hello.tis.SenderServerProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class RestClientConfiguration {
    @Bean
    fun sendClient(
        senderServerProperties: SenderServerProperties,
    ): SendClient {
        val restClient = RestClient.builder()
            .baseUrl(senderServerProperties.baseUrl)
            .build()
        val restClientAdapter = RestClientAdapter.create(restClient)
        val factory = HttpServiceProxyFactory.builderFor(restClientAdapter).build()
        return factory.createClient(SendClient::class.java)
    }
}
