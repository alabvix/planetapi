package com.alabvix.planet.api.exception

import com.alabvix.planet.api.planet.PlanetNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleIllegalStateException(ex: MethodArgumentNotValidException): ResponseEntity<MutableMap<String, String?>> {

        var errorsS : String = ""
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach { error ->
            val errorMessage = error.defaultMessage
            errors["validationError"] = errorMessage
            errorsS += errorMessage
        }

        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(PlanetNotFoundException::class)
    fun handlePlanetNotFoundException(ex: PlanetNotFoundException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

}