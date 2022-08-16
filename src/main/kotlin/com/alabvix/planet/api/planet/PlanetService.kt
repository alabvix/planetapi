package com.alabvix.planet.api.planet

import com.alabvix.planet.api.planet.payload.CalculateDistanceInputPayload
import com.alabvix.planet.api.planet.payload.CalculateDistanceOutputPayload
import com.alabvix.planet.api.planet.payload.PlanetInputPayload
import com.alabvix.planet.api.planet.payload.PlanetOutputPayload
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

@Service
class PlanetService(
    private val repository: PlanetRepository,
    private val converter: PlanetConverter) {

    fun getPlanets(): List<PlanetOutputPayload> =
        repository.findAll().map { PlanetOutputPayload(it.id, it.name, it.x, it.y, it.z) }

    fun savePlanet(payload: PlanetInputPayload): PlanetOutputPayload {

        val planetEntity = converter.toEntity(payload)

        repository.save(planetEntity)

        return converter.toOutputPayload(planetEntity)
    }

    /**
     * The distance between two planet is calculated by the formula:
     *
     * dAB = Square Root of (xb - xa)2 + (yb - ya)2 + (zb - za)2
     *
     */
    fun calculateDistance(payload: CalculateDistanceInputPayload):CalculateDistanceOutputPayload {

        val planetA: Optional<PlanetEntity> = repository.findById(UUID.fromString(payload.planet1Id))
        if (planetA.isEmpty){
            throw PlanetNotFoundException("Planet not found with id: " + payload.planet1Id);
        }

        val planetB: Optional<PlanetEntity> = repository.findById(UUID.fromString(payload.planet2Id))
        if (planetB.isEmpty){
            throw PlanetNotFoundException("Planet not found with id: " + payload.planet2Id);
        }

        val x:Double = planetB.get().x - planetA.get().x
        val y:Double = planetB.get().y - planetA.get().y
        val z:Double = planetB.get().z - planetA.get().z

        val x2:Double = x.pow(2.toDouble())
        val y2:Double = y.pow(2.toDouble())
        val z2:Double = z.pow(2.toDouble())

        val distance = sqrt(x2 + y2 + z2)

        return CalculateDistanceOutputPayload(BigDecimal(distance).setScale(2, RoundingMode.HALF_EVEN).toDouble())

    }

}