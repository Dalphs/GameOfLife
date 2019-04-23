package observable;

import model.Cell;

public interface Observer {
    public void update(Cell[][] board);
}
