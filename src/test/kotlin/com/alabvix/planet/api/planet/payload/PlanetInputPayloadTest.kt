package com.alabvix.planet.api.planet.payload

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator

class PlanetInputPayloadTest {

    private val factory = Validation.buildDefaultValidatorFactory()

    private val validator: Validator = factory.validator

    @Test
    fun `Given a Planet input payload with x=0 should create a constraint violation`() {

        val planetInput = PlanetInputPayload("Jupiter", 0.00, 250.00, 300.00)

        val violations: Set<ConstraintViolation<PlanetInputPayload>> =
            validator.validate(planetInput)

        Assertions.assertFalse(violations.isEmpty())
        Assertions.assertEquals(1, violations.size)
        val violation = violations.iterator().next()
        Assertions.assertEquals("Planet position can not be zero", violation.message)

    }
}