# agentjandk


## BALL

Lancer le jar avec les paramètres par défaut: `java -jar ball.jar`

```
Usage: java -jar ball.jar
                          [-nbAgent <NumberOfAgent>] [-nbTurn <numberOfTurn>] [-width <width>] [-height <height>] [-agentSize <sizeOfAgent>] [-speed <speed>] [-seed <seed>] [-grid] [-equity] [-toric]

  [-nbAgent <NumberOfAgent>]
        Define the number of ball for the simulation. (default: 400)

  [-nbTurn <numberOfTurn>]
        number of turn of the simulation. (default: 1000000)

  [-width <width>]
        Width of the window. (default: 120)

  [-height <height>]
        Height of the window. (default: 120)

  [-agentSize <sizeOfAgent>]
        Size of one Agent in pixel. (default: 5)

  [-speed <speed>]
        waiting time (in ms) beetween each turn. (default: 50)

  [-seed <seed>]
        seed for the generation of random numbers. (default: random)

  [-grid]
        set the visiblity of grod. (default: false)

  [-equity]
        set the equity of simulation (shuffle beetween each turn). (default: false)

  [-toric]
        Set the world toric. (default: false)

```


## WATOR

Lancer le jar avec les paramètres par défaut: `java -jar wator.jar`

```
Usage: java -jar wator.jar
                          [-nbShark <NumberOfShark>] [-nbFish <numberOfFish>] [-fBreed <fishBreed>] [-sBreed <sharkBreed>] [-starve <SharkStarve>] [-nbTurn <numberOfTurn>] [-width <width>] [-height <height>] [-agentSize <sizeOfAgent>] [-speed <speed>] [-seed <seed>] [-grid] [-equity] [-noToric]

  [-nbShark <NumberOfShark>]
        number of shark at the beginning of simulation (default: 500)

  [-nbFish <numberOfFish>]
        number of fish at the beginning of simulation (default: 500)

  [-fBreed <fishBreed>]
        number of turn for the fish to breed (default: 1)

  [-sBreed <sharkBreed>]
        number of turn for the shark to breed (default: 10)

  [-starve <SharkStarve>]
        number of turn for the shark to die (default: 15)

  [-nbTurn <numberOfTurn>]
        number of turn of the simulation. (default: 1000000)

  [-width <width>]
        Width of the window. (default: 120)

  [-height <height>]
        Height of the window. (default: 120)

  [-agentSize <sizeOfAgent>]
        Size of one Agent in pixel. (default: 5)

  [-speed <speed>]
        waiting time (in ms) beetween each turn. (default: 5)

  [-seed <seed>]
        seed for the generation of random numbers. If 42 then random seed. (default: 0)

  [-grid]
        set the visiblity of grod. (default: false)

  [-equity]
        set the equity of simulation (shuffle beetween each turn). (default: true)

  [-noToric]
        Set the world no toric. (default: toric)

```



## PACMAN

Lancer le jar avec les paramètres par défaut: `java -jar pacman.jar`

```
Usage: java -jar pacman.jar
                          [-nbPredator <NumberOfPredator>] [-nbRock <NumberOfRock>] [-width <width>] [-height <height>] [-agentSize <sizeOfAgent>] [-speedRatio <speedRatioWithPredator>] [-speed <speed>] [-seed <seed>] [-grid] [-equity] [-dijkstra]

  [-nbPredator <NumberOfPredator>]
        number of predator at the beginning of simulation (default: 3)

  [-nbRock <NumberOfRock>]
        number of rock at the beginning of simulation (default: 500)

  [-width <width>]
        Width of the window. (default: 50)

  [-height <height>]
        Height of the window. (default: 50)

  [-agentSize <sizeOfAgent>]
        Size of one Agent in pixel. (default: 14)

  [-speedRatio <speedRatioWithPredator>]
        ratio Speed beetween Prey and predator. speedPrey = ratio * speedPredator (default: 2)

  [-speed <speed>]
        waiting time (in ms) beetween each turn. (default: 100)

  [-seed <seed>]
        seed for the generation of random numbers. (default: random)

  [-grid]
        set the visiblity of grod. (default: false)

  [-equity]
        set the equity of simulation (shuffle beetween each turn). (default: true)

  [-dijkstra]
        Set the world no toric. (default: false)

```
