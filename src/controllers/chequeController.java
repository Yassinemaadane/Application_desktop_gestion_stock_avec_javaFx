package controllers;
import models.article;
import models.cheque;
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

import javax.print.attribute.standard.DateTimeAtCreation;

public class chequeController implements Initializable{
	  connexionMysql connectNow= new connexionMysql();
	    Connection connectDB = connectNow.getConnection();
	    public void openAddCheque(MouseEvent event) throws IOException {
		 	   Stage secondStage = new Stage();
		 	Parent root = FXMLLoader.load(getClass().getResource("/templates/Addcheque.fxml"));
		 	 Scene scene = new Scene(root);
		 	 secondStage.setScene(scene);
		 	 secondStage.show();

		  
		  
		 }
	    
	    
	    
	    
	    @FXML
	    private TableColumn<cheque, String> banquecolumn;

	    @FXML
	    private TableColumn<cheque, String> clcolumn;

	    @FXML
	    private TableColumn<cheque, Date> dateccolumn;

	    @FXML
	    private TableColumn<cheque, Date> dateecolumn;

	    @FXML
	    private TableColumn<cheque, Float> mtchcolumn;

	    @FXML
	    private TableColumn<cheque, Float> mtrcolumn;

	    @FXML
	    private TableColumn<cheque, Integer> numccolumn;

	    @FXML
	    private TableColumn<cheque, String> refcolumn;

	    @FXML
	    private TableView<cheque> tableviewcheque;
	    
	    
	    public ObservableList<cheque> data = FXCollections.observableArrayList();

	    public void showCheque() throws SQLException {
	   	 tableviewcheque.getItems().removeAll();
	   	   String client="";
	   	String banque="";
	   	   String sql1="select * from cheque";
	         Statement statement =connectDB.createStatement();
	         ResultSet queryResult =statement.executeQuery(sql1);
	         while (queryResult.next()){
	        	 
	        	 String sql2="select nom  from client where idc ='"+queryResult.getInt("idc")+"'";
		         Statement statement1 =connectDB.createStatement();
		         ResultSet queryResult1 =statement1.executeQuery(sql2);
		         while (queryResult1.next()){
		         client= queryResult1.getString("nom");
		         }
		         
		         
		         String sql3="select nomb  from banque where idb ='"+queryResult.getInt("idb")+"'";
		         Statement statement2 =connectDB.createStatement();
		         ResultSet queryResult2 =statement2.executeQuery(sql3);
		         while (queryResult2.next()){
		         banque= queryResult2.getString("nomb");
		         }
			        data.add(new cheque(queryResult.getString("refch"),queryResult.getDate("datec"),queryResult.getDate("datee"),client,queryResult.getInt("idc"),banque,queryResult.getFloat("mtch"),queryResult.getFloat("mtr")));
  
	         }
	         refcolumn.setCellValueFactory(new PropertyValueFactory<cheque,String>("ref"));

		         banquecolumn.setCellValueFactory(new PropertyValueFactory<cheque,String>("banque"));
		         clcolumn.setCellValueFactory(new PropertyValueFactory<cheque,String>("nclient"));
		         mtchcolumn.setCellValueFactory(new PropertyValueFactory<cheque,Float>("mtch"));
		         mtrcolumn.setCellValueFactory(new PropertyValueFactory<cheque,Float>("mtr"));
			   
		         dateccolumn.setCellValueFactory(new PropertyValueFactory<cheque,Date>("datec"));
		         dateecolumn.setCellValueFactory(new PropertyValueFactory<cheque,Date>("datee"));

			        numccolumn.setCellValueFactory(new PropertyValueFactory<cheque,Integer>("numc"));
			        
			        
			        tableviewcheque.setItems(data);
		         
		         
	        
	         
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			try {
				showCheque();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}}
	    