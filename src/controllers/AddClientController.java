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

public class AddClientController implements Initializable{
	  connexionMysql connectNow= new connexionMysql();
	    Connection connectDB = connectNow.getConnection();
	    @FXML
	    private CheckBox togglefid;
	    @FXML
	    private CheckBox togglecrd;
	    
	    @FXML
	    private TextField prenomfield;
	    @FXML
	    private TextField cinfield;
	    @FXML
	    private DatePicker datecreation;
	    @FXML
	    private TextField nomfield;
	    @FXML
	    private TextField numfield;
	    @FXML
	    private TextField adressefield;
	    @FXML
	    private TextField idcfield;
	    @FXML
	    private TextField rechechertxf;

	    @FXML
	    private Button rechercherbtn;
	    @FXML
	    private Button btnnouveau;
	    @FXML
	    private Button btnquitter;

	    
	    
	    public void addClient(){    

	        String nom = nomfield.getText();
	        String prenom = prenomfield.getText();
	        String cin = cinfield.getText();
	        String adresse = adressefield.getText();
	        String numm= numfield.getText();
	        Integer numc = Integer.parseInt(numm);
	        Integer fid=0;
	        Integer crd=0;
	        LocalDate datee = datecreation.getValue();
	        String datecreation=datee.toString();
	        if(togglefid.isSelected()) {
	        fid=1;
	        	
	        }
	        else 
	        {
	        	System.out.println("false");
	        }
	        if(togglecrd.isSelected()) {
	            crd=1;
	            	
	            }
	            else 
	            {
	            	System.out.println("false");
	            }
	        
	        String sqladd="insert into client(cin,nom,prenom,adresse,numc,fid,crd,datecreation) values('"+cin+"','"+nom+"','"+prenom+"','"+adresse+"','"+numc+"','"+fid+"','"+crd+"','"+datecreation+"')";
	        try {
	            //Statement statement =connectDB.createStatement();
	            Statement st =connectDB.prepareStatement(sqladd);
	            st.executeUpdate(sqladd);
	            Alert alert =new Alert(Alert.AlertType.CONFIRMATION,"client ajouté",ButtonType.OK);
	    alert.showAndWait();
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	            
	        }


	    }

	  public void quitter() {
		  Stage secondStage=(Stage) btnquitter.getScene().getWindow();
		  secondStage.close();
		/*	 secondStage.setOnCloseRequest ( event -> {
				 clientController clientController = loader.getController;
			 } );
*/
	  }  
	  
	  
	  
	  public void showInfo(String ref) throws SQLException {
		  String sql1="select * from client where cin= '" + ref + "'";;
	        Statement statement = connectDB.createStatement();
	        ResultSet queryResult =statement.executeQuery(sql1);
	        while (queryResult.next()){
idcfield.setText(queryResult.getString("idc"));
cinfield.setText(queryResult.getString("cin"));
prenomfield.setText(queryResult.getString("prenom"));
nomfield.setText(queryResult.getString("nom"));
adressefield.setText(queryResult.getString("adresse"));
numfield.setText(queryResult.getString("numc"));
datecreation.setPromptText(queryResult.getString("datecreation"));
Integer crd=queryResult.getInt("crd");	
Integer fid=queryResult.getInt("fid");	
if (crd==1) {
	togglecrd.setSelected(true);
	
}
if(fid==1) {
	togglefid.setSelected(true);
}

	        	
	        	

	        	
	        	
	        }
	  }
	   
	  
	  public void updateClient() {
		  
	  }
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			
		}}