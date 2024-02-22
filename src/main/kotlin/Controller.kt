import models.rocket
import org.example.models.Capsule
import org.example.models.Engine

/**
 * @author Aidan Scott
 * Controller is the main interaction the user has with the Rocket Builder program.
 * It provides the CLI interface to build a rocket with real parts!
 */
class Controller {
    // Map of functions tied to keywords that are used as user commands
    var commands = mapOf(
        "capsule" to ::capsule,
        "tank" to ::tank,
        "engine" to ::engine,
        "help" to ::help,
        "status" to ::status,
        "launch" to ::launch,
        "quit" to ::quit,
        "thankyou" to ::thankyou
    )

    // Init capsules
    private var capsules = mapOf(
        "apollo" to Capsule("Apollo", 11900, 3),
        "dragon" to Capsule("Dragon", 12519, 4),
        "orion" to Capsule("Orion", 10400, 4)
    )

    // Init tanks
    private var tanks = mapOf(
        "small" to Tank("small", 10000, 75000, 2.4),
        "medium" to Tank("medium", 27000, 400700, 3.7),
        "large" to Tank("large", 137000, 2077000, 10.1)
    )

    // Init engines
    private var engines = mapOf(
        "f-1" to Engine("F-1", 8400, 7770, 304, 3.7),
        "raptor" to Engine("Raptor", 1600, 2690, 363, 1.3),
        "rd-170" to Engine("RD-170", 9750, 7900, 337, 3.8)
    )

    // init rocket with default params
    private var rocket = rocket()

    // setting gameLoop to true, will be set to false on quit
    private var gameLoop = true

    // number of launch attempts so specific messages can be shown on the first attempt
    private var launchAttempts = 0

    fun startGame() {
        println(""" ______  ______  ______  __  __   ______  ______     ______    __  __  __  __     _____   ______  ______""")
        println("""/\  == \/\  __ \/\  ___\/\ \/ /  /\  ___\/\__  _\   /\  == \/ \ \/\ \/\ \/\ \    /\  __-./\  ___\/\  == \   """)
        println("""\ \  __<\ \ \/\ \ \ \___\ \  \_  \ \  __\\/_/\ \/   \ \  __< \ \ \_\ \ \ \ \ \___\ \ \/\ \ \  __\\ \  __<   """)
        println(""" \ \_\ \_\ \_____\ \_____\ \_\ \_\\ \_____\ \ \_\    \ \_____ \ \_____\ \_\ \_____\ \____-\ \_____\ \_\ \_\ """)
        println("""  \/_/ /_/\/_____/\/_____/\/_/\/_/ \/_____/  \/_/     \/_____/ \/_____/\/_/\/_____/\/____/ \/_____/\/_/ /_/ """)

        println("Welcome to Rocket Builder Kotlin Addition! If you need a refresher on the commands type 'help'.")
        println("Your goal is to build a rocket that can get to orbit! Type help for more information")
        println("Build your rocket by typing in 'capsule', 'tank', and 'engine' to choose your parts, then type 'launch' to launch!")
        println("You can always use 'quit' to quit the game.")
        println("Take note that you wont be able to use commands in the capsule, tank and engine builder bays")
        println("-------------------------------------------------------------------------------------------------------------------")
        // loop through readLn() and format user input by making all lowercase and getting rid of spaces
        while (gameLoop) {
            print("-> ") // Fun looking terminal so user knows that they are in game
            val userInput = readlnOrNull()?.lowercase()?.replace(" ", "")// format user input
            if (userInput in commands.keys)
                commands[userInput]?.invoke() // Invoke command in a null-protected way.
            else
            // If command is not found, tell the user
                println("Invalid command, try help to find valid commands")
        }
    }

    /**
     * capsule will list available capsules and allow the user to select one using the same method as in the start game
     * user input.
     */
    private fun capsule() {
        println("Welcome to the Capsule builder bay")
        println("---------------------------------")
        // List all capsules
        for (i in capsules.values) {
            println("--- ${i.name} Crew Capsule ---")
            println("Mass: ${i.mass}kg")
            println("Crew: ${i.crew}")
        }
        var choosing = true
        while (choosing) {
            print("Apollo, Dragon, Orion -> ")
            val userInput = readln().lowercase().replace(" ", "")
            if (userInput in capsules.keys) {
                println("$userInput selected")
                rocket.capsule = capsules[userInput]!! // adding capsule to the rocket
                choosing = false // stop loop
            } else if (userInput == "quit") {
                print("exiting Capsule Bay")
                choosing = false // stop loop
            } else
                println("Invalid Capsule, '$userInput' try again, or quit")
        }
    }

