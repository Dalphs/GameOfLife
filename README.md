# GameOfLife

This is an example of a game of life

The rules areas follows:

* If a cell is alive and has 1 or fewer live neighbors it dies by lonliness 
* If a cell is alive has 4 or more live neighbors it dies by overcrowding
* If a cell is alive and has 2 or 3 live neighbors it stays alive
* If a cell is dead and has exactly 3 neighbors it comes alive
* In all other instances the cell stays dead

In my example you can choose the side of an X*X grid where x >= 10 and x <= 40

If you click on a dead cell it comes alive

A timer will autmatically update the board every 0.4 second

When the Game class updates the board it uses its own thread

I have used ArrayList for storing Observers, and could have used Maps to store and keep track of each individual cell

The performance of the game would probably be something like O^2 due to the nested forloops

In the video you can see an example of how the program works. 
On the video the program is lagging but that is not the case when running the program. 
At 0:10 I stop the game and add my own pattern to the board and then resume the game.

[![Game of life](https://img.youtube.com/vi/gpEyN-xYoF0/0.jpg)](https://www.youtube.com/watch?v=gpEyN-xYoF0)

