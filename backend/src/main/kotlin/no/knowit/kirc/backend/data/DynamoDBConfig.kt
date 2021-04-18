package no.knowit.kirc.backend.data

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableDynamoDBRepositories(basePackages = ["no.knowit.kirc.backend.data"])
class DynamoDBConfig {

    @Value("\${amazon.aws.accesskey}")
    lateinit var accesskey: String

    @Value("\${amazon.aws.secretkey}")
    lateinit var secretkey: String

    @Value("\${amazon.dynamodb.endpoint}")
    lateinit var endpoint: String

    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB {
        val amazonDynamoDB = AmazonDynamoDBClient(amazonCredentials())
        amazonDynamoDB.setEndpoint(endpoint)
        return amazonDynamoDB
    }

    @Bean
    fun amazonCredentials(): AWSCredentials {
        return BasicAWSCredentials(accesskey, secretkey)
    }
}