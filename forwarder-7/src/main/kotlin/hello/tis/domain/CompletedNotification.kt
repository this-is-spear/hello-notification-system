package hello.tis.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class CompletedNotification(
    val channel: String,
    val channelKey: String,
    val title: String,
    val content: String,
    val sendResult: SendResult,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
)
