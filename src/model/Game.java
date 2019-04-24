package model;

import observable.Observable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class has some of the game logic and is responsible for holding and uppdating the board
 */
public class Game extends Observable {
    Cell[][] board;

    public Game(int size) {
        board = new Cell[size][size];
        createRandomBoard();
    }

    /**
     * Creates a x*x matrix with random Cell objects
     */
    public void createRandomBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Cell((int) (Math.random() * 2) == 1);
            }
        }
    }

    /**
     * Updates the board from generation n to generation n + 1
     */
    public void updateBoard(){
        //First all cells gets the number of livingNeighbors updated, and the the cells update() method is called
        updateNeighborsAllCells();
        updateAlive();
        setChanged();
        notifyObservers(board);

    }

    /**
     * Calls Game.updateNeighbors() on all cells in the matrix to get current number of neighbors
     */
    public void updateNeighborsAllCells(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0]. length; j++) {
             updateNeighbors(i, j);
            }
        }
    }

    /**
     * Takes x and y coordinate and counts the number of alive neighbors for the specific Cell
     * @param x
     * @param y
     */
    public void updateNeighbors(int x, int y){
        int sum = 0;

        //The nested forloop runs a 3 x 3 patter with the current cell at index (1, 1)
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                //This if statement makes sure there wont be an outOfBoundsException,
                // by checking that all indexes are between 0 and the size of the array
               if(x + i >= 0 && x + i < board.length && y + j >= 0 && y + j < board[0].length){
                   //If the cell being checked is alive and is not the cell in the middle of the 3 x 3 grid the counter is incremented
                   if(board[x + i][y + j].isAlive() && (x + i != x || y + j != y))
                       sum++;
               }

            }
        }
        board[x][y].setLivingNeighbors(sum);
    }

    /**
     * Calls Cell.update() on all cells in the maatrix
     */
    public void updateAlive(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j].update();
            }
        }
    }

    /**
     * Printing the matrix to the console
     */
    public void printBoardToConsole(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.printf("[" + (board[i][j].isAlive() ? 1 : 0) + "] ");
            }
            System.out.println();
        }
    }

    /**
     * This method changes state of Cell[x][y] and notifies the view when changed.
     * It is called from the viewModel when a square on the board has been pressed.
     * @param x
     * @param y
     */
    public void makeCellComeAlive(int x, int y){
        board[x][y].alive = true;
        setChanged();
        notifyObservers(board);
    }

    public Cell[][] getBoard() {
        return board;
    }
}
