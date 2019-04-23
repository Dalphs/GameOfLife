package viewmodel;

import model.Cell;
import model.Game;

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

    public Cell[][] getGameBoard(){
        return game.getBoard();
    }


}
