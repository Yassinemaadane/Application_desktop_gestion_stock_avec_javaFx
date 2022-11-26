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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.article;
public class AddAuStock implements Initializable{
	
	
	  connexionMysql connectNow= new connexionMysql();
	    Connection connectDB = connectNow.getConnection();
	    
	    
	    @FXML
	    private TableColumn<article, String> designationcolumn;

	    @FXML
	    private TableColumn<article, String> famillecolumn;

	    @FXML
	    private TableColumn<article, String> fournisseurcolumn;

	    @FXML
	    private TableColumn<article, Float> prixacolumn;

	    @FXML
	    private TableColumn<article, Float> prixvcolumn;

	    @FXML
	    private TableColumn<article, String> referencecolumn;
	    @FXML
	    private TableColumn<article, Boolean> enstockcolumn;
	    @FXML
	    private TableColumn<article, Integer> quantitecolumn;

	    @FXML
	    private TableView<article> tablearticle;
	    
		   public void showArticle() throws SQLException {
			   
			   for ( int i = 0; i<tablearticle.getItems().size(); i++) {
			         tablearticle.getItems().clear();
			     }
			   
			   String sql1="select ref,des,pa,pv,Nom,libelle,enstock,qte from article INNER join fournisseur on fournisseur.code=article.idfr INNER join famille on famille.code=article.idfm where enstock =0 ";
		        Statement statement =connectDB.createStatement();
		        ResultSet queryResult =statement.executeQuery(sql1);
		        Float valeur =(float) 0;
		        while (queryResult.next()){
		        data.add(new article(queryResult.getString("ref"),queryResult.getString("des"),queryResult.getFloat("pa"),queryResult.getFloat("pv"),queryResult.getString("libelle"),queryResult.getString("Nom"),queryResult.getBoolean("enstock"),queryResult.getInt("qte"),valeur));
		        System.out.println(queryResult.getString("libelle"));
		        }
		        
		        
		        referencecolumn.setCellValueFactory(new PropertyValueFactory<article,String>("ref"));
		        designationcolumn.setCellValueFactory(new PropertyValueFactory<article,String>("des"));
		        prixacolumn.setCellValueFactory(new PropertyValueFactory<article,Float>("pa"));
		        prixvcolumn.setCellValueFactory(new PropertyValueFactory<article,Float>("pv"));
		   
		        famillecolumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
		        fournisseurcolumn.setCellValueFactory(new PropertyValueFactory<article,String>("Nom"));
		        quantitecolumn.setCellValueFactory(new PropertyValueFactory<article,Integer>("qte"));
		        
		        
		        tablearticle.setItems(data);
			   
		   }
		   public ObservableList<article> data = FXCollections.observableArrayList();
		   
		   
		   
		   
		   
		   
		   
		   public void AddSelected() {
			   article a = tablearticle.getSelectionModel().getSelectedItem();

			   if((a instanceof article)==true) {
			   	
			   	
			   	
			   	
			   	Integer etat=1;
			   	String ref=a.getRef();
			   	String sql ="update article set enstock='"+etat+"'where ref ='"+ref+"'";
			   	Statement statement;
			   	try {
			   		statement = connectDB.createStatement();
			   		statement.executeUpdate(sql);
			   	 Alert alert =new Alert(Alert.AlertType.CONFIRMATION,"Operation effectuée",ButtonType.OK);
			   	    alert.showAndWait();
			   	    showArticle();
			   	} catch (SQLException e) {
			   		// TODO Auto-generated catch block
			   		Alert alert =new Alert(Alert.AlertType.ERROR,"Operation echouée",ButtonType.OK);
			   		alert.setHeaderText("Erreur de supprimer l'article du stock");
			   	    alert.showAndWait();
			   	} 
			   	
			   }

			   }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			try {
				showArticle();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		


}
