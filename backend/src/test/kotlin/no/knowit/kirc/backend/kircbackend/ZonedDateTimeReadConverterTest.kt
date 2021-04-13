package no.knowit.kirc.backend.kircbackend

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Month
import java.time.ZoneId

class ZonedDateTimeReadConverterTest {

    @Test
    fun convertsCorrectly() {
        val dateString = "2021-04-03T15:00:41.277200"

        val convertedDate = ZonedDateTimeReadConverter().convert(dateString)
        assertEquals(2021, convertedDate.year)
        assertEquals(Month.APRIL, convertedDate.month)
        assertEquals(3, convertedDate.dayOfMonth)
        assertEquals(15, convertedDate.hour)
        assertEquals(0, convertedDate.minute)
        assertEquals(41, convertedDate.second)
        assertEquals(ZoneId.of("UTC"), convertedDate.zone)
    }
}