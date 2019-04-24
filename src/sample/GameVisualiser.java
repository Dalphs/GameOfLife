package sample;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Cell;
import observable.Observable;

/**
 * This class acts as a Factory for creating the visual representation of the board
 */
public class GameVisualiser extends Observable {

    public GameVisualiser() {
    }

    /**
     * Takes a Cell[][] as a parameter and parses it to a GridPane as a visual represntation
     * @param board
     * @return
     */
    public GridPane parseCellArray(Cell[][] board){
        GridPane gridPane = new GridPane();
        //Iterates through the board and creates the same board as a GridPane and returns it
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                double width = 600 / (double) board.length;
                double height = 600 / (double) board[0].length;

                //Creates a single cell with the correct width and height as well as showing whether the Cell is Alive or not
                Rectangle cell = createCell(width, height, board[i][j].isAlive());
                gridPane.add(cell, i, j);
            }
        }
        return gridPane;
    }

    /**
     * Creates a rectangle with a speecified size aswell as assigning color to Stroke
     * and Fill depending on wether boolean aalive is true or false
     * @param width
     * @param height
     * @param alive
     * @return
     */
    public Rectangle createCell(double width, double height, boolean alive){
        Rectangle cell = new Rectangle(width, height);
        Color fillColor = alive ? Color.WHITE : Color.BLACK;
        Color strokeColor = alive ? Color.BLACK : Color.WHITE;
        cell.setFill(fillColor);
        cell.setStroke(strokeColor);

        //If a square is pressed the coordinates of the click is sent to the observer, which notifies the model
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