    /**
     * tank will list available tanks and allow the user to select one using the same method as in the start game
     * user input.
     */
    private fun tank() {
        println("Welcome to the Tank builder bay")
        println("---------------------------------")
        // List all tanks
        for (i in tanks.values) {
            println("--- ${i.name} Tank ---")
            println("Mass:     ${i.mass}kg")
            println("Wet Mass: ${i.liquidFuel}kn")
            println("Width:    ${i.width} meters")
        }
        var choosing = true
        while (choosing) {
            print("Small, Medium, Large -> ") // make sure user knows what their options are
            val userInput = readln().lowercase().replace(" ", "")
            if (userInput in tanks.keys) {
                println("$userInput selected")
                rocket.tank = tanks[userInput]!! // setting tank safely
                choosing = false
            } else if (userInput == "quit") {
                print("exiting Tank Bay")
                choosing = false
            } else
                println("Invalid Tank, '$userInput' try again, or quit")
        }
    }

    /**
     * engine will list available engines and allow the user to select one using the same method as in the start game
     * user input.
     * engine will also allow the user to select a number of engines, to a maximum which the rocket decides
     */
    private fun engine() {
        println("Welcome to the Engine builder bay")
        println("---------------------------------")
        // list all engines
        if (rocket.tank.name != "Unset") {
            for (i in engines.values) {
                println("--- ${i.name} Engine ---")
                println("Mass:   ${i.mass}kg")
                println("Thrust: ${i.thrust}kn")
                println("ISP:    ${i.isp} seconds")
                println("Width:  ${i.width} meters")
            }
            var choosingEngine = true // set up loop variables for both preceding loops
            var choosingNumEngine = true
            while (choosingEngine) { // start engine choosing loop
                print("F-1, Raptor, RD-170 -> ")
                val engineChoiceStr = readln().lowercase().replace(" ", "")
                if (engineChoiceStr in engines.keys) {
                    choosingEngine = false
                    println("$engineChoiceStr selected")
                    val engineChoice = engines[engineChoiceStr]!! // engine saved
                    rocket.engine = engineChoice// engine set
                    while (choosingNumEngine) { // start engine number set loop
                        val maxEngines = rocket.getMaxEngines(engineChoice)
                        println("Maximum number of engines mountable: $maxEngines")
                        print("number of engines -> ")
                        val input = readln()
                        val numberOfEngines = input.toIntOrNull()
                        if (numberOfEngines != null)
                            if (maxEngines < numberOfEngines) {
                                rocket.engineNumber = maxEngines
                                println("You wanted more engines than what could be mounted on the rocket, so we added ${rocket.engineNumber}!")
                                choosingNumEngine = false
                            } else {
                                rocket.engineNumber = numberOfEngines
                                println("Added ${rocket.engineNumber} rocket engines")
                                choosingNumEngine = false
                            }
                        else if (input == "quit") { // if user would like to quit they should be able to
                            choosingNumEngine = false
                            choosingEngine = false
                        } else {
                            println("Please select a valid number of engines, like '2', $input is not valid")
                        }
                    }
                } else if (engineChoiceStr == "quit") {
                    choosingEngine = false
                    choosingNumEngine = false
                } else
                    println("cant find engine ${engineChoiceStr}.")
            }
        } else {
            println("Please add a tank to your rocket in the tank builder bay before adding any engines")
        }

    }

