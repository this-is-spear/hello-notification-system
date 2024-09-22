package hello.tis

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ForwardController(
    private val forwardService: ForwardService,
) {
    @PostMapping("/send/{channel}")
    fun forward(
        @PathVariable channel: String,
        channelKey: String,
        title: String,
        content: String,
    ) {
        forwardService.sendRequest(channel, channelKey, title, content)
    }
}
