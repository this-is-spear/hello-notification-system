package hello.tis

import java.util.Random
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SenderController {
    @PostMapping("/send/{channel}")
    fun send(
        @PathVariable
        channel: String,
        channelKey: String,
        title: String,
        content: String,
    ) {
        val pickedLatency = Random().nextInt(3) + 4
        Thread.sleep(pickedLatency * 1000L)

        val pickedNumber = Random().nextInt(10) + 1
        if (pickedNumber > 9) {
            throw IllegalStateException("Failed to send message")
        }

        if (pickedNumber > 8) {
            throw IllegalArgumentException("Invalid message")
        }
    }
}
