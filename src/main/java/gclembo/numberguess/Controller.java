package gclembo.numberguess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This is a controller which handles user input and output
 */
public class Controller {
    @FXML
    private Label textLabel;
    @FXML
    private Label countLabel;
    @FXML
    private TextField textField;
    @FXML
    private Button enterButton;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private final String css;
    private boolean hasGameStarted;
    private boolean isGameOver;
    private NumberGuess numberGuessGame;

    /**
     * Creates new controller
     */
    public Controller() {
        hasGameStarted = false;
        isGameOver = false;
        css = this.getClass().getResource("application.css").toExternalForm();
    }

    /**
     * On button press, handles user input and progresses the game
     */
    public void submit() {
        if (isGameOver) {
            resetGame();
        } else {
            try {
                // get user input
                int inputNum = Integer.parseInt(textField.getText());
                if (hasGameStarted) {
                    // make guess
                    checkGuess(inputNum);
                } else {
                    // start new game
                    numberGuessGame = new NumberGuess(inputNum);
                    textLabel.setText("Guess the secret number!");
                    hasGameStarted = true;
                }
            } catch (NumberFormatException e) {
                textLabel.setText("Enter only integer numbers from 1 to " +
                        Integer.MAX_VALUE + " please");
            } catch (IllegalArgumentException e) {
                textLabel.setText(e.getMessage());
            } catch (Exception e) {
                textLabel.setText("An Error Has Occurred");
            }
            textField.clear();
        }
    }

    /**
     * Given a user guess, tells the user if they guessed correctly, or if they were too low or high
     * @param inputNum guess from user input
     */
    private void checkGuess(int inputNum) {
        int dist = numberGuessGame.makeGuess(inputNum);
        countLabel.setText("Guesses: " + numberGuessGame.getNumberOfGuesses());
        if (dist == 0) {
            textLabel.setText("You Win!");
            endGame();
        } else if (dist > 0) {
            textLabel.setText("Too High");
        } else {
            textLabel.setText("Too Low");
        }
    }

    /**
     * Sets up and formats the end screen layout
     */
    private void endGame() {
        enterButton.setText("Restart");
        textField.setVisible(false);
        textField.setPrefHeight(0);
        textLabel.setStyle("-fx-font-size: 50; -fx-text-fill: #0000FF;");
        countLabel.setText("You won in " + numberGuessGame.getNumberOfGuesses() + " guesses!");
        isGameOver = true;
    }

    /**
     * Resents and formats the main game layout
     */
    private void resetGame() {
        enterButton.setText("Enter");
        textField.setVisible(true);
        textField.setPrefHeight(30);
        textLabel.setStyle("-fx-font-size: 24; -fx-text-fill: #000000;");
        countLabel.setText("");
        textLabel.setText("Let's play guess the number! I will chose a number from 0 to " +
                "(enter an upper bound)");
        isGameOver = false;
        hasGameStarted = false;
    }

    /**
     * Changes scene to the main game
     * @param event event of button being pressed
     */
    public void switchToGame(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes scene to the game instructions.
     * @param event event of button being pressed
     */
    public void switchToInstructions(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("instructions.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}