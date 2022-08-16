package com.alabvix.planet.api.planet

import com.alabvix.planet.api.planet.payload.CalculateDistanceInputPayload
import com.alabvix.planet.api.planet.payload.CalculateDistanceOutputPayload
import com.alabvix.planet.api.planet.payload.PlanetInputPayload
import com.alabvix.planet.api.planet.payload.PlanetOutputPayload
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class PlanetController(private val service: PlanetService) {

    @GetMapping
    fun getPlanets(): List<PlanetOutputPayload> {
        return service.getPlanets();
    }

    @PostMapping
    fun savePlanet(@Valid @RequestBody payload: PlanetInputPayload): ResponseEntity<PlanetOutputPayload> {
        return ResponseEntity.ok(service.savePlanet(payload))
    }

    @PostMapping("/calculate/distance")
    fun calculateDistance(@Valid @RequestBody payload: CalculateDistanceInputPayload):
            ResponseEntity<CalculateDistanceOutputPayload> {
        return ResponseEntity.ok(service.calculateDistance(payload))
    }

}