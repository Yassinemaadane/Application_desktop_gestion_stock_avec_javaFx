package controllers;
import models.client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.print.attribute.standard.DateTimeAtCreation;

public class fournisseurController implements Initializable{
	  connexionMysql connectNow= new connexionMysql();
	    Connection connectDB = connectNow.getConnection();
	    
	    
	    
	    
	    
	    public void openAddFournisseur() throws IOException {
	    	Stage secondStage = new Stage();	
	    	Parent root = FXMLLoader.load(getClass().getResource("/templates/AddFournisseur.fxml"));
	    	 Scene scene = new Scene(root);
	    	 
	    	 secondStage.setScene(scene);
	    	 secondStage.initModality(Modality.APPLICATION_MODAL);
	    	 secondStage.setResizable(false);
	    	 secondStage.showAndWait();
	    	secondStage.setOnCloseRequest ( event -> {
	    		System.out.println("test");
	     } );

	    }  	
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			
		}


}
