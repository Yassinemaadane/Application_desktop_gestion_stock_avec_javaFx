package controllers;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.facture;
public class ReglementController implements Initializable{
	  @FXML
	    private Label tfTTC;
	  
	  @FXML
	    private Label labelreste;
	  
	   @FXML
	    private TextField txmt;

	    @FXML
	    private Label txdr;

	    @FXML
	    private TextField txref;

	    @FXML
	    private Label txrr;
    

    @FXML
    private Label clientlabel;
    
    @FXML
    private ComboBox<String> cbp;
	
	  connexionMysql connectNow= new connexionMysql();
	    Connection connectDB = connectNow.getConnection();
	    
	    public void Data(facture a) {
			txref.setText("Règlement sur la facture "+a.getRef());
			clientlabel.setText(a.getClient());
			tfTTC.setText(String.valueOf(a.getTtc())+" DH");
			txdr.setText(String.valueOf(a.getTotalr())+" DH");
			txrr.setText(String.valueOf(a.getReste())+" DH");
			txmt.setText(String.valueOf(a.getReste()));
	    }
	    
	    
	    public void remplirPaiement() {

	    	List<String> choix = new ArrayList<String>();		   
		        	choix.add("Espèce");
		        	choix.add("Chèque");
		        	choix.add("Crédit");
		        	choix.add("Virement");
		      
		        	cbp.setItems(FXCollections.observableArrayList(choix));
		        	cbp.getSelectionModel().select(0);
	    }
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			remplirPaiement();
			
		}

}
