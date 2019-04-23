package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Cell;
import observable.Observer;
import viewmodel.ViewModel;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    @FXML
    public Pane mainPane;
    @FXML
    public Slider matrixSizeSlider;
    ViewModel viewModel;
    GameVisualiser gameVisualiser;
    GridPane currentGeneration;
    Button startStopTimer;
    Timeline timeline;

    public void startGame(ActionEvent actionEvent) {
        mainPane.getChildren().clear();
        viewModel = new ViewModel((int) matrixSizeSlider.getValue());
        gameVisualiser = new GameVisualiser();
        defaultLayout();
        ModelObserver modelObserver = new ModelObserver();
        viewModel.addObserToModel(modelObserver);
        timer();
    }

    public void updateGame(Cell[][] board){
        currentGeneration = gameVisualiser.parseCellArray(board);
        mainPane.getChildren().remove(currentGeneration);
        mainPane.getChildren().add(currentGeneration);
        startStopTimer.toFront();


    }

    public void defaultLayout(){
        currentGeneration = gameVisualiser.parseCellArray(viewModel.getGameBoard());
        currentGeneration.setMaxHeight(600); currentGeneration.setMaxWidth(600);
        currentGeneration.setLayoutX(0); currentGeneration.setLayoutY(0);

        mainPane.getChildren().add(currentGeneration);

        startStopTimer = new Button("Start/Stop");
        startStopTimer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (timeline.getStatus() == Animation.Status.STOPPED)
                    timeline.play();
                else
                    timeline.stop();
            }
        });
        startStopTimer.setLayoutX(200); startStopTimer.setLayoutY(610);
        startStopTimer.setPrefSize(200,60);
        mainPane.getChildren().add(startStopTimer);
        startStopTimer.toFront();
    }

    public void timer(){
        timeline = new Timeline(new KeyFrame(Duration.millis(400), ev -> {
            viewModel.updateGame();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    public class ModelObserver implements Observer{

        @Override
        public void update(Cell[][] board) {
            updateGame(board);
        }
    }
}
