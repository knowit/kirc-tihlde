package no.knowit.kirc.backend.kircbackend

import no.knowit.kirc.api.Message
import org.springframework.stereotype.Service

@Service
class MessageService(val messageRepository: MessageRepository) {

    fun getLatestMessages(): List<Message> {
        return messageRepository.findAll()
                .map { it.toDTO() }
                .sortedBy { it.timestamp }
    }

    fun createNewTestMessages() {
        messageRepository.deleteAll()
        (1..100).map { TestData.createMessage() }
                .map { MessageEntity.fromDTO(it) }
                .let { messageRepository.saveAll(it) }
    }
}