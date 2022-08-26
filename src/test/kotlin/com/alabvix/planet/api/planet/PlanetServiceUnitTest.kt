package com.alabvix.planet.api.planet

import com.alabvix.planet.api.planet.payload.CalculateDistanceInputPayload
import com.alabvix.planet.api.planet.payload.PlanetInputPayload
import com.alabvix.planet.api.planet.payload.PlanetOutputPayload
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import java.util.*

class PlanetServiceUnitTest {

    private val planetRepository = Mockito.mock(PlanetRepository::class.java)

    private val planetConverter = Mockito.mock(PlanetConverter::class.java)

    private val planetService = PlanetService(planetRepository, planetConverter)

    @Test
    fun `GetPlanets - Should list all planets`() {

        // Given mocked planets
        val planets = listOf(
            PlanetEntity(UUID.randomUUID(),"Earth", 100.00, 90.00, 303.00),
            PlanetEntity(UUID.randomUUID(),"Mars", 345.00, 260.00, 23.00),
        )
        Mockito.`when` (planetRepository.findAll()).thenReturn(planets)

        // When call the service
        val planetsOutput = planetService.getPlanets()

        // Then should list all planets
        Assertions.assertEquals(2, planetsOutput.size)

        Assertions.assertEquals("Earth", planetsOutput[0].name)
        Assertions.assertEquals(100.00, planetsOutput[0].x)
        Assertions.assertEquals(90.00, planetsOutput[0].y)
        Assertions.assertEquals(303.00, planetsOutput[0].z)

        Assertions.assertEquals("Mars", planetsOutput[1].name)
        Assertions.assertEquals(345.00, planetsOutput[1].x)
        Assertions.assertEquals(260.00, planetsOutput[1].y)
        Assertions.assertEquals(23.00, planetsOutput[1].z)

        Mockito.verify(planetRepository, Mockito.times(1)).findAll()
    }

    @Test
    fun `SavePlanets - Given a valid Planet with all fields filled, should create a Planet`() {

        // given
        val planetId = UUID.randomUUID()
        val planetInput = PlanetInputPayload("Jupiter", 100.00, 250.00, 300.00)
        val planetEntity = PlanetEntity(planetId,"Jupiter", 100.00, 250.00, 300.00)
        val planetOutput = PlanetOutputPayload(planetId,"Jupiter", 100.00, 250.00, 300.00)

        Mockito.`when` (planetConverter.toEntity(planetInput)).thenReturn(planetEntity)
        Mockito.`when` (planetConverter.toOutputPayload(planetEntity)).thenReturn(planetOutput)
        Mockito.`when` (planetRepository.save(planetEntity)).thenReturn(planetEntity)

        // when
        val planetOutput2 = planetService.savePlanet(planetInput)

        // then
        Assertions.assertNotNull(planetOutput2.id)
        Assertions.assertEquals(planetId, planetOutput2.id)
        Assertions.assertEquals(planetInput.name, planetOutput2.name)
        Assertions.assertEquals(planetInput.x, planetOutput2.x)
        Assertions.assertEquals(planetInput.y, planetOutput2.y)
        Assertions.assertEquals(planetInput.z, planetOutput2.z)

        Mockito.verify(planetConverter, times(1)).toEntity(planetInput)
        Mockito.verify(planetConverter, times(1)).toOutputPayload(planetEntity)
        Mockito.verify(planetRepository, times(1)).save(planetEntity)
    }

    @Test
    fun `CalculateDistance - Given a not found planet for first planet should throw a Planet not found exception`() {

        // given
        val planet1Id = UUID.randomUUID()
        val planet2Id = UUID.randomUUID()
        val payload = CalculateDistanceInputPayload(planet1Id.toString(), planet2Id.toString())

        Mockito.`when` (planetRepository.findById(planet1Id)).thenReturn(Optional.empty())

        val exception = Assertions.assertThrows(PlanetNotFoundException::class.java) {
            planetService.calculateDistance(payload)
        }

        Assertions.assertEquals("Planet not found with id: $planet1Id", exception.message)

        Mockito.verify(planetRepository, times(1)).findById(planet1Id)
        Mockito.verify(planetRepository, times(0)).findById(planet2Id)
    }

    @Test
    fun `calculateDistance - Given a not found planet for second planet should throw a Planet not found exception`() {

        // given
        val planet1Id = UUID.randomUUID()
        val planet2Id = UUID.randomUUID()
        val payload = CalculateDistanceInputPayload(planet1Id.toString(), planet2Id.toString())
        val planetA = PlanetEntity(planet1Id,"Jupiter", 1.00, 2.00, 3.00)

        Mockito.`when` (planetRepository.findById(planet1Id)).thenReturn(Optional.of(planetA))
        Mockito.`when` (planetRepository.findById(planet2Id)).thenReturn(Optional.empty())

        // when
        val exception = Assertions.assertThrows(PlanetNotFoundException::class.java) {
            planetService.calculateDistance(payload)
        }

        // then
        Assertions.assertEquals("Planet not found with id: $planet2Id", exception.message)

        Mockito.verify(planetRepository, times(1)).findById(planet1Id)
        Mockito.verify(planetRepository, times(1)).findById(planet2Id)
    }

    @Test
    fun `CalculateDistance - Given two planets id's should calculate the distance between then`() {

        // given
        val planet1Id = UUID.randomUUID()
        val planet2Id = UUID.randomUUID()
        val planetA = PlanetEntity(planet1Id,"Jupiter", 1.00, 2.00, 3.00)
        val planetB = PlanetEntity(planet2Id,"Jupiter", -4.00, -5.00, -6.00)
        val input = CalculateDistanceInputPayload(planet1Id.toString(), planet2Id.toString())

        Mockito.`when` (planetRepository.findById(planet1Id)).thenReturn(Optional.of(planetA))
        Mockito.`when` (planetRepository.findById(planet2Id)).thenReturn(Optional.of(planetB))

        // when
        val output = planetService.calculateDistance(input)

        // then
        Assertions.assertEquals(12.45, output.distance)
        Mockito.verify(planetRepository, times(1)).findById(planet1Id)
        Mockito.verify(planetRepository, times(1)).findById(planet2Id)

    }

}