package com.alabvix.planet.api.planet

class PlanetNotFoundException : RuntimeException {
    constructor(msg:String) : super(msg)
}