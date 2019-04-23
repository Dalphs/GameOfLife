package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Cell;
import viewmodel.ViewModel;

public class Controller {
    @FXML
    public Pane mainPane;
    @FXML
    public Slider matrixSizeSlider;
    ViewModel viewModel;
    GameVisualiser gameVisualiser;
    GridPane currentGeneration;

    public void start(){
        mainPane.getChildren().clear();
        viewModel = new ViewModel((int) matrixSizeSlider.getValue());
        gameVisualiser = new GameVisualiser();
        defaultLayout();
        //updateGame(viewModel.getGameBoard());

    }

    public void startGame(ActionEvent actionEvent) {
        start();
    }

    public void updateGame(Cell[][] board){
        currentGeneration = gameVisualiser.parseCellArray(board);

    }

    public void defaultLayout(){
        currentGeneration = gameVisualiser.parseCellArray(viewModel.getGameBoard());
        currentGeneration.setMaxHeight(600); currentGeneration.setMaxWidth(600);
        currentGeneration.setLayoutX(0); currentGeneration.setLayoutY(0);

        mainPane.getChildren().add(currentGeneration);

        Button startStopTimer = new Button("Start/Stop");
        startStopTimer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        startStopTimer.setLayoutX(200); startStopTimer.setLayoutY(610);
        startStopTimer.setPrefSize(200,60);
        mainPane.getChildren().add(startStopTimer);
    }
}
