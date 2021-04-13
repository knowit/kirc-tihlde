package no.knowit.kirc.backend.kircbackend

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.ArrayList
import java.util.Date


@Configuration
class MongoDbConfig {


    @Bean
    fun customConversions(): MongoCustomConversions {
        val converters: MutableList<Converter<*, *>> = ArrayList()
        converters.add(ZonedDateTimeReadConverter())
        converters.add(ZonedDateTimeFromDateReadConverter())
        return MongoCustomConversions(converters)
    }
}

class ZonedDateTimeReadConverter : Converter<String, ZonedDateTime> {
    var dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME

    override fun convert(date: String): ZonedDateTime {
        return ZonedDateTime.parse(date, dateTimeFormatter)
    }

}

class ZonedDateTimeFromDateReadConverter : Converter<Date, ZonedDateTime> {
    override fun convert(date: Date): ZonedDateTime {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC"))
    }

}