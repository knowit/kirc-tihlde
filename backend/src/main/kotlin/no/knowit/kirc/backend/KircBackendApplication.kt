package no.knowit.kirc.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans

@ComponentScan(basePackages = ["no.knowit.kirc"])
@SpringBootApplication
class KircBackendApplication

fun main(args: Array<String>) {
	runApplication<KircBackendApplication>(*args)
}
