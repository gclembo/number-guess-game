package gclembo.numberguess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class is an application for a number guessing game
 * @author Carter Lembo
 */
public class Main extends Application {

    /**
     * Sets up stage for the window of the application
     * @param stage stage to add visual elements
     */
    @Override
    public void start(Stage stage) throws IOException {
        // sets FXML
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);

        //sets css styling
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);

        // sets window properties
        Image icon = new Image("/numberIcon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Guess The Number");
        stage.setResizable(false);

        // displays scene
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}