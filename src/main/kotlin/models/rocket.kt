package org.example.models
import Tank
import kotlin.math.*

/*
    Name: Name of the rocket, you will be able to choose a name
    capsule: object type Capsule
    tank: object type tank
    engine: object type engine
 */
class rocket(var name: String = "My First Rocket") {
    var capsule: Capsule = Capsule("Unset", 0, 0)
    var tank: Tank = Tank("Unset", 0, 0, 0.0)
    var engine: Engine = Engine("Unset", 0, 0, 0, 0.0)
    var engineNumber = 0
    private val gravity = 9.81 // Gravity force downward in meters / second

    fun getMass(): Int{
        return capsule.mass + tank.mass + tank.liquidFuel + engine.mass*engineNumber
    }
    fun getThrust(): Int {
        return engine.thrust*engineNumber*1000
    }
    fun getISP(): Int{
        return engine.isp
    }
    fun getTWR(): Double{
        return getThrust()/(getMass()*gravity)
    }
    fun getDeltaV(): Int{
        val dryMass = getMass() - tank.liquidFuel
        var deltaV = getISP() * gravity * log(((getMass() / dryMass.toDouble())), Math.E)
        return deltaV.toInt()
    }
    fun getMaxEngines(engine: Engine): Int{
        return (floor((Math.PI*(tank.width / 2).pow(2.0)) /(Math.PI*(engine.width/2).pow(2.0)))).toInt()
    }
    fun setCapsule(capsule: Capsule): Boolean{
        this.capsule = capsule
        return this.capsule == capsule
    }
    fun setEngines(engine: Engine): Boolean{
        this.engine = engine
        return this.engine == engine
    }
    fun setTank(tank: Tank): Boolean{
        this.tank = tank
        return this.tank == tank
    }
}