    /**
     * status will list set rocket parts and the thrust to weight ratio
     */
    private fun status() {
        println("Status of all parts of your rocket, if some parts are Unset, you will need to select that part")
        println("If you have questions use 'help'")

        println("--- ${rocket.capsule.name} Crew Capsule ---")
        println("Mass: ${rocket.capsule.mass}kg")
        println("Crew: ${rocket.capsule.crew}")

        println("--- ${rocket.tank.name} Tank ---")
        println("Mass:     ${rocket.tank.mass}kg")
        println("Wet Mass: ${rocket.tank.liquidFuel}kn")
        println("Width:    ${rocket.tank.width} meters")

        println("--- ${rocket.engine.name} Engine Section ---")
        println("Number of Engines: ${rocket.engineNumber}")
        println("Mass:   ${rocket.engine.mass * rocket.engineNumber}kg")
        println("Thrust: ${rocket.engine.thrust * rocket.engineNumber}kn")
        println("ISP:    ${rocket.engine.isp} seconds")
        println("Width (single Engine):  ${rocket.engine.width} meters")
        println("Thrust to weight Ratio: ${rocket.getTWR()}")
    }

    /**
     * launch will check if the rocket is able to launch then check if the deltaV of the rocket is high enough to get to space
     */
    private fun launch() {
        // Checking to see if the full rocket has been built
        if (rocket.capsule.name == "Unset")
            println("You need to select a Capsule before you launch!")
        else if (rocket.tank.name == "Unset")
            println("You need to select a Tank before you launch!")
        else if (rocket.engine.name == "Unset")
            println("You need to select an Engine before you launch!")
        else if (rocket.getTWR() < 1.5) // checking if the thrust to weight ratio is high enough
            println("Check Thrust to weight ratio is too low, it must be over 1.5 to launch. Check it by typing status")
        else {
            println("Welcome to pad 39A where the legendary Saturn 5 Launched from")
            if (launchAttempts == 0) // if the launch attempt is the first, then state how much deltaV is needed to get to space
                println("hope you built your rocket well because it will take 8,0000 Delta V to make it to space!")
            launchAttempts++
            // Countdown from 3 to 0
            for (i in 1..4) {
                println("T-${4 - i}")
                Thread.sleep(1000)
            }
            val deltaV = rocket.getDeltaV() // Set rocket deltaV
            if (deltaV >= 8000) // Made it to space!
                println("You got $deltaV deltaV! You made it to space!! Good job!!")
            else // Didnt make it to space
                println("you didnt make it, you had $deltaV Delta V, to get to space you need 8000")
        }
    }

    /**
     * Provides help for the user. Similar to README.md file
     */
    private fun help() {
        println("Rocket builder, otherwise known as SpaceAdventure is a game where you can build your own rocket")
        println("You will need to balance mass, thrust and fuel to build a rocket with the highest Delta V.")
        println("Delta V is the measurement of how much change in velocity a rocket can achieve. This game does not take into account drag or more complicated factors.")
        println("ISP is a measure of efficiency, think miles/gallon.")
        println("1. Once Launched Rocket Builder checks thrust to mass ratio (TWR). If your TWR is less than 1.5, you fail to get to orbit")
        println("2. Your Delta V will then be calculated. Depending on it's value, you are able to achieve orbit around Earth")
        println("This game is supposed to be a learning experience, so the values for the capsules and engines are accurate. Sadly, Delta V Calculations are not.")
        println("When typing in commands remember to pay attention to capitalization!")
        println("<----> Here are all of the commands and the descriptions for what they do <---->")
        println("____________________________________________________________________________________________________________________________________")
        println("capsule -> Lists capsules and their stats. Any choice will override a previous choice")
        println("tank    -> Lists tanks and their stats. Any choice will override a previous choice")
        println("engine  -> Lists engines and their stats. Any choice will override a previous choice")
        println("status  -> Lists the mass and thrust of your rocket. you will just have to wait for Delta V to be calculated")
        println("launch  -> Launches your rocket! See how far you get!")
        println("help    -> looks like you already know what this does")
        println("thankyou-> Shows my thanks for the person that helped debug")
        println("____________________________________________________________________________________________________________________________________")
    }

    /**
     * Quits the game by setting the gameLoop to false
     * goodbye
     */
    private fun quit() {
        println("Goodbye!")
        gameLoop = false
    }

    /**
     * Thank you to the people who debug my random programs, which means thanks Anzhela.
     */
    private fun thankyou() {
        println("Thank you Anzhela for debugging when you has a test in your next class!")
    }
}