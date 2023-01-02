package controllers;
import models.article;
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
import javafx.scene.input.MouseEvent;
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
import models.facture;

import javax.print.attribute.standard.DateTimeAtCreation;

public class documentController implements Initializable{
	  connexionMysql connectNow= new connexionMysql();
	    Connection connectDB = connectNow.getConnection();
	    
	    @FXML
	    private Button btnimprimer;

	    @FXML
	    private Button btnnouveau;

	    @FXML
	    private Button btnouvrir;

	    @FXML
	    private Button btnquitter;

	    @FXML
	    private Button btnregl;

	    @FXML
	    private Button btnvalider;

	    @FXML
	    private TableColumn<facture, String> clcolumn;
	    

	    @FXML
	    private TableColumn<facture, String> dccolumn;

	    @FXML
	    private TableView<facture> facturetable;

	    @FXML
	    private TableColumn<facture, String> modecolumn;

	    @FXML
	    private TableColumn<facture, String> numccolumn;

	    @FXML
	    private TableColumn<facture, String> refcolumn;

	    @FXML
	    private TableColumn<facture, Float> restecolumn;

	    @FXML
	    private TableColumn<facture, String> statutcolumn;

	    @FXML
	    private TableColumn<facture, Float> trcolumn;

	    @FXML
	    private TableColumn<facture, Float> ttccolumn;

	

	    
		public ObservableList<facture> data = FXCollections.observableArrayList();
	    public void showFacture() throws SQLException {
	    	String sql="SELECT *,nom FROM facture inner join client on facture.idc=client.idc";
	    	
	    	  Statement statement =connectDB.createStatement();
		        ResultSet queryResult =statement.executeQuery(sql);
		        while (queryResult.next()){
		       data.add(new facture(queryResult.getString("reff"),queryResult.getString("datef"),queryResult.getString("nom"),queryResult.getString("idc"),queryResult.getFloat("total"),queryResult.getFloat("totaldu"),queryResult.getFloat("totalr"),queryResult.getString("typer"),queryResult.getString("statut")));
		        }
			   
		        refcolumn.setCellValueFactory(new PropertyValueFactory<facture,String>("ref"));
			    clcolumn.setCellValueFactory(new PropertyValueFactory<facture,String>("client"));
			    dccolumn.setCellValueFactory(new PropertyValueFactory<facture,String>("dc"));
			    modecolumn.setCellValueFactory(new PropertyValueFactory<facture,String>("mode"));
			    numccolumn.setCellValueFactory(new PropertyValueFactory<facture,String>("numc"));
		   
			    restecolumn.setCellValueFactory(new PropertyValueFactory<facture,Float>("reste"));
			    statutcolumn.setCellValueFactory(new PropertyValueFactory<facture,String>("status"));
			    trcolumn.setCellValueFactory(new PropertyValueFactory<facture,Float>("totalr"));
			    ttccolumn.setCellValueFactory(new PropertyValueFactory<facture,Float>("ttc"));
		        
		        facturetable.setItems(data);
	    }	    
	    public void openFacture(MouseEvent event) throws IOException {
	 	   Stage secondStage = new Stage();
	 	Parent root = FXMLLoader.load(getClass().getResource("/templates/facture.fxml"));
	 	 Scene scene = new Scene(root);
	 	 secondStage.setScene(scene);
	 	 secondStage.show();

	  
	  
	 }
	    
	   
	    
	    
	    public void openReglement(MouseEvent event) throws IOException {
	        facture a = facturetable.getSelectionModel().getSelectedItem();
	        if((a instanceof facture)==true) {
	        
	       
			 Stage secondStage = new Stage();

	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/Reglement.fxml"));
			Parent root = loader.load();	
			Scene scene = new Scene(root);
		 	 ReglementController stcont = loader.getController();
				stcont.Data(a);
				 
				 secondStage.setScene(scene);
				 secondStage.setTitle("Règlement sur la facture "+a.getRef());
				 secondStage.show();

	        }
		  
		 }
		 
	 
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
try {
	showFacture();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}			
		}}