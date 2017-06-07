package it.polito.tdp.porto;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import it.polito.tdp.porto.model.Paper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
    Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<Author> boxSecondo;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCoautori(ActionEvent event) {
    	txtResult.clear();
      	txtResult.setText("Coautori:\n");
    List<Author> l = model.trovaCoautori(boxPrimo.getValue());
    for(Author a  : l)
    txtResult.appendText(a.toString());
    
    boxSecondo.getItems().clear();
    boxSecondo.getItems().addAll(model.popolaSecondaTendina(boxPrimo.getValue()));
    
    }
    
    
    @FXML
    void handleSequenza(ActionEvent event) {
      	txtResult.clear();
      	txtResult.setText("Articoli che compongono il cammino:\n");
     	List<Paper> l = model.cammino(boxPrimo.getValue(), boxSecondo.getValue());
     	for(Paper p : l )
      	txtResult.appendText(p.toString());
    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }

	public void setModel(Model model2) {
		model= model2;
		model.popola();
		
		boxPrimo.getItems().addAll(model.getAutori());
	
		
		
	}
}
