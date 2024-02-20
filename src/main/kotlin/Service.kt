package org.example

class Service {
    // Map of functions tied to keywords that are used as user commands
    var commands = mapOf(
        "capsule" to ::capsule,
        "tank" to ::tank,
        "engine" to ::engine,
        "help" to ::help,
        "status" to ::status,
        "reset" to ::reset,
        "launch" to ::launch,
        "quit" to ::quit
    )
    var gameLoop = true



    fun startGame(){

    }
    fun capsule() {

    }
    fun tank(){

    }
    fun engine(){

    }
    fun status(){

    }
    fun launch(){

    }
    fun reset(){

    }
    fun help(){

    }
    fun quit(){

    }
}