package no.knowit.kirc.backend.kircbackend

import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDateTime

interface MessageRepository : MongoRepository<MessageEntity, String> {

    fun findAllByTimestampBefore(timestamp: LocalDateTime): List<MessageEntity>
}