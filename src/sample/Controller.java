package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.util.Scanner;

/**
 * Controlador de l'aplicacio
 */
public class Controller {

    public TextField textFieldCiudad;
    public TextField textFieldDies;
    public ListView<String> listViewGeneral = new ListView<String>();
    public Button bAceptar;

    public TextField mediaTempMinimaController;
    public TextField mediaTempMaximaController;

    ConnectAPI connectApi = new ConnectAPI();

    /**
     * Procediment que s'inicialitza al principi de l'aplicació
     * Obre un dialeg per informar a l'usuari
     */
    public void initialize(){

        textFieldCiudad.setText(connectApi.getCity());
        textFieldDies.setText(connectApi.getDies());
        connectApi.mainTemps();
        mediaTempMinimaController.setText(connectApi.getMediaTempMinima());
        mediaTempMaximaController.setText(connectApi.getMediaTempMaxima());

        ObservableList<String> tiemposOL = FXCollections.observableArrayList(connectApi.vectorTemperaturas);

        listViewGeneral.setItems(tiemposOL);
    }


    /**Procediment que s'activa cada cop que
     * l'usuari apreta el botó aceptar.
     * Es conecta a la API amb les dades introduides
     * per l'usuari.
     * Controla errors d'introduccio de dades
     * mitjançant dialog.ERROR
     *
     */
    public void metodoAceptar(){
        //ConnectAPI connectApi = new ConnectAPI();
        connectApi.limpiaListView();
        connectApi.setCity(textFieldCiudad.getText());
        connectApi.setDies(textFieldDies.getText());

        if(!(connectApi.getDies().equals("1")) &&
                !(connectApi.getDies().equals("2")) &&
                !(connectApi.getDies().equals("3")) &&
                !(connectApi.getDies().equals("4")) &&
                !(connectApi.getDies().equals("5")) &&
                !(connectApi.getDies().equals("6")) &&
                !(connectApi.getDies().equals("7")) &&
                !(connectApi.getDies().equals("8")) &&
                !(connectApi.getDies().equals("9")) &&
                !(connectApi.getDies().equals("10")) &&
                !(connectApi.getDies().equals("11")) &&
                !(connectApi.getDies().equals("12")) &&
                !(connectApi.getDies().equals("13")) &&
                !(connectApi.getDies().equals("14")) &&
                !(connectApi.getDies().equals("15")) &&
                !(connectApi.getDies().equals("16"))){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Tipo Incorrecto");
            alert.setHeaderText("El tipo de dato especificado debe ser\nun numero entre 1 y 16");
            alert.setContentText("Introduzca el valor correcto!");

            alert.showAndWait();
        }
        else{
            connectApi.setDies(textFieldDies.getText());
            connectApi.mainTemps();

            mediaTempMinimaController.setText(connectApi.getMediaTempMinima());
            mediaTempMaximaController.setText(connectApi.getMediaTempMaxima());

            ObservableList<String> tiemposOL = FXCollections.observableArrayList(connectApi.vectorTemperaturas);

            listViewGeneral.setItems(tiemposOL);
        }

    }

}