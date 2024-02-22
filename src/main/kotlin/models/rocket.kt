package models

import org.example.models.Capsule
import org.example.models.Engine
import kotlin.math.floor
import kotlin.math.log
import kotlin.math.pow

/**
 * @author Aidan Scott
 *  * rocket is the class that manages all of the logic for thrust, ISP capsules and other parts of how a rocket works
 * @param name, optional name for your rocket
 */
class rocket(var name: String = "My First Rocket") {
    // Init default values for capsule, tank, engine and engine number. This means nulls don't need to be used.
    var capsule: Capsule = Capsule("Unset", 0, 0)
    var tank: Tank = Tank("Unset", 0, 0, 0.0)
    var engine: Engine = Engine("Unset", 0, 0, 0, 0.0)
    var engineNumber = 0
    private val gravity = 9.81 // Gravity force downward in meters / second

    /**
     * getMass() calculates mass
     * @return mass: Int
     */
    fun getMass(): Int {
        return capsule.mass + tank.mass + tank.liquidFuel + engine.mass * engineNumber
    }

    /**
     * getThrust() calculates thrust
     * @return thrust: Int
     */
    fun getThrust(): Int {
        return engine.thrust * engineNumber * 1000
    }

    /**
     * getISP() calculates ISP (specific impulse) how efficient a rocket engine is
     * @return ISP: Int
     */
    fun getISP(): Int {
        return engine.isp
    }

    /**
     * getTWR() calculates thrust to weight ratio
     * @return TWR: Double
     */
    fun getTWR(): Double {
        return getThrust() / (getMass() * gravity)
    }

    /**
     * getDeltaV() calculates the deltaV or change in velocity the rocket has
     * @return deltaV: Int
     */
    fun getDeltaV(): Int {
        val dryMass = getMass() - tank.liquidFuel
        val deltaV = getISP() * gravity * log(((getMass() / dryMass.toDouble())), Math.E)
        return deltaV.toInt()
    }

    /**
     * getMaxEngines() calculates the maximum number of engines that could fit on the currently installed tank
     * @param engine: Engine
     * @return engines: Int
     */
    fun getMaxEngines(engine: Engine): Int {
        return (floor((Math.PI * (tank.width / 2).pow(2.0)) / (Math.PI * (engine.width / 2).pow(2.0)))).toInt()
    }
}