package models
/**
 * Base Engine Class
 * @param name String: Name of the Engine
 * @param mass Int: Mass in kg
 * @param thrust Int: thrust in kn (kili newton)
 * @param isp Int: efficiency of the engine in seconds
 * @param width int: Greatest width of the engine
 */
data class Engine(val name: String, val mass: Int, val thrust: Int, val isp: Int, val width: Double)