package com.alabvix.planet.api.planet.validator

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class PlanetPositionValidator : ConstraintValidator<PlanetPosition, Double> {
    override fun isValid(position: Double, p1: ConstraintValidatorContext?): Boolean {
        return position != 0.00
    }

}