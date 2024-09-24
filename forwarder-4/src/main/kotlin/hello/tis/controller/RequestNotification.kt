package hello.tis.controller

data class RequestNotification(
    val channel: String,
    val channelKey: String,
    val title: String,
    val content: String,
)
