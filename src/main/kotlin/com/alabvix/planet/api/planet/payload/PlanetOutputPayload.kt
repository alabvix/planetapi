package com.alabvix.planet.api.planet.payload

import java.util.*

data class PlanetOutputPayload(val id: UUID,
                               val name: String,
                               val x: Double,
                               val y: Double,
                               val z: Double)
