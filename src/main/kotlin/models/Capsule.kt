package models
/**
 * Base capsule class
 * @param name String: Name of the crew capsule
 * @param mass Int: mass of the craft in KG
 * @param crew Int: number of crew that can be onboard
 */
data class Capsule(val name: String, val mass: Int, val crew: Int)