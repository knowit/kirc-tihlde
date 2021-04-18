package no.knowit.kirc.backend.data

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import no.knowit.kirc.api.Message
import java.nio.charset.Charset
import java.time.ZonedDateTime
import java.time.ZonedDateTime.now
import java.util.UUID

@DynamoDBTable(tableName = "kirc")
data class MessageEntity(
        @DynamoDBHashKey
        var id: String? = null,
        @DynamoDBAttribute
        var timestamp: String? = null,
        @DynamoDBAttribute
        var message: String = "",
        @DynamoDBAttribute
        var nickname: String? = null,
        @DynamoDBAttribute
        var style: String? = null,
) {
    fun toDTO(): Message {
        return Message(
                id = UUID.nameUUIDFromBytes(id?.toByteArray(Charset.defaultCharset())),
                timestamp = ZonedDateTime.parse(timestamp),
                message = message,
                nickname = nickname,
                style = style
        )
    }
}