package no.knowit.kirc.backend

import no.knowit.kirc.backend.data.MessageRepository
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MessageRepositoryTest {
    val log: Logger = LoggerFactory.getLogger(MessageRepositoryTest::class.java)

    @Autowired
    private lateinit var messageRepository: MessageRepository

    @Test
    internal fun `fetch documents`() {
        val allDocuments = messageRepository.findAll()
        assertNotNull(allDocuments)
        allDocuments.forEach { me ->
            log.info("${me.timestamp} [${me.nickname}]: ${me.message}")
        }
    }
}