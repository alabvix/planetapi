package com.alabvix.planet.api.planet

import com.alabvix.planet.api.planet.payload.PlanetInputPayload
import com.alabvix.planet.api.planet.payload.PlanetOutputPayload
import org.springframework.stereotype.Component
import java.util.*

@Component
class PlanetConverter {
    fun toOutputPayload(entity: PlanetEntity): PlanetOutputPayload {
        return PlanetOutputPayload(
            entity.id,
            entity.name,
            entity.x,
            entity.y,
            entity.z
        )
    }

    fun toEntity(inputPayload: PlanetInputPayload): PlanetEntity {
        return PlanetEntity(
            UUID.randomUUID(),
            inputPayload.name,
            inputPayload.x,
            inputPayload.y,
            inputPayload.z
        )
    }
}