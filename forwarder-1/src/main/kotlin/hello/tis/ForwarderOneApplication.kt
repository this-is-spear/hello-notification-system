package hello.tis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ForwarderOneApplication

fun main(args: Array<String>) {
    runApplication<ForwarderOneApplication>(*args)
}