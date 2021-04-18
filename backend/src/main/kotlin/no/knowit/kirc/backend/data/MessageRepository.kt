package no.knowit.kirc.backend.data

import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository

@EnableScan
interface MessageRepository: CrudRepository<MessageEntity, String>