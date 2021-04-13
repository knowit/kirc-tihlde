package no.knowit.kirc.backend.kircbackend

import no.knowit.kirc.api.Message
import no.knowit.kirc.api.MessagesApiService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZonedDateTime

@Service
class MessageController(val messageService: MessageService): MessagesApiService {

    override fun getMessages(): List<Message> {
        return messageService.getLatestMessages()
    }

//    override fun getMessages(): List<Message> {
//        return (0..100).map { TestData.createMessage() }
//    }
}