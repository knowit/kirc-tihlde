package no.knowit.kirc.backend.kircbackend

import no.knowit.kirc.api.Message
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.nio.charset.Charset
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.UUID

@Document("Message")
data class MessageEntity(
        @Id
        val id: String,
        val timestamp: ZonedDateTime,
        val message: String,
        val nickname: String? = null,
        val style: String? = null
) {
    fun toDTO(): Message {
        return Message(
                id = UUID.nameUUIDFromBytes(id.toByteArray(Charset.defaultCharset())),
                timestamp = timestamp,
                message = message,
                nickname = nickname,
                style = style
        )
    }

    companion object {
        fun fromDTO(source: Message): MessageEntity {
            return MessageEntity(
                    id = source.id.toString(),
                    timestamp = source.timestamp,
                    message = source.message,
                    nickname = source.nickname,
                    style = source.style
            )
        }
    }
}