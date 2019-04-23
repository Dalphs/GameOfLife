package observable;

import model.Cell;

import java.util.ArrayList;

public class Observable {
    public boolean hasChanged = false;
    public ArrayList<Observer> observers = new ArrayList<>();

    public Observable() {
    }

    public void setChanged(){
        hasChanged = true;
    }

    public void notifyObservers(Cell[][] board){
        for (Observer o: observers) {
            o.update(board);
        }
        hasChanged = false;
    }

    public void notifyObservers(int x, int y){
        for (Observer o: observers) {
            o.update(x, y);
        }
        hasChanged = false;
    }

    public void addObserver(Observer o){
        observers.add(o);
    }
}
