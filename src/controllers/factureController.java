package controllers;

import models.client;
import models.commande;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class factureController implements Initializable{
	  connexionMysql connectNow= new connexionMysql();
	    Connection connectDB = connectNow.getConnection();
	    @FXML
	    private Button btnenregistrer;

	    @FXML
	    private Button btnvaliderfacture;

	    @FXML
	    private ComboBox<String> cbremise;
	    @FXML
	    private ComboBox<String> cbtva;

	    @FXML
	    private CheckBox chekremise;
	    @FXML
	    private TextField txfdes;
	    @FXML
	    private Label labelqd;
	    @FXML
	    private TextField txfprix;

	    @FXML
	    private TextField txfqte;

	    @FXML
	    private TextField txfref;

	    @FXML
	    private TextField txfremise;

	
	    @FXML
	    private ComboBox<String> cbclient;

	    @FXML
	    private ComboBox<String> cbrecherche;

	    @FXML
	    private ComboBox<String> cbreference;

	    @FXML
	    private ComboBox<String> cbstatut;

	    @FXML
	    private DatePicker datef;
	    @FXML
	    private TextField txtlibf;

	    @FXML
	    private TextField txtrf;
	  

	    @FXML
	    private TableColumn<commande,String > designationcolumn;
	    @FXML
	    private TableColumn<commande, Float> mhtcolumn;

	    @FXML
	    private TableColumn<commande, Float> mrcolumn;

	    @FXML
	    private TableColumn<commande, Float> mttccolumn;

	    @FXML
	    private TableColumn<commande, Float> mtvacolumn;

	    @FXML
	    private TableColumn<commande, Float> prixcolumn;

	    @FXML
	    private TableColumn<commande, Integer> quantitecolumn;

	    @FXML
	    private TableColumn<commande, String> referencecolumn;

	    @FXML
	    private TableView<commande> tablearticle;
		public ObservableList<commande> dataCommande = FXCollections.observableArrayList();

	    public void showCommande() throws SQLException {
	    	 for ( int i = 0; i<tablearticle.getItems().size(); i++) {
	             tablearticle.getItems().clear();
	         }
	    	  String refA,des = null;
	  	 
			Float mht,mtva,mr,mttc,pht = null;
	  	    Integer qte;
	    	
	    	 String sql1="select * from commande where refF='"+txtrf.getText()+"'";
		        Statement statement = connectDB.createStatement();
		        ResultSet queryResult =statement.executeQuery(sql1);
		        while (queryResult.next()){
refA=queryResult.getString("refA");
String sql2="select * from article where ref='"+refA+"'";
Statement statement1 = connectDB.createStatement();
ResultSet queryResult1 =statement1.executeQuery(sql2);
while (queryResult1.next()){
	des=queryResult1.getString("des");
	pht=queryResult1.getFloat("pv");
	
}
qte=queryResult.getInt("qte");
mht=queryResult.getFloat("toHT");
mtva=queryResult.getFloat("toTVA");
mr=queryResult.getFloat("toR");
mttc=mht-mr+mtva;

 dataCommande.add(new commande(refA,des,pht,mht,mtva,mr,mttc,qte));


		        }
		       
		        referencecolumn.setCellValueFactory(new PropertyValueFactory<commande,String>("refA"));
		        designationcolumn.setCellValueFactory(new PropertyValueFactory<commande,String>("des"));
		        mhtcolumn.setCellValueFactory(new PropertyValueFactory<commande,Float>("mht"));
		        mrcolumn.setCellValueFactory(new PropertyValueFactory<commande,Float>("mr"));
		        mttccolumn.setCellValueFactory(new PropertyValueFactory<commande,Float>("mttc"));
		        mtvacolumn.setCellValueFactory(new PropertyValueFactory<commande,Float>("mtva"));
		        prixcolumn.setCellValueFactory(new PropertyValueFactory<commande,Float>("pht"));
		        quantitecolumn.setCellValueFactory(new PropertyValueFactory<commande,Integer>("qte"));
		        
		       

		        tablearticle.setItems(dataCommande);
		        
	    	
	    	
	    	
	    }
	    
	    
	    public void commande() {
	    	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
	    	   LocalDateTime date = LocalDateTime.now(); //datec
	    	   String datec=date.toString();
	    	   String p = txfprix.getText();
	    	
	    	   String q= txfqte.getText();
	    	   Integer qte=Integer.parseInt(q);//qte
	    	   Float prix=Float.parseFloat(p);
	    	   Float toHT= prix*qte;//toHT
	    	   String refF=txtrf.getText();//refF
	    	   String refA = txfref.getText();//refA
	    	   Float remise=(float) 0;
	    	   Integer pr=0;
	    	   Float toTva=(float) 0;
	    	   String rr=txfremise.getText();
	    	   
	    	   //remise
	    	   if(chekremise.isSelected()) {
	    		   if(cbremise.getValue()=="Par montant") {
	    			   remise=Float.parseFloat(rr);//remise
	    			   
	    		   }else if(cbremise.getValue()=="Par pourcentage") {
	    			   
	    			  pr=Integer.parseInt(rr);
	    			  remise = (toHT*pr)/100;//remise
	    			  
	    		   }
	    	   }
	    	   else {
	    		   remise =(float) 0;
	    	   }
	    	   //tva
	    	   if(cbtva.getValue()=="0%") {
	    		   toTva=(float) 0;
	    	   }
	    	   else if(cbtva.getValue()=="7%") {
	    		   toTva=((toHT-remise)*7)/100;
	    	   }
	    	   else if(cbtva.getValue()=="10%") {
	    		   toTva=((toHT-remise)*10)/100;
	    	   }
	    	   else if(cbtva.getValue()=="14%") {
	    		   toTva=((toHT-remise)*14)/100;}
	    	   else if(cbtva.getValue()=="20%") {
	    		   toTva=((toHT-remise)*20)/100;
	    	   }
	    	   
	    	   
	    	   String sql="insert into commande( datec, qte, toHT, toTVA, toR, refA, refF)values ('"+datec+"','"+qte+"','"+toHT+"','"+toTva+"','"+remise+"','"+refA+"','"+refF+"')";
	    	   
	    	   try {
		            //Statement statement =connectDB.createStatement();
		            Statement st =connectDB.prepareStatement(sql);
		            st.executeUpdate(sql);
		            Alert alert =new Alert(Alert.AlertType.CONFIRMATION,"succes",ButtonType.OK);
		            showCommande();
		    alert.showAndWait();
		        } catch (SQLException e) {
		            throw new RuntimeException(e);
		            
		        }
	    	
	    	
	    }
	    public void rechercherArticle() throws SQLException {
	    	 String sql1="select * from article where ref = '"+txfref.getText()+"'";
	         Statement statement =connectDB.createStatement();
	         ResultSet queryResult =statement.executeQuery(sql1);
	         while (queryResult.next()){
	        	 txfdes.setText(queryResult.getString("des"));
	        	 txfprix.setText(queryResult.getString("pv"));
	        	 labelqd.setText(queryResult.getString("qte"));
	        	 txfqte.setText("1");
	        	 
	         }
	            
	          
	    }
	    
	    public void remiseTest() throws SQLException {
	    	
	    	
	    		remplirRemise();
	    	
	    	
	    }
	    
 public void remplirRemise() throws SQLException {
	    	
	    	
	    	
	    	List<String> choix = new ArrayList<String>();	
	    	List<String> choix1 = new ArrayList<String>();
		        choix.add("Par montant");
		        choix.add("Par pourcentage");
		        choix1.add("");
		        	
		        if(chekremise.isSelected()) {
		       
		        	
		        	cbremise.setItems(FXCollections.observableArrayList(choix));
		        	cbremise.getSelectionModel().select(0);
		        	}else{
		 		       
			        	
			        	cbremise.setItems(FXCollections.observableArrayList(choix1));
			        	cbremise.getSelectionModel().select(0);
			        	}
 	        
		       
		      
	      }
 public void remplirTva() throws SQLException {
	    	
	    	
	    	
	    	List<String> choix = new ArrayList<String>();		   
		        choix.add("0%");
		        choix.add("7% ");
		        	
		        choix.add("10%");
		        choix.add("14%");
		        choix.add("20%");
		        	
		      
		        	cbtva.setItems(FXCollections.observableArrayList(choix));
		        	cbtva.getSelectionModel().select(0);
	      }
	    
	    
	    
	    public void remplirReglement() throws SQLException {
	    	
	    	
	    	
	    	List<String> choix = new ArrayList<String>();		   
		        choix.add("Banque");
		        choix.add("Espèce ");
		        	
		        choix.add("Chèque");
		        choix.add("Crédit");
		        	
		      
		        	cbreference.setItems(FXCollections.observableArrayList(choix));
		        	cbreference.getSelectionModel().select(1);
	      }
	    
	    
	    public void remplirStatut() throws SQLException {
	    	
	    	
	    	
	    	List<String> choix = new ArrayList<String>();		   
		        	choix.add("Reglé");
		        	choix.add("Non reglé");
		        	
		      
		        	cbstatut.setItems(FXCollections.observableArrayList(choix));
		        	cbstatut.getSelectionModel().select(1);
	      }
	    
	    
	    
	    public void remplirRechercherClient() throws SQLException {
	    	
	    	
	    	
	    	List<String> choix = new ArrayList<String>();		   
		        	choix.add("Cin");
		        	choix.add("Nom");
		        	
		      
		        	cbrecherche.setItems(FXCollections.observableArrayList(choix));
		        	cbrecherche.getSelectionModel().select(1);
	      }
	    
	    public void remplirClients() throws SQLException {
	    	String choix="";
	    	String sql="";
	    	List<String> list = new ArrayList<String>();	
	    	choix=cbrecherche.getValue();
	    	if(choix=="Cin") {
	    		sql="select cin from client ";
	    		Statement statement = connectDB.createStatement();
		        ResultSet queryResult =statement.executeQuery(sql);
		        while (queryResult.next()){
		        	list.add(queryResult.getString("cin"));
		        	
		        }
	    		
	    	}else if(choix=="Nom") {
	    		sql="select nom from client ";
	    		Statement statement = connectDB.createStatement();
		        ResultSet queryResult =statement.executeQuery(sql);
		        while (queryResult.next()){
		        	list.add(queryResult.getString("nom"));
	    	}}
	    	
	    	cbclient.setItems(FXCollections.observableArrayList(list));
        	cbclient.getSelectionModel().select(1);
	    }
	    
	    
	    
	    public void createFacture() throws SQLException {
	    	LocalDate datee = datef.getValue();
	        String datef=datee.toString();
	        String libelle="";
	        String statut=cbreference.getValue();
	        String type=cbstatut.getValue();
	        if(txtlibf.getText()!=null) {
	        	libelle=txtlibf.getText();
	        }
	        String reference=txtrf.getText();
	        Integer idc=0;
	       String sql="select idc from client where cin ='"+cbclient.getValue()+"'or nom='"+cbclient.getValue()+"'";
    		Statement statement = connectDB.createStatement();
	        ResultSet queryResult =statement.executeQuery(sql);
	        while (queryResult.next()){
	        	idc=queryResult.getInt("idc");
	        	
	        }
	        
	    
	        String    sql1="insert into facture(reff,libelle,datef,typer,statut,idc) values('" + reference + "','" + libelle + "','" + datef + "','" + type + "','" + statut + "','" + idc + "');";
	        Statement st =connectDB.prepareStatement(sql1);
	        st.executeUpdate(sql1);
	        
	    
	    }
	    
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			try {
				remplirRechercherClient();
				remplirReglement();
				remplirStatut();
				remplirTva();
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	    }