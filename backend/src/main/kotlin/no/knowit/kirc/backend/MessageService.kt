package no.knowit.kirc.backend

import no.knowit.kirc.api.Message
import no.knowit.kirc.backend.data.MessageEntity
import no.knowit.kirc.backend.data.MessageRepository
import org.springframework.stereotype.Service

@Service
class MessageService(val messageRepository: MessageRepository) {

    fun getLatestMessages(): List<Message> {
        return messageRepository.findAll()
                .map { it.toDTO() }
                .sortedBy { it.timestamp }
    }

}