package com.alabvix.planet.api.planet

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name="planet")
data class PlanetEntity(
    @Id
    @Type(type = "uuid-char")
    val id: UUID,

    val name: String,

    val x: Double,

    val y: Double,

    val z: Double
)