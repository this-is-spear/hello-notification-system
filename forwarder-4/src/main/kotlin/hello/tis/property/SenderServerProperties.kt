package hello.tis.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "sender")
class SenderServerProperties(
    val baseUrl: String,
)
