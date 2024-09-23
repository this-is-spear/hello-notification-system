package hello.tis.domain

class SendingNotification(
    val channel: String,
    val channelKey: String,
    val title: String,
    val content: String,
    val sendResult: SendResult,
)
