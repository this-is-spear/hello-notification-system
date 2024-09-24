package hello.tis.controller

import hello.tis.service.StandbyService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ForwardController(
    private val standbyService: StandbyService,
) {
    @PostMapping("/send/{channel}")
    fun forward(
        @PathVariable channel: String,
        channelKey: String,
        title: String,
        content: String,
    ) {
        standbyService.pushPendingNotification(RequestNotification(channel, channelKey, title, content))
    }
}
