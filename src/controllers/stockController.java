
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.article;
import models.client;
public class stockController implements Initializable{
	
	
	  connexionMysql connectNow= new connexionMysql();
	    Connection connectDB = connectNow.getConnection();
	    @FXML
	    private Button btnajouter;

	    @FXML
	    private Button btnvalider1;

	    @FXML
	    private Button btnvalider2;


	    @FXML
	    private TextField txtajouter;

	    @FXML
	    private TextField txtrechercher;

	    @FXML
	    private TextField txtstock;
	    @FXML
	    private TableColumn<article, String> designationcolumn;

	    @FXML
	    private TableColumn<article, String> famillecolumn;

	    @FXML
	    private TableColumn<article, String> fournisseurcolumn;

	    @FXML
	    private TableColumn<article, Float> prixacolumn;
	    @FXML
	    private TableColumn<article, Integer> quantitecolumn;

	    @FXML
	    private TableColumn<article, Float> prixvcolumn;

	    @FXML
	    private TableColumn<article, String> referencecolumn;

	    @FXML
	    private TableColumn<article, Float> valeurcolumn;
	    @FXML
	    private ComboBox<String> cbsearch;    
	    @FXML
	    private TextField txtsearch;

	    @FXML
	    private TableView<article> tablearticle;
	    public void showArticle() throws SQLException {
	    	 for ( int i = 0; i<tablearticle.getItems().size(); i++) {
	             tablearticle.getItems().clear();
	         }
			  
			   
			   String sql1="select ref,des,pa,pv,Nom,libelle,enstock,qte from article INNER join fournisseur on fournisseur.code=article.idfr INNER join famille on famille.code=article.idfm where enstock=true";
		        Statement statement =connectDB.createStatement();
		        ResultSet queryResult =statement.executeQuery(sql1);
		        Float valeur=(float) 0;
		        while (queryResult.next()){
		        	   valeur =queryResult.getFloat("pv")*queryResult.getInt("qte");
		    
		        data.add(new article(queryResult.getString("ref"),queryResult.getString("des"),queryResult.getFloat("pa"),queryResult.getFloat("pv"),queryResult.getString("libelle"),queryResult.getString("Nom"),queryResult.getBoolean("enstock"),queryResult.getInt("qte"),valeur));
		        }
		        
		        
		        referencecolumn.setCellValueFactory(new PropertyValueFactory<article,String>("ref"));
		        designationcolumn.setCellValueFactory(new PropertyValueFactory<article,String>("des"));
		        prixvcolumn.setCellValueFactory(new PropertyValueFactory<article,Float>("pv"));
		        valeurcolumn.setCellValueFactory(new PropertyValueFactory<article,Float>("valeur"));
		        quantitecolumn.setCellValueFactory(new PropertyValueFactory<article,Integer>("qte"));
		   
		        famillecolumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
		        fournisseurcolumn.setCellValueFactory(new PropertyValueFactory<article,String>("Nom"));
		        tablearticle.setItems(data);
		        tablearticle.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		   }
	    
	    public ObservableList<article> data = FXCollections.observableArrayList();
	    
	    
	    
	    
	    
