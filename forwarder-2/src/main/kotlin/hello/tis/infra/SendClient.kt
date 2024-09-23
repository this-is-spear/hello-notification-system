package hello.tis.infra

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.PostExchange

interface SendClient {
    @PostExchange(value = "/send/{channel}", accept = [MediaType.APPLICATION_JSON_VALUE])
    fun send(
        @PathVariable channel: String,
        @RequestParam channelKey: String,
        @RequestParam title: String,
        @RequestParam content: String,
    ): SendResponse
}
