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
    var capsule: Capsule = Capsule("Default", 0, 0)
    var tank: Tank = Tank("Default", 0, 0, 0)
    var engine: Engine = Engine("Default", 0, 0, 0, 0)
    var engineNumber = 0
    val gravity = 9.81 // Gravity force downward in meters / second

    fun getMass(): Int{
        return capsule.mass + tank.mass + engine.mass*engineNumber
    }
    fun getThrust(): Int {
        return engine.thrust*engineNumber
    }
    fun getISP(): Int{
        return engine.isp
    }
    fun getTWR(): Int{
        return getThrust()/getMass()
    }
    fun getDeltaV(): Int{
        val dryMass = getMass() - tank.liquidFuel
        return (getISP() * gravity * log((getMass() / dryMass).toDouble(), 10.0)).toInt()
    }
    fun getMaxEngines(engine: Engine): Int{
        return (floor(tank.width.toDouble()/engine.width)).toInt()
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