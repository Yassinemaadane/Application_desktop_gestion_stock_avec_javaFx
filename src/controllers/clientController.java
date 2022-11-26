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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.print.attribute.standard.DateTimeAtCreation;

import org.testng.reporters.jq.Model;

public class clientController implements Initializable{
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
	  
	    private Button btnexit;
	    @FXML
	    private TableView<client> tableclient;
	    @FXML
	    private TableColumn<client, String> adressecolumn;
	    @FXML
	    private TableColumn<client, Integer> idcolumn;
	    @FXML
	    private TableColumn<client,String > cincolumn;
	    @FXML
	    private TableColumn<client, String> nomcolumn;

	    @FXML
	    private TableColumn<client, String> numerocolumn;

	    @FXML
	    private TableColumn<client, String> prenomcolumn;

	    @FXML
	    private ComboBox<String> cbsearch;

	    @FXML
	    private TextField txfsearch;

public void deleteselected() throws SQLException {
	    	    tableclient.getItems().removeAll();
	    	    client cl = tableclient.getSelectionModel().getSelectedItem();
	    	    String cinc=cl.getCin();
	    	    System.out.println(cinc);
	    	    String sqldelete ="delete from client where cin= '"+cinc+"'";
	    	    Statement statement =connectDB.createStatement();
	    	    statement.executeUpdate(sqldelete);

	    	showClient();
	    	}
public void openAddClient() throws IOException {
	Stage secondStage = new Stage();	
	Parent root = FXMLLoader.load(getClass().getResource("/templates/openAddClient.fxml"));
	 Scene scene = new Scene(root);
	 secondStage.setScene(scene);
	 secondStage.initModality(Modality.APPLICATION_MODAL);
	 secondStage.setResizable(false);
	 secondStage.showAndWait();
	 secondStage.setOnCloseRequest ( event -> {
		System.out.println("test");
 } );

}  	
public void openModify() throws IOException, SQLException {
	
	  tableclient.getItems().removeAll();
	    client cl = tableclient.getSelectionModel().getSelectedItem();
	String cinc=cl.getCin();
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/openAddClient.fxml"));
	Parent root = loader.load();
	AddClientController cont = loader.getController();
	cont.showInfo(cinc);
	// Scene scene = new Scene(root);
	 Stage secondStage = new Stage();	
	 secondStage.setScene(new Scene(root));
	 secondStage.initModality(Modality.APPLICATION_MODAL);
	 secondStage.setResizable(false);
	 secondStage.showAndWait();
	
	
	
}



/*
public void uploadFile() {
	 FileChooser fc=new FileChooser();
     fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Csv Files","*.csv"));
     File f=fc.showOpenDialog(null);
     if(f!=null){
        System.out.println(f);
      
     }
}*/
public void importClients() {
	String path ="";
	FileChooser fc=new FileChooser();
    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Csv Files","*.csv"));
    File f=fc.showOpenDialog(null);
    if(f!=null){
    	
    	 path=f.getAbsolutePath();
    }
	
    String  line="";
    String nom = "";
    String prenom = "";
    String cin = "";
    String adresse = "";
    
    Integer numc = 0;
    Integer fid=0;
    Integer crd=0;
    LocalDate datecreation ;
    
    try {
		BufferedReader br = new BufferedReader(new FileReader(path));
		while((line=br.readLine()) != null) {
			String [] values = line .split(",");
			nom = values[2];
			prenom = values[3];
			cin = values[1];
			adresse = values[4];
			numc = Integer.parseInt(values[5]);
			fid = Integer.parseInt(values[6]);
			crd = Integer.parseInt(values[7]);
			datecreation   = LocalDate.parse(values[8]);

			  String sqladd="insert into client(cin,nom,prenom,adresse,numc,fid,crd,datecreation) values('"+cin+"','"+nom+"','"+prenom+"','"+adresse+"','"+numc+"','"+fid+"','"+crd+"','"+datecreation+"')";
			   
			        //Statement statement =connectDB.createStatement();
			        Statement st =connectDB.prepareStatement(sqladd);
			        st.executeUpdate(sqladd);
			        		
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    try {
		showClient();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
	
}
public void exportClients() {
	String path ="C:\\Users\\Yassine Maadane\\Desktop\\cli.csv";
	try {
		FileWriter fw = new FileWriter(path);
		

        String sql1="select * from client";
        Statement statement = connectDB.createStatement();
        ResultSet queryResult =statement.executeQuery(sql1);
        while (queryResult.next()){

fw.append(String.valueOf(queryResult.getInt("idc")));
fw.append(",");
fw.append( queryResult.getString("cin"));
fw.append(",");
fw.append(queryResult.getString("nom"));
fw.append(",");
fw.append(queryResult.getString("prenom"));
fw.append(",");
fw.append(queryResult.getString("adresse"));
fw.append(",");
fw.append(queryResult.getString("numc"));
fw.append(",");
fw.append(queryResult.getString("fid"));
fw.append(",");
fw.append(queryResult.getString("crd"));
fw.append(",");
fw.append(queryResult.getString("datecreation"));
fw.append("\n");
        }
        System.out.println("succes");
fw.flush();
fw.close();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	    
 public void showClient() throws SQLException {
	 for ( int i = 0; i<tableclient.getItems().size(); i++) {
         tableclient.getItems().clear();
     }
	        String sql1="select * from client";
	        Statement statement = connectDB.createStatement();
	        ResultSet queryResult =statement.executeQuery(sql1);
	        while (queryResult.next()){

	            data.add(new client(queryResult.getInt("idc"),queryResult.getString("cin"),queryResult.getString("nom"),queryResult.getString("prenom"),queryResult.getString("adresse"),queryResult.getString("numc")));

	        }

	        idcolumn.setCellValueFactory(new PropertyValueFactory<client,Integer>("idc"));
	        cincolumn.setCellValueFactory(new PropertyValueFactory<client,String>("cin"));
	        nomcolumn.setCellValueFactory(new PropertyValueFactory<client,String>("nom"));
	        prenomcolumn.setCellValueFactory(new PropertyValueFactory<client,String>("prenom"));
	        adressecolumn.setCellValueFactory(new PropertyValueFactory<client,String>("adresse"));
	        numerocolumn.setCellValueFactory(new PropertyValueFactory<client,String>("numc"));

	        tableclient.setItems(data);
	    }

	public ObservableList<client> data = FXCollections.observableArrayList();
	
	
	public void quitter() {
		  Stage secondStage=(Stage) btnexit.getScene().getWindow();
		  secondStage.close();
		/*	 secondStage.setOnCloseRequest ( event -> {
				 clientController clientController = loader.getController;
			 } );
*/
	  }  
	
	public void deleteAll() throws SQLException {
		 String sqldelete ="delete  from client" ;
		 
		    Statement statement =connectDB.createStatement();
		    statement.executeUpdate(sqldelete);
		    showClient();

		}

	
	
	
	public void remplirRechercher() throws SQLException {
    	
    	
    	
    	List<String> choix = new ArrayList<String>();		   
	        	choix.add("Cin");
	        	choix.add("Nom");
	        	
	      
	        	cbsearch.setItems(FXCollections.observableArrayList(choix));
	        	cbsearch.getSelectionModel().select(1);
      }
	
	
@FXML
public void handle(KeyEvent e) {
	if(e.getCode().equals(KeyCode.ENTER)) {
		System.out.println(txfsearch.getText());
	}
	
}
	
	
	public void chercher() throws SQLException {
		for ( int i = 0; i<tableclient.getItems().size(); i++) {
		    tableclient.getItems().clear();
		}	
		String sql="";
		String ctr="";
String val =txfsearch.getText();
		ctr=cbsearch.getValue();
		if(ctr=="Cin") {
			sql=" select * from client where cin='" + val + "'";
			
		}else if(ctr=="Nom") {
			sql=" select * from client where Nom='" + val + "'";
			
		}
		
		
        Statement statement = connectDB.createStatement();
        ResultSet queryResult =statement.executeQuery(sql);
        while (queryResult.next()){

            data1.add(new client(queryResult.getInt("idc"),queryResult.getString("cin"),queryResult.getString("nom"),queryResult.getString("prenom"),queryResult.getString("adresse"),queryResult.getString("numc")));

        }

        idcolumn.setCellValueFactory(new PropertyValueFactory<client,Integer>("idc"));
        cincolumn.setCellValueFactory(new PropertyValueFactory<client,String>("cin"));
        nomcolumn.setCellValueFactory(new PropertyValueFactory<client,String>("nom"));
        prenomcolumn.setCellValueFactory(new PropertyValueFactory<client,String>("prenom"));
        adressecolumn.setCellValueFactory(new PropertyValueFactory<client,String>("adresse"));
        numerocolumn.setCellValueFactory(new PropertyValueFactory<client,String>("numc"));

        tableclient.setItems(data1);
    }

public ObservableList<client> data1 = FXCollections.observableArrayList();
public void exit() {
	  Stage secondStage=(Stage) btnexit.getScene().getWindow();
	  secondStage.close();
	/*	 secondStage.setOnCloseRequest ( event -> {
			 clientController clientController = loader.getController;
		 } );
*/
}  
	
	
	@Override
	 public void initialize(URL url, ResourceBundle resourceBundle) {
      
try {
	showClient();
	remplirRechercher();

} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	

}}
