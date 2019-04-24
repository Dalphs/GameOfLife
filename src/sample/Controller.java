package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
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
    int size;
    double height;
    double width;

    /**
     * The game starts when the "Start Game" button is pressed
     * @param actionEvent
     */
    public void startGame(ActionEvent actionEvent) {
        //removes all elements in the pane
        mainPane.getChildren().clear();

        //The classvariable viewmodel is assigned with the size chosen on the slider
        size = (int) matrixSizeSlider.getValue();
        viewModel = new ViewModel(size);

        //The gameVisualiser is declared, which is a graphics factory
        //An observer is created to observe the gamevisualiser and notifies the Controller if the user presses a square
        gameVisualiser = new GameVisualiser();
        CellObserver cellObserver = new CellObserver();
        gameVisualiser.addObserver(cellObserver);

        //An observer is passed to the viewModel to be able observe model, and update the GUI when changes occur in the model
        ModelObserver modelObserver = new ModelObserver();
        viewModel.addObserToModel(modelObserver);

        //The first generation of the program is shown, and a timer started so the GUI changes automatically
        defaultLayout();
        timer();
    }

    /**
     * Clears the current board in the GUI and parses a Cell[][] to a visual representation, which is then shown
     * @param board
     */
    public void updateGame(Cell[][] board){
        //Creates a new generation to show in the GUI, it removes the node at index 0,
        // which will always be the last generation, because .toFront() method is called on the startStopTimer Button
        currentGeneration = gameVisualiser.parseCellArray(board);
        mainPane.getChildren().remove(0);
        mainPane.getChildren().add(currentGeneration);
        startStopTimer.toFront();


    }

    /**
     * Creates the first generation of the game, and creates the "Start/Stop" Button
     */
    public void defaultLayout(){
        //Creates the visual representation of the first generation
        currentGeneration = gameVisualiser.parseCellArray(viewModel.getGameBoard());

        mainPane.getChildren().add(currentGeneration);
        System.out.println(currentGeneration.getHeight());

        //Creates a Button which starts or stops the game when pressed
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
        startStopTimer.setLayoutX(250); startStopTimer.setLayoutY(610);
        startStopTimer.setPrefSize(200,60);

        //Adds the Button to the pane and ensures that the Button is the forward most element
        mainPane.getChildren().add(startStopTimer);
        startStopTimer.toFront();
    }

    /**
     * Timer class, to update the game at a fixed rate using the Timeline class
     */
    public void timer(){
        //The game will update every 400 milisecond
        timeline = new Timeline(new KeyFrame(Duration.millis(400), ev -> {
            viewModel.updateGame();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Class for observing the Model
     */
    public class ModelObserver implements Observer{

        @Override
        public void update(Cell[][] board) {
            updateGame(board);
        }

        @Override
        public void update(int x, int y) {

        }
    }

    /**
     * Class for observing the GameVisualiser class
     */
    public class CellObserver implements Observer{

        @Override
        public void update(Cell[][] board) {

        }

        @Override
        public void update(int x, int y) {
            viewModel.makeCellComeAlive( (int) (x / (currentGeneration.getWidth() / (double) size)), (int) (y / (currentGeneration.getHeight() / (double) size)));
        }
    }
}
