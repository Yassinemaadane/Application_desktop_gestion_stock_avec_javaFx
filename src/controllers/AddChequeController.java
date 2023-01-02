package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.article;

public class AddChequeController implements Initializable{
	  connexionMysql connectNow= new connexionMysql();
	    Connection connectDB = connectNow.getConnection();
	    
	    @FXML
	    private Button btnajouterfm;

	    @FXML
	    private Button btnajouterfr;

	    @FXML
	    private Button btnquitter;

	    @FXML
	    private Button btnvalider;

	    @FXML
	    private ComboBox<String> cbbanque;

	    @FXML
	    private ComboBox<String> cbclient;

	    @FXML
	    private DatePicker datec;

	    @FXML
	    private DatePicker datee;

	    @FXML
	    private TextField prixafield;

	    @FXML
	    private TextField referencefield;

	    
	    
	   
	    
	    
	    public void remplirBanques() throws SQLException {
	    	
	    	
	    	String sql = "select nomb from banque";
	    	List<String> banques = new ArrayList<String>();
	    	Statement statement = connectDB.createStatement();
	        ResultSet queryResult =statement.executeQuery(sql);
	            while (queryResult.next()){
	            	banques.add(queryResult.getString("nomb"));
	            }
	            cbbanque.setItems(FXCollections.observableArrayList(banques));
	            System.out.println(banques);
	            }
	    
	    
	    
 public void remplirClients() throws SQLException {
	    	
	    	
	    	String sql = "select nom from client";
	    	List<String> clients = new ArrayList<String>();
	    	Statement statement = connectDB.createStatement();
	        ResultSet queryResult =statement.executeQuery(sql);
	            while (queryResult.next()){
	            	clients.add(queryResult.getString("nom"));
	            }
	            cbclient.setItems(FXCollections.observableArrayList(clients));
	            }
 
 
 
 public void addCheque() throws SQLException{    

  
     
     LocalDate datecc = datec.getValue();
     LocalDate dateee = datee.getValue();
     String ref= referencefield.getText();
     String banque =cbbanque.getValue();
     String client = cbclient.getValue();
     String p= prixafield.getText();
     Float mt=Float.valueOf(p);
     Float mtr=mt;
     Integer idb=0;
     Integer idc=0;
     
     String sqlGetbanque="select idb from banque where nomb='"+banque+"'";
     Statement statement1 = connectDB.createStatement();
     ResultSet queryResult1 =statement1.executeQuery(sqlGetbanque);
         while (queryResult1.next()){
         	idb=queryResult1.getInt("idb");
         	
         }
         
         
         String sqlGetClient="select idc from client where nom='"+client+"'";
         Statement statement2 = connectDB.createStatement();
         ResultSet queryResult2 =statement2.executeQuery(sqlGetClient);
             while (queryResult2.next()){
             	idc=queryResult2.getInt("idc");
             	
             }

             
            		 
            		 
            String sqlInsertCheque= "insert into cheque(refch,datec,mtch,mtr,datee,idc,idb) values('"+ref+"','"+datecc+"','"+mt+"','"+mtr+"','"+dateee+"','"+idc+"','"+idb+"')";
			   
		        //Statement statement =connectDB.createStatement();
		        Statement st =connectDB.prepareStatement(sqlInsertCheque);
		        st.executeUpdate(sqlInsertCheque);
		        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Le cheque dont la référence est : "+ref+" est crée avec succes", ButtonType.OK);
	             alert.showAndWait();
		        		
   
    


 }
 
 

 
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {

			
			try {
				remplirBanques();
				remplirClients();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

}
