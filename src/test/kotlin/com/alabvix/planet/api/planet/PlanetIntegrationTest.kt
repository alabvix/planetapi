package com.alabvix.planet.api.planet

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlanetIntegrationTest {

    @LocalServerPort
    private val port = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `Given no input should list all planets`() {

        var response =
            this.restTemplate.getForEntity("http://localhost:$port/planet", String::class.java)

        assertEquals(response.statusCode, HttpStatus.OK)
        val body:String = response.body.toString()
        assertTrue(body.contains("Earth"))
        assertTrue(body.contains("3930f3f2-8b36-11ec-a8a3-0242ac120002"))
        assertTrue(body.contains("Mars"))
        assertTrue(body.contains("5ad40044-8b36-11ec-a8a3-0242ac120002"))

    }
}