 public void remplirRechercher() throws SQLException {
	    	
	    	
	    	
	    	List<String> choix = new ArrayList<String>();		   
		        	choix.add("Fournisseur");
		        	choix.add("Article");
		        	choix.add("Famille");
		        	choix.add("Quantité");
		      
		        	cbsearch.setItems(FXCollections.observableArrayList(choix));
		        	cbsearch.getSelectionModel().select(1);
	      }
	    
public void chercher() throws SQLException {
	for ( int i = 0; i<tablearticle.getItems().size(); i++) {
	    tablearticle.getItems().clear();
	}	
	String ctr="";
	String sql="";
	Integer qtee=0;
	String val=txtsearch.getText();
	ctr=cbsearch.getValue();
	if(ctr=="Fournisseur") {
		sql="select ref,des,pa,pv,Nom,libelle,enstock,qte"
				+ " from article INNER join fournisseur on fournisseur.code=article.idfr"
				+ " INNER join famille on famille.code=article.idfm "
				+ "where enstock=true and fournisseur.Nom='" + val + "'";
	}else if(ctr=="Article" ) {
		sql="select ref,des,pa,pv,Nom,libelle,enstock,qte from article"
				+ " INNER join fournisseur on fournisseur.code=article.idfr "
				+ "INNER join famille on famille.code=article.idfm "
				+ "where enstock=true and ref ='" + val + "'";
	
	}else if(ctr=="Famille") {
		sql="select ref,des,pa,pv,Nom,libelle,enstock,qte"
				+ " from article INNER join fournisseur on fournisseur.code=article.idfr"
				+ " INNER join famille on famille.code=article.idfm "
				+ "where enstock=true and famille.libelle='" + val + "'";
	}else if(ctr=="Quantité") {
		try {
qtee=Integer.parseInt(val);
		}catch(NumberFormatException e) {
			 Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur de saisie", ButtonType.OK);
             alert.showAndWait();
             showArticle();
             
		}
		
		sql="select ref,des,pa,pv,Nom,libelle,enstock,qte from article"
				+ " INNER join fournisseur on fournisseur.code=article.idfr "
				+ "INNER join famille on famille.code=article.idfm "
				+ "where enstock=true and qte >='" + qtee + "'";
	}else {
		System.out.println("Err");
	}
	Statement statement =connectDB.createStatement();
    ResultSet queryResult =statement.executeQuery(sql);
    Float valeur=(float) 0;
    while (queryResult.next()){
    	   valeur =queryResult.getFloat("pv")*queryResult.getInt("qte");

    data1.add(new article(queryResult.getString("ref"),queryResult.getString("des"),queryResult.getFloat("pa"),queryResult.getFloat("pv"),queryResult.getString("libelle"),queryResult.getString("Nom"),queryResult.getBoolean("enstock"),queryResult.getInt("qte"),valeur));
    }
    
    
    referencecolumn.setCellValueFactory(new PropertyValueFactory<article,String>("ref"));
    designationcolumn.setCellValueFactory(new PropertyValueFactory<article,String>("des"));
    prixvcolumn.setCellValueFactory(new PropertyValueFactory<article,Float>("pv"));
    valeurcolumn.setCellValueFactory(new PropertyValueFactory<article,Float>("valeur"));
    quantitecolumn.setCellValueFactory(new PropertyValueFactory<article,Integer>("qte"));
    famillecolumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
    fournisseurcolumn.setCellValueFactory(new PropertyValueFactory<article,String>("Nom"));
    tablearticle.setItems(data1);
   
}

public void chercherStock() throws SQLException, IOException {
    article a = tablearticle.getSelectionModel().getSelectedItem();

   
if((a instanceof article)==true) {
	
	
	
	
	
	String ref=a.getRef();
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/miseStock.fxml"));
	Parent root = loader.load();
	miseStockController stcont = loader.getController();
	stcont.receiveData(ref);
	// Scene scene = new Scene(root);
	 Stage secondStage = new Stage();	
	 secondStage.setScene(new Scene(root));
	 secondStage.initModality(Modality.APPLICATION_MODAL);
	 secondStage.setResizable(false);
	 secondStage.showAndWait();
	
	
	
	
	
	
	
	
	
}  else {
	System.out.println("false");
}

    /*
	String valeur =txtrechercher.getText();
	String sql="select ref,des from article where ref'" + valeur + "'";;
	Statement statement =connectDB.createStatement();
    ResultSet queryResult =statement.executeQuery(sql);
    while (queryResult.next()){
    	
    }*/
    
}


public void deleteFromStock() {
article a = tablearticle.getSelectionModel().getSelectedItem();

if((a instanceof article)==true) {
	
	
	
	
	Integer etat=0;
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

public ObservableList<article> data1 = FXCollections.observableArrayList();

public void openAddAuStock() throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/AjouterAuStock.fxml"));
	Parent root = loader.load();

	// Scene scene = new Scene(root);
	 Stage secondStage = new Stage();	
	 secondStage.setScene(new Scene(root));
	 secondStage.initModality(Modality.APPLICATION_MODAL);
	 secondStage.setResizable(false);
	 secondStage.showAndWait();
}




		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			try {
				remplirRechercher();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		
		try {
			showArticle();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}}
	    