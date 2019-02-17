This gradle based project is a place for two test tasks:

Test 1. Visitor
Test 2. Paper Scissors Rock

The first test located in imc.visitor package and the second in the : imc.game.

To build both tasks :

./gradlew clean build appJar

Run only integration tests :

./gradlew integrationTest

Run Paper Scissors Rock Game :

java -jar build/libs/Navigutra-all.jar -c game.properties

The game will run until specified number of the game cycles will be played (specified in config file) 
or until Q command will be entered into console. 

