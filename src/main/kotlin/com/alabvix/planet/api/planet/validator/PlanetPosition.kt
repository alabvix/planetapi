package com.alabvix.planet.api.planet.validator

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PlanetPositionValidator::class])
@ReportAsSingleViolation
@MustBeDocumented
annotation class PlanetPosition(val message:String = "Planet position can not be zero",
                                val groups: Array<KClass<Any>> = [],
                                val payload: Array<KClass<Payload>> = [])
