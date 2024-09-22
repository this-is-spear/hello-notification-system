package hello.tis

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ForwardService(
    private val sendClient: SendClient,
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun sendRequest(channel: String, channelKey: String, title: String, content: String) {
        sendClient.send(channel, channelKey, title, content)
    }
}
