package hello.tis.domain

import hello.tis.infra.SendResponse

class SendingNotification(
    val channel: String,
    val channelKey: String,
    val title: String,
    val content: String,
    val sendResult: SendResult,
)
