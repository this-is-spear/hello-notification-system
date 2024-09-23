package hello.tis.repository

import hello.tis.domain.CompletedNotification
import org.springframework.data.jpa.repository.JpaRepository

interface CompletedNotificationRepository: JpaRepository<CompletedNotification, Long>

