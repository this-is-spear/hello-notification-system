package hello.tis.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "chunk-size")
data class ChunkSizeProperties(
    val sendSize: Int = 100,
    val failbackSize: Int = 100,
)
