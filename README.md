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
- Navigate into the newly created directory so you can see the 'gradlew' file.
- Now type './gradlew build', this will build a jar with all needed libraries.
- Lastly, type in 'java -jar build/lib/KSpaceAdventure-CLI-1.0-SNAPSHOT.jar' and rocket builder will boot right up!

Note: Using gradlew run from Gradle plugin application would be better, but because the RocketBuilder program consumes lines
from the CLI, gradle will start the program, then keep feeding information to the readln(). This makes the program completely
unusable, so building the jar with all the needed libraries then executing that was the best known option.

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