package com.alabvix.planet.api.planet.payload

import com.alabvix.planet.api.planet.validator.PlanetId
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CalculateDistanceInputPayload (
    @field:NotNull
    @field:NotEmpty(message = "Planet id 1 cannot be empty")
    @field:NotBlank(message = "Planet id 1 cannot be blank")
    @PlanetId
    val planet1Id: String,

    @field:NotNull
    @field:NotEmpty(message = "Planet id 2 cannot be empty")
    @field:NotBlank(message = "Planet id 2 cannot be blank")
    val planet2Id: String,
)