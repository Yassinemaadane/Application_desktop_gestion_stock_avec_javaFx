package controllers;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.cheque;
import models.notification;
public class boardController implements Initializable { 
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
	@FXML
private Label today;
	private Parent fxml;
    @FXML
private Button btngestion;

public void openClient(MouseEvent event) throws IOException {
	   Stage secondStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/templates/test.fxml"));
	 Scene scene = new Scene(root);
	 secondStage.setScene(scene);
	 secondStage.initModality(Modality.APPLICATION_MODAL);
	 secondStage.showAndWait();

       
       
}
   
public void openProduit(MouseEvent event) throws IOException {
	   Stage secondStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/templates/produit.fxml"));
	 Scene scene = new Scene(root);
	 secondStage.setScene(scene);
	 secondStage.show();

 
 
}



public void openFournisseur(MouseEvent event) throws IOException {
	   Stage secondStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/templates/fournisseur.fxml"));
	 Scene scene = new Scene(root);
	 secondStage.setScene(scene);
	 secondStage.show();

    
    
}


public void openFacture(MouseEvent event) throws IOException {
	   Stage secondStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/templates/choixDoc.fxml"));
	 Scene scene = new Scene(root);
	 secondStage.setScene(scene);
	 secondStage.show();

 
 
}


public void openCheque(MouseEvent event) throws IOException {
	   Stage secondStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/templates/cheque.fxml"));
	 Scene scene = new Scene(root);
	 secondStage.setScene(scene);
	 secondStage.show();



}
public void openStock(MouseEvent event) throws IOException {
	   Stage secondStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/templates/stock.fxml"));
	 Scene scene = new Scene(root);
	 secondStage.setScene(scene);
	 secondStage.initModality(Modality.APPLICATION_MODAL);
	 secondStage.showAndWait();

    
    
}
connexionMysql connectNow= new connexionMysql();
Connection connectDB = connectNow.getConnection();
@FXML
private BarChart<String,Number> chartbarr;



public void chart() throws SQLException {
	String sql ="select month(datec) as month,SUM(toHT+toTVA-toR) as summ,year(datec) as year from commande where datec> now() - INTERVAL 12 month group by(month(datec)) ORDER by datec LIMIT 12;";
	 Statement statement =connectDB.createStatement();
     ResultSet queryResult =statement.executeQuery(sql);
 	XYChart.Series<String,Number> s = new XYChart.Series<String,Number>();
s.setName("Chiffre d'affaire");


     while (queryResult.next()){
 
    	s.getData().add(new XYChart.Data<>(queryResult.getString("month")+"/"+queryResult.getString("year"),queryResult.getInt("summ")));
	
     }
	chartbarr.getData().addAll(s);
	
}


public void deleteAllNoti() throws SQLException {
	String sql1="delete  from notification";
	  Statement st =connectDB.prepareStatement(sql1);
      st.executeUpdate(sql1);
}

public void testDays() throws SQLException {
	
	 LocalDate localDate = LocalDate.now();

	  Date currentDate = new Date();
	LocalDate datee ;

	String sql ="SELECT * from cheque ORDER BY datee DESC;";
	 Statement statement =connectDB.createStatement();
    ResultSet queryResult =statement.executeQuery(sql);
    while (queryResult.next()){
    	datee=queryResult.getDate("datee").toLocalDate();
    	
    	
        Period period = Period.between(localDate, datee);
        
        System.out.println(queryResult.getString("refch")+ period.getDays());
        
        
        if(period.getDays()<10 &&period.getDays()>0) {
        	
        	String not="Le cheque du référence : "+queryResult.getString("refch")+" du client : "+queryResult.getInt("idc")+ "un échéancier de : "+period.getDays()+" jours";
        	String sql1="Insert into notification(notification) values('"+not+"')";
        	  Statement st =connectDB.prepareStatement(sql1);
		        st.executeUpdate(sql1);
        	
        	
        }else if(period.getDays()<0) {
        	
        	String not="Le cheque du référence : "+queryResult.getString("refch")+" du client : "+queryResult.getInt("idc")+ " à passer le délai avec : "+(-period.getDays())+" jours";
        	String sql1="Insert into notification(notification) values('"+not+"')";
        	  Statement st =connectDB.prepareStatement(sql1);
		        st.executeUpdate(sql1);
        }

    	
    }
	
	
}

@FXML
private TableColumn<notification, String> columnnotification;

@FXML
private TableView<notification> tableviwnotification;

public ObservableList<notification> data = FXCollections.observableArrayList();

public void showNotification() throws SQLException {
	tableviwnotification.getItems().removeAll();
	
	String sql3="select * from notification";
    Statement statement2 =connectDB.createStatement();
    ResultSet queryResult2 =statement2.executeQuery(sql3);
    while (queryResult2.next()){
    
data.add(new notification(queryResult2.getString("notification")));
}
   
    
    columnnotification.setCellValueFactory(new PropertyValueFactory<notification,String>("not"));
 tableviwnotification.setItems(data);
}
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		 Timer timer = new Timer();

		    // Set the schedule function and rate
		    timer.scheduleAtFixedRate(new TimerTask() {
		      
		      public void run() {
		    	  
		    	  try {
		    		deleteAllNoti();
					testDays();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        // This function runs every day
		        
		        
		        
		        
		        
		        
		        
		      }
		    }, 0,24*60*60*1000);  // 24*60*60*1000 is the number of milliseconds in a day
		  
		
		//24*60*60*1000



		
		
		
		   today.setText(dtf.format(now));
		   try {
			chart();
			showNotification();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}}
