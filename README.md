---
SpaceAdventure Kotlin Addition
---
The kotlin version of space adventure is a port of my python space adventure fromhttps://github.com/cwdatlas/SpaceAdventure-terminal-.
You are able to use the cli to build a rocket and fly it, and if you have enough thrust and DeltaV, then you can get to space!
This is my first ever Kotlin program! Woohoo
Vocab for the game!
- DeltaV -> the change in velocity for your rocket, the more the better.
- TWR -> Thrust to weight ratio is the thrust / mass * 9.81. If it is over 1, then your rocket goes up. If it is over 1.5 it has a chance to get to space.
- ISP -> Specific impulse (ISP) is the efficiency of a rocket engine measured in seconds. think of it as miles per gallon.

---
how to install
---
you must have git and java17 and up installed to run this program

- in a new folder use 'git repo clone 'https://github.com/cwdatlas/SpaceAdventureKotlin-CLI' in your command line
- Navigate into the newly created directory
- Now type ./gradlew run, and the application will boot up. --Be warned there is a major issue relating to booting up game this way--
- If the application boots and repeats "invalid command, try help to find valid commands", then the error is trying to be fixed. This error seems to result from gradle printing lines that the game picks up resulting in a loop.

---
how to use
---
Here is a list of commands for the game:
capsule -> Lists capsules and their stats. Any choice will override a previous choice
tank    -> Lists tanks and their stats. Any choice will override a previous choice
engine  -> Lists engines and their stats. Any choice will override a previous choice
status  -> Lists the mass and thrust of your rocket. you will just have to wait for Delta V to be calculated
launch  -> Launches your rocket! See how far you get!
help    -> looks like you already know what this does
thankyou-> Shows my thanks for the person that helped debug (Anzhela the debugger)