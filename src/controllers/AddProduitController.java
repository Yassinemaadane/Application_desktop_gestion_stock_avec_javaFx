package controllers;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class AddProduitController implements Initializable{
	
	
	  connexionMysql connectNow= new connexionMysql();
	    Connection connectDB = connectNow.getConnection();
	    
	    
	    @FXML
	    private ComboBox<String> cbtest;  
	    @FXML
	    private ComboBox<String> cmcodefr; 
	    @FXML
	    private ComboBox<String> cbfamille;  
	    @FXML
	    private ComboBox<String> cbfournisseur;
	    @FXML
	    private TextField designationfield;

	    @FXML
	    private TextField prixafield;

	    @FXML
	    private TextField prixvfield;

	    @FXML
	    private TextField referencefield;
	    @FXML
	    private Button btnquitter;
	    


public void remplirFamille() throws SQLException {
	
	
	String sql = "select libelle from famille";
	List<String> familles = new ArrayList<String>();
	Statement statement = connectDB.createStatement();
    ResultSet queryResult =statement.executeQuery(sql);
        while (queryResult.next()){
        	familles.add(queryResult.getString("libelle"));
        }
        cbfamille.setItems(FXCollections.observableArrayList(familles));
        System.out.println(familles);
        }

  
public void remplirFournisseur() throws SQLException {
	
	
	String sql = "select code,nom from fournisseur";
	List<String> fournisseurs = new ArrayList<String>();
	Statement statement = connectDB.createStatement();
    ResultSet queryResult =statement.executeQuery(sql);
        while (queryResult.next()){
        	fournisseurs.add(queryResult.getString("nom"));
        }
        cbfournisseur.setItems(FXCollections.observableArrayList(fournisseurs));
     
        }


public void addArticle() throws SQLException {
	String ref=referencefield.getText();
	String des=designationfield.getText();
	String paa=prixafield.getText();
	String pvv=prixvfield.getText();
	Float pa;
	Float pv;;
	if(paa.trim().isEmpty()) 
	{
		pa=(float) 0;
	}else {
     pa = Float.parseFloat(paa);
    }
	if(pvv.trim().isEmpty()) 
	{
		pv=(float) 0;
	}else {
     pv = Float.parseFloat(pvv);
    }
    
    String idfr="";
    Integer idfm=0;
    
        
        
        if(referencefield.getText().trim().isEmpty()) {
        	 Alert alert = new Alert(Alert.AlertType.ERROR, "Vous n'avez pas saisi la reference de l'article", ButtonType.OK);
             alert.showAndWait();
        }else {
        	String fournisseur=cbfournisseur.getValue();
            String famille=cbfamille.getValue();
            
            String sqlgetfournisseur = "select code from fournisseur where nom ='"+fournisseur+"'";
            Statement statement = connectDB.createStatement();
            ResultSet queryResult =statement.executeQuery(sqlgetfournisseur);
                while (queryResult.next()){
                	idfr=queryResult.getString("code");
                }
            String sqlgetfamille = "select code from famille where libelle ='"+famille+"'";
            Statement statement1 = connectDB.createStatement();
            ResultSet queryResult1 =statement1.executeQuery(sqlgetfamille);
                while (queryResult1.next()){
                	idfm=queryResult1.getInt("code");
                	
                }
                String sqlverification = "select ref from article where ref ='"+ref+"'";
                Statement statement2 = connectDB.createStatement();
                ResultSet queryResult2 =statement2.executeQuery(sqlverification);
                int cmpt=0;
                    while (queryResult2.next()){
                    	cmpt++;
                    	
                    }
                  if(cmpt>0) {
                	  Alert alert = new Alert(Alert.AlertType.ERROR, "la reference entrée existe déja", ButtonType.OK);
                      alert.showAndWait();
                  }else if(cmpt==0) {
                PreparedStatement st = connectDB.prepareStatement("insert into article(ref, des, pa, pv,idfr,idfm) values('" + ref + "','" + des + "','" + pa + "','" + pv + "','" + idfr + "','" + idfm + "');");
                st.executeUpdate();
                referencefield.setText("");
                designationfield.setText("");
                cbfamille.setValue("");
                cbfournisseur.setValue("");
                prixafield.setText("");
                prixvfield.setText("");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Article Ajouté", ButtonType.OK);
                alert.showAndWait();
                
        }}

	
	
	
	
	
}


public void openFamille() throws IOException {
	Stage secondStage = new Stage();
	
	
	
	Parent root = FXMLLoader.load(getClass().getResource("/templates/AddFamille.fxml"));
	 Scene scene = new Scene(root);
	 
	 secondStage.setScene(scene);
	 secondStage.initModality(Modality.APPLICATION_MODAL);
	 secondStage.showAndWait();

}
public void openFournisseur() throws IOException {
	Stage secondStage = new Stage();
	
	
	
	Parent root = FXMLLoader.load(getClass().getResource("/templates/AddFournisseur.fxml"));
	 Scene scene = new Scene(root);
	 
	 secondStage.setScene(scene);
	 secondStage.initModality(Modality.APPLICATION_MODAL);
	 secondStage.showAndWait();

}


public void closeAddArticle() {
	 Stage stage = (Stage) btnquitter.getScene().getWindow();
	  stage.close();
}



@Override
public void initialize(URL arg0, ResourceBundle arg1) {
try {
	remplirFamille();
	remplirFournisseur();


} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}


}	