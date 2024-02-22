package org.example.models

/*
    Name: Name of the Engine
    Mass: Mass in kg
    Thrust: thrust in kn (kili newton)
    ISP: efficiency of the engine in seconds
    Width: Greatest width of the engine
 */
data class Engine(val name: String, val mass: Int, val thrust: Int, val isp: Int, val width: Double)