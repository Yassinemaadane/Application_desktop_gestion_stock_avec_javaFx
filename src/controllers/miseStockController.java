
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.article;
public class miseStockController implements Initializable{

    @FXML
    private TextField txtstock;
    
    @FXML
    private Button btnajouterstock;

    @FXML
    private Button btnquitter;

    @FXML
    private Button btnvalider;

    @FXML
    private FontAwesomeIconView iconsearch;

    @FXML
    private Label labeldes;

    @FXML
    private TextField txtajouterstock;

    @FXML
    private TextField txtrechercher;


	
	  connexionMysql connectNow= new connexionMysql();
	    Connection connectDB = connectNow.getConnection();
	    
	    
	    public void receiveData(String ref) throws SQLException {
	    	
	    	   String sql1="select * from article where ref= '" + ref + "'";;
		        Statement statement = connectDB.createStatement();
		        ResultSet queryResult =statement.executeQuery(sql1);
		        while (queryResult.next()){
		        	labeldes.setText(queryResult.getString("des"));
		        	txtrechercher.setText(queryResult.getString("ref"));
		        	txtstock.setText(queryResult.getString("qte"));
		        }
	    	
	    }
	    
	    
	    
	    public void updateStock() throws SQLException {
	    	String ref = txtrechercher.getText();
            String stockdipsonible =txtstock.getText();
            Integer sd=Integer.parseInt(stockdipsonible);
	    	String stockentree =txtajouterstock.getText();
	    	Integer se =Integer.parseInt(stockentree);
	    	Integer totale=se+sd;
	    	if(totale<0) {
	    		 Alert alert =new Alert(Alert.AlertType.ERROR,"Stock indisponible",ButtonType.OK);
				    alert.showAndWait();
	    	}else {
	    		
				try {
					String sql ="update article set qte='"+totale+"'where ref ='"+ref+"'";
					Statement	statement = connectDB.createStatement(); 
					 statement.executeUpdate(sql);
					 Alert alert =new Alert(Alert.AlertType.CONFIRMATION,"Operation effectuée",ButtonType.OK);
					    alert.showAndWait();
				} catch (SQLException e) {
					 Alert alert =new Alert(Alert.AlertType.ERROR,"Operation echouée",ButtonType.OK);
					    alert.showAndWait();
					
				}
			       
				receiveData(ref);
	    		
	    		
	    	}
		        
	    	
	    	
	    }
	    
	    

	    
	    
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}}

