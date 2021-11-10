package de.rabitem.evaluationtool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Layout.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Evaluation Tool!");
        stage.setScene(scene);

        Controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        //controller.initialize();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}