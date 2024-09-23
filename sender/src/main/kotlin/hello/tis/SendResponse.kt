package hello.tis

data class SendResponse(
    val resultCode: String,
) {
    companion object {
        val RESPONSE_SUCCESS = SendResponse("SUCCESS")
        val RESPONSE_FAIL = SendResponse("FAIL")
    }
}
