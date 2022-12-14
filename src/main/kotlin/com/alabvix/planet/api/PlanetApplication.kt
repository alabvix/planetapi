package com.alabvix.planet.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class PlanetApplication

fun main(args: Array<String>) {
	runApplication<PlanetApplication>(*args)
}
