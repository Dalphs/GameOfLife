package observable;

import model.Cell;

public interface Observer {
    void update(Cell[][] board);
    void update(int x, int y);
}
