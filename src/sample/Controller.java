package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.Scanner;

public class Controller {

    public TextField textFieldCiudad;
    public TextField textFieldDies;
    public ListView<String> listViewGeneral = new ListView<String>();
    public Button bAceptar;

    //ConnectAPI connectApi = new ConnectAPI();

    ObservableList<String> tiemposOL = FXCollections.observableArrayList(
            "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");

    public void initialize(){
        listViewGeneral.setItems(tiemposOL);
    }

}
