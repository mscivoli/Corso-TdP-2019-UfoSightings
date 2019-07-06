/**
 * Sample Skeleton for 'Ufo.fxml' Controller Class
 */

package it.polito.tdp.ufo;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.ufo.db.Intestazione;
import it.polito.tdp.ufo.db.SightingsDAO;
import it.polito.tdp.ufo.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class UfoController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ComboBox<Intestazione> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxStato"
    private ComboBox<String> boxStato; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

	private Model model;

    @FXML
    void handleAnalizza(ActionEvent event) {
    	
    	


    }

    @FXML
    void handleAvvistamenti(ActionEvent event) {
    	
    	if(boxAnno!=null) {
    		int anno = boxAnno.getValue().getAnno();
    		model.creaGrafo(anno);
    		txtResult.appendText("GRAFO CREATO");
    		boxStato.getItems().clear();
    		boxStato.getItems().addAll(model.getGrado().vertexSet());
    	}

    }

    @FXML
    void handleSequenza(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Ufo.fxml'.";
        assert boxStato != null : "fx:id=\"boxStato\" was not injected: check your FXML file 'Ufo.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Ufo.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		SightingsDAO dao = new SightingsDAO();
		
		boxAnno.getItems().clear();
		boxAnno.getItems().addAll(dao.getIntestazione());
		
	}
}
