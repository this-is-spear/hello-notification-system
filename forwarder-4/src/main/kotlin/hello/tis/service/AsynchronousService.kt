package hello.tis.service

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class AsynchronousService {
    @Async
    fun async(function: () -> Unit) {
        function()
    }
}
