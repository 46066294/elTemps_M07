package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.Scanner;

public class Controller {

    public TextField textCiudad;
    public TextField textDies;
    public ListView lw;

    public Button btAceptar;
    public AnchorPane mainPane;

    ObservableList<Object> tiemposOL = FXCollections.observableArrayList(
            "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");

    ListView<Object> list = new ListView<Object>(tiemposOL);

    public void inicialize(){
        list.setItems(tiemposOL);
    }

}
