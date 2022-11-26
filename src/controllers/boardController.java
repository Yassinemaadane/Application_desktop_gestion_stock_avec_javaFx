package controllers;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class boardController implements Initializable { 
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
	@FXML
private Label today;
	private Parent fxml;
    @FXML
private Button btngestion;

public void openClient(MouseEvent event) throws IOException {
	   Stage secondStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/templates/test.fxml"));
	 Scene scene = new Scene(root);
	 secondStage.setScene(scene);
	 secondStage.initModality(Modality.APPLICATION_MODAL);
	 secondStage.showAndWait();

       
       
}
   
public void openProduit(MouseEvent event) throws IOException {
	   Stage secondStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/templates/produit.fxml"));
	 Scene scene = new Scene(root);
	 secondStage.setScene(scene);
	 secondStage.show();

 
 
}



public void openFournisseur(MouseEvent event) throws IOException {
	   Stage secondStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/templates/fournisseur.fxml"));
	 Scene scene = new Scene(root);
	 secondStage.setScene(scene);
	 secondStage.show();

    
    
}


public void openFacture(MouseEvent event) throws IOException {
	   Stage secondStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/templates/facture.fxml"));
	 Scene scene = new Scene(root);
	 secondStage.setScene(scene);
	 secondStage.show();

 
 
}

public void openStock(MouseEvent event) throws IOException {
	   Stage secondStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/templates/stock.fxml"));
	 Scene scene = new Scene(root);
	 secondStage.setScene(scene);
	 secondStage.initModality(Modality.APPLICATION_MODAL);
	 secondStage.showAndWait();

    
    
}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		   today.setText(dtf.format(now));
	}}
