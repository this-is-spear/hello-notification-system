package hello.tis.repository

import hello.tis.domain.FailedNotification
import org.springframework.data.jpa.repository.JpaRepository

interface FailedNotificationRepository: JpaRepository<FailedNotification, Long>
