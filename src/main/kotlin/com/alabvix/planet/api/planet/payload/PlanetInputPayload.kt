package com.alabvix.planet.api.planet.payload

import com.alabvix.planet.api.planet.validator.PlanetPosition
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class PlanetInputPayload(
    @field:NotNull(message = "Planet name cannot be null")
    @field:NotEmpty(message = "Planet name cannot be empty")
    @field:NotBlank(message = "Planet name cannot be blank")
    val name: String,

    @field:NotNull
    @field:Min(-50000, message = "Planet X position must be greater than -50000")
    @PlanetPosition
    val x: Double,

    @field:NotNull
    @field:Min(-50000, message = "Planet Y position must be greater than -50000")
    @PlanetPosition
    val y: Double,

    @field:NotNull
    @field:Min(-50000, message = "Planet Z position must be greater than -50000")
    @PlanetPosition
    val z: Double)
