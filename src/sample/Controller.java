package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class Controller {

    ObservableList<Object> tiemposOL = FXCollections.observableArrayList(
            "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");

    ListView<Object> list = new ListView<Object>(tiemposOL);

    public void inicialize(){
        list.setItems(tiemposOL);
    }

}
