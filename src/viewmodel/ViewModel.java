package viewmodel;

import model.Cell;
import model.Game;
import observable.Observer;

import java.util.Timer;
import java.util.TimerTask;

public class ViewModel {
    Game game;
    boolean timerOn = true;


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
