package hello.tis.repository

import hello.tis.domain.PendingNotification
import org.springframework.data.jpa.repository.JpaRepository

interface PendingNotificationRepository : JpaRepository<PendingNotification, Long>
