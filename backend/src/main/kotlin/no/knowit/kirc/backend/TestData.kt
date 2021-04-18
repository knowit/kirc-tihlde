package no.knowit.kirc.backend

import com.thedeanda.lorem.Lorem
import com.thedeanda.lorem.LoremIpsum
import no.knowit.kirc.api.Message
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.UUID
import kotlin.random.Random


object TestData {
    var lorem: Lorem = LoremIpsum.getInstance()

    fun createMessage(): Message {
        return Message(
                id = UUID.randomUUID(),
                timestamp = ZonedDateTime.now(),
                message = lorem.getWords(Random.nextInt(100))
        )
    }
}