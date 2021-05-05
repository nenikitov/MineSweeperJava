# Minesweeper
Minesweeper game created using Java. Outputs to console.

## How to play
### Difficulties
When the game is launched, it gives the user the possiblity of selecting the difficulty. There is a number of possible difficulties that you can select from (and even the ability to play on custom difficulty). (And yes, they are references to difficulty names of classic shooters...)
#### Table
| Difficulty | Name                            | Description                               | Mine Field Dimensions | Percentage of Mine Tiles | Player Lives |
|------------|---------------------------------|-------------------------------------------|-----------------------|--------------------------|--------------|
| Custom     | Mine Your own Custom Mine Field | Set up the difficulty yourself            | N.A                   | N.A.                     | N.A.         |
| Trivial    | Piece of Cake                   | Easy mode for beginners                   | 8x6                   | 10%                      | 2            |
| Easy       | Mine Me Plenty                  | A bit harder, but still easy              | 10x8                  | 12%                      | 2            |
| Normal     | Thou Art a Defuse Meister       | The right mode for intermediate players   | 15x8                  | 15%                      | 1            |
| Hard       | They Call Me "The Mine Sweeper" | About as difficult as you want to go      | 20x10                 | 20%                      | 1            |
| Unfair     | Minecore!                       | This skill level isn't even remotely fair | 30x15                 | 25%                      | 1            |
### Goal and rules
Each turn the game displays the state of the mine field. The goal is to open all the empty tiles while avoiding mined tiles.
* Opening a mine will decrement the number of your lives. Eventually, you will lose.
* Opening an empty tile reveals the number of mines in adjacent tiles. 
### Commands
The game uses console to get player input and output the state of the game. To do so, you should use in-game commands.
#### Table
| Command                   | How to execute  | Number of arguments | Action                                                                                      | Usage&nbsp;Example  |
|---------------------------|-----------------|---------------------|---------------------------------------------------------------------------------------------|----------------|
| Open                      | `open`, `o`     | 2                   | Opens the specified tile                                                                    | `open a 5`     |
| Mark with a Flag          | `flag`, `f`     | 2                   | Marks the specified tile with a flag. Used to mark where you are sure there is a mine       | `flag c 10`    |
| Mark with a Question Mark | `question`, `q` | 2                   | Marks the specified tile with a question. Used to mark where you are unsure there is a mine | `question y 2` |
| Clear all the Marks       | `clear`, `c`    | 2                   | Clears all the marks form the specified tile                                                | `clear q 3`    |
| Documentation             | `help`, `h`     | 0                   | Prints the documentation on how to play                                                     | `help`         |
| Retry                     | `retry`, `r`    | 0                   | Resets the game, keeping the same settings                                                  | `retry`        |
| Restart                   | `restart`, `r!` | 0                   | Resets the game and prompts to input new settings                                           | `restart`      |
| Quit                      | `quit`, `q!`    | 0                   | Quits the game                                                                              | `quit`         |