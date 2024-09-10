package hello.tis

data class SendRequest(
    val channel: String,
    val message: String,
)
