package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Aplicació que informa del temps
 * d'una ciutat (introduida per l'usuari) per a
 * tants dies com introdueixi l'usuari.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("El Temps Desktop");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        //Controller controller = (Controller)loader.getController();

    }


    public static void main(String[] args) {
        launch(args);
    }
}