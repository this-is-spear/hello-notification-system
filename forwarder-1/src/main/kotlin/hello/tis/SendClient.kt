package hello.tis

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.PostExchange

interface SendClient {
    @PostExchange("/send/{channel}")
    fun send(@PathVariable channel: String, @RequestParam channelKey: String, @RequestParam title: String, @RequestParam content: String)
}
