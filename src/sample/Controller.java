package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.Scanner;

public class Controller {

    public TextField textFieldCiudad;
    public TextField textFieldDies;
    public ListView<String> listViewGeneral = new ListView<String>();
    public Button bAceptar;

    ConnectAPI connectApi = new ConnectAPI();

    public void initialize(){

        textFieldCiudad.setText(connectApi.getCity());
        textFieldDies.setText(connectApi.getDies());

        ObservableList<String> tiemposOL = FXCollections.observableArrayList(connectApi.vectorTemperaturas);

        listViewGeneral.setItems(tiemposOL);
    }


    public void metodoAceptar(){
        //ConnectAPI connectApi = new ConnectAPI();
        connectApi.limpiaListView();
        connectApi.setCity(textFieldCiudad.getText());
        connectApi.setDies(textFieldDies.getText());

        connectApi.mainTemps();

        ObservableList<String> tiemposOL = FXCollections.observableArrayList(connectApi.vectorTemperaturas);

        listViewGeneral.setItems(tiemposOL);


    }

    public void setCityOnMain() {
        connectApi.setCity(textFieldCiudad.getText());
    }

    public void setDiesOnMain() {
        connectApi.setDies(textFieldDies.getText());
    }
}