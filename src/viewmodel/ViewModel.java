package viewmodel;

import model.Cell;
import model.Game;
import observable.Observer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is responsible for creating an instance of the gameClass and communicate from view to model
 */
public class ViewModel {
    Game game;


    public ViewModel(int size) {
        game = new Game(size);
    }

    public void updateGame(){
        game.updateBoard();
    }

    public void print(){
        game.printBoardToConsole();
    }


    public void addObserToModel(Observer o){
        game.addObserver(o);
    }

    public Cell[][] getGameBoard(){
        return game.getBoard();
    }

    public void makeCellComeAlive(int x, int y){
        game.makeCellComeAlive(x, y);
    }
}
