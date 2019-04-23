package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Cell;
import observable.Observable;


public class GameVisualiser extends Observable {

    public GameVisualiser() {
    }

    public GridPane parseCellArray(Cell[][] board){
        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(600); gridPane.setMaxHeight(600);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                double width = 600 / (double) board.length;
                double height = 600 / (double) board[0].length;

                Rectangle cell = createCell(width, height, board[i][j].isAlive());

                gridPane.add(cell, i, j);
            }
        }
        return gridPane;
    }

    public Rectangle createCell(double width, double height, boolean alive){
        Rectangle cell = new Rectangle(width, height);
        Color fillColor = alive ? Color.WHITE : Color.BLACK;
        Color strokeColor = alive ? Color.BLACK : Color.BLACK;
        cell.setFill(fillColor);
        cell.setStroke(strokeColor);

        cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = (int) event.getSceneX();
                int y = (int) event.getSceneY();
                setChanged();
                notifyObservers(x, y);
            }
        });

        return cell;
    }
}
