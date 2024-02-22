package models
/**
 * Base models.Tank class
 * @param name String: name of the fuel tank
 * @param mass String: Mass in kg
 * @param liquidFuel Int: quantity of liquid fuel in the tank in kg
 * @param width Double: width of the tank
 */
data class Tank(val name: String, val mass: Int, val liquidFuel: Int, val width: Double)