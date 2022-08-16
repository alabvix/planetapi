package com.alabvix.planet.api.planet.payload

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator

class CalculateDistanceInputPayloadTest {

    private val factory = Validation.buildDefaultValidatorFactory()

    private val validator: Validator = factory.validator

    @Test
    fun `Given a Distance input payload with invalids UUIDs should create a constraint violation`() {

        val payload = CalculateDistanceInputPayload("0000", "29929")

        val violations: Set<ConstraintViolation<CalculateDistanceInputPayload>> =
            validator.validate(payload)

        Assertions.assertFalse(violations.isEmpty())
        Assertions.assertEquals(1, violations.size)
        val violation = violations.iterator().next()
        Assertions.assertEquals("Invalid planet UUID", violation.message)

    }
}