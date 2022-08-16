package com.alabvix.planet.api.planet

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlanetRepository : CrudRepository<PlanetEntity, UUID> {
}