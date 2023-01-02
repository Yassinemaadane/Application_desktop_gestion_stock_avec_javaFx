package controllers;


import models.client;
import models.commande;
import models.produitFacture;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.github.royken.converter.FrenchNumberToWords;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

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
import com.ibm.icu.text.RuleBasedNumberFormat;

public class factureController implements Initializable{
	private static int factureCounter = 0;
	
	  connexionMysql connectNow= new connexionMysql();
	    Connection connectDB = connectNow.getConnection();
	    @FXML
	    private Button btnenregistrer;
	    @FXML
	    private Button btnvaliderall;

	    
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
	    private ComboBox<String> cbstatutr;

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
		
		
		public void miseFacture() throws SQLException {
			
			String sql="select typer from facture where reff='"+txtrf.getText()+"'";
			String nett=labelnet.getText();
			Float net =Float.valueOf(nett);
			Float totalr=0f;
			 Statement statement =connectDB.createStatement();
		        ResultSet queryResult =statement.executeQuery(sql);
		        String sql1="";
		        String test="nr";
		        String type="";
		        while (queryResult.next()){
		        	//System.out.println((queryResult.getString("typer"))==(queryResult.getString("typer")));
		        	//if(queryResult.getString("typer")=="nr") {
		        		// sql1="update facture set total='"+net+"', totaldu='"+net+"', totalr='"+totalr+"'where reff='"+txtrf.getText()+"'";
		        	type=queryResult.getString("typer");
		        		//System.out.println("here2");
		        		// sql1="update facture set total='"+totalr+"',totaldu='"+totalr+"', totalr='"+net+"'where reff='"+txtrf.getText()+"'";
		        		
		        	}
		        if(cbstatutr.getValue()=="Non reglé") {
		        	
		        	sql1="update facture set total='"+net+"', totaldu='"+net+"', totalr='"+totalr+"'where reff='"+txtrf.getText()+"'";
		        }
		        else {
	        		 sql1="update facture set total='"+totalr+"',totaldu='"+totalr+"', totalr='"+net+"'where reff='"+txtrf.getText()+"'";

		        }
		        

			    Statement statement1 =connectDB.createStatement();
			    statement1.executeUpdate(sql1);
		        
		        
		}
		
		
		
		
		
		        	
		        //Statement statement1 =connectDB.createStatement();
        		
				// statement1.executeUpdate(sql1);
		        	
		        
		
		
		
		public void invoice() throws FileNotFoundException, MalformedURLException, SQLException {
			String path ="C:\\Users\\Yassine Maadane\\Desktop\\facture.pdf";
		    PdfWriter writer = new PdfWriter(path);

		    PdfDocument pdf = new PdfDocument(writer);
			PageSize ps = pdf.getDefaultPageSize();

		    pdf.setDefaultPageSize(PageSize.A4);
		    
		    Document document= new Document(pdf);
		    
		    float twocol=600f;
		    float twocol1=300f;
		    float twocol150=twocol+2f;
		    float twocolumnWidth[]= {twocol,twocol};
		    float twocolumnWidth1[]= {twocol1};
		    float threecol[]= {244f,112f,112f,112f};
		    Table table11 = new Table(twocolumnWidth1);
		    Table table22 = new Table(twocolumnWidth1);
		    Table tablee = new Table(twocolumnWidth);
		    
		    Table table1 = new Table(twocolumnWidth1);
		    Table table2 = new Table(twocolumnWidth1);
		    Table table = new Table(twocolumnWidth);
		    var cell = new Cell();
		    var cell1 = new Cell();
		    var cell2 = new Cell();
		    var cell3 = new Cell();
		    
		    
		    
		    String imageFile = "C:\\Users\\Yassine Maadane\\Desktop\\fx\\pytho.png";       
		      ImageData data = ImageDataFactory.create(imageFile);        

		      // Creating the image       
		      Image img = new Image(data);  
		      
		      
		      
		      String imageFile1 = "C:\\Users\\Yassine Maadane\\Desktop\\fx\\bon.png";       
		      ImageData data1 = ImageDataFactory.create(imageFile1);        

		      // Creating the image       
		      Image img1 = new Image(data1);   
		    
		    cell2.add(img.setAutoScale(true));
		    cell3.add(img1.setAutoScale(true));
		    table11.addCell(cell2.setPaddingLeft(10f).setBorder(Border.NO_BORDER));
		    table22.addCell(cell3.setPaddingLeft(10f).setBorder(Border.NO_BORDER));
		    tablee.addCell(new Cell().add(table11).setBorder(Border.NO_BORDER));
		    tablee.addCell(new Cell().add(table22).setBorder(Border.NO_BORDER).setPaddingLeft(10f));
		
		    
		    cell.add(new Paragraph("Reference de facture   : facture1").setMultipliedLeading(2.0f));
		    cell.add(new Paragraph("Date de facture            : 18/10/2022").setMultipliedLeading(2.0f));
		    cell.add(new Paragraph("Date d'échéance          : 18/10/2022").setMultipliedLeading(2.0f));
		    cell.add(new Paragraph("Mode de paiement       : Espece").setMultipliedLeading(2.0f));
		    
		    table1.addCell(cell.setPaddingLeft(10f));
		    cell1.add(new Paragraph("Code client       : Cl-1").setMultipliedLeading(2.0f));
		    cell1.add(new Paragraph("Client                : Moha Moha").setMultipliedLeading(2.0f));
		    cell1.add(new Paragraph("Adresse            : Aghbalou").setMultipliedLeading(2.0f));
		    cell1.add(new Paragraph("Telephone        : 0666221417").setMultipliedLeading(2.0f));
		    table2.addCell(cell1.setPaddingLeft(10f));
		    table.addCell(new Cell().add(table1).setBorder(Border.NO_BORDER));
		    table.addCell(new Cell().add(table2).setBorder(Border.NO_BORDER).setPaddingLeft(10f));
		    
		    
		    
		    
		    document.add(tablee.setMarginBottom(20f));
		    document.add(table.setMarginBottom(20f));
		    
		    
		    Table tableProduct = new Table(threecol);
		    tableProduct.setBackgroundColor(Color.GRAY, 0.7f);
		    tableProduct.addCell(new Cell().add("Designation").setFontColor(Color.BLACK).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
		    tableProduct.addCell(new Cell().add("Qauntité").setFontColor(Color.BLACK).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
		    tableProduct.addCell(new Cell().add("Prix TTC").setFontColor(Color.BLACK).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
		    tableProduct.addCell(new Cell().add("Montant TTC").setFontColor(Color.BLACK).setBorder(Border.NO_BORDER).setBorderRight(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
		    List<produitFacture> produitfacture = new ArrayList<>();
/*produitfacture.add(new produitFacture("des1", 1f,1));
produitfacture.add(new produitFacture("des2",(float) 2,1));
produitfacture.add(new produitFacture("des3",(float) 3,1));
produitfacture.add(new produitFacture("des4",(float) 4,1));
produitfacture.add(new produitFacture("des5",(float) 5,1));
produitfacture.add(new produitFacture("des6",(float) 6,1));

produitfacture.add(new produitFacture("des1", 1f,1));
produitfacture.add(new produitFacture("des2",(float) 2,1));
produitfacture.add(new produitFacture("des3",(float) 3,1));
produitfacture.add(new produitFacture("des4",(float) 4,1));
produitfacture.add(new produitFacture("des5",(float) 5,1));
produitfacture.add(new produitFacture("des6",(float) 6,1));*/


List<produitFacture> produitfacture1 = new ArrayList<>();
String sqlGetCommande="select * from commande where refF='"+txtrf.getText()+"'";
Statement statement = connectDB.createStatement();
ResultSet queryResult =statement.executeQuery(sqlGetCommande);
Float prixTTc=0.2f;
Float totalTTc=0.2f;
while (queryResult.next()){
	Statement statement1 = connectDB.createStatement();
	String sqlgetDes="select * from article where ref='"+ queryResult.getString("refA")+"'";
	ResultSet queryResult1 =statement1.executeQuery(sqlgetDes);
	prixTTc=(queryResult.getFloat("toHT")-queryResult.getFloat("toR")+queryResult.getFloat("toTVA"))/queryResult.getInt("qte");
	totalTTc=(queryResult.getFloat("toHT")-queryResult.getFloat("toR")+queryResult.getFloat("toTVA"));
	while (queryResult1.next()){
		System.out.println(queryResult1.getString("des"));
	produitfacture1.add(new produitFacture(queryResult1.getString("des"), prixTTc, queryResult.getInt("qte"), totalTTc,queryResult.getFloat("toR"),queryResult.getFloat("toTVA")));
	
	}

}

















Table tableProduct1 = new Table(threecol);
tableProduct1.setHorizontalAlignment(HorizontalAlignment.RIGHT);
float sum =0f;
float sumR =0f;
float sumTva =0f;
float sumTht =0f;
produitFacture e = produitfacture1.get(produitfacture1.size() - 1);
		   
		   for(produitFacture produit:produitfacture1) {
			      float total=produit.getTottc();
                  sum+=total;
                  sumR=sumR+produit.getRemise();
                  sumTva=sumTva+produit.getTva();
             if(produit==e) {
			   
			   tableProduct1.addCell(new Cell().add(produit.getDes()).setFontColor(Color.BLACK).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)).setBorderBottom(new SolidBorder(1)).setTextAlignment(TextAlignment.LEFT));
			   tableProduct1.addCell(new Cell().add(String.valueOf(produit.getQte())).setFontColor(Color.BLACK).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)).setBorderBottom(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
			   tableProduct1.addCell(new Cell().add(String.valueOf(produit.getPrix())).setFontColor(Color.BLACK).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)).setBorderRight(new SolidBorder(1)).setBorderBottom(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
			 
			   tableProduct1.addCell(new Cell().add(String.valueOf(total)).setFontColor(Color.BLACK).setBorder(Border.NO_BORDER).setBorderRight(new SolidBorder(1)).setBorderBottom(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));}
			   else {
				   tableProduct1.addCell(new Cell().add(produit.getDes()).setFontColor(Color.BLACK).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)).setTextAlignment(TextAlignment.LEFT));
				   tableProduct1.addCell(new Cell().add(String.valueOf(produit.getQte())).setFontColor(Color.BLACK).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
				   tableProduct1.addCell(new Cell().add(String.valueOf(produit.getPrix())).setFontColor(Color.BLACK).setBorder(Border.NO_BORDER).setBorderLeft(new SolidBorder(1)).setBorderRight(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
				   tableProduct1.addCell(new Cell().add(String.valueOf(total)).setFontColor(Color.BLACK).setBorder(Border.NO_BORDER).setBorderRight(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));}
			   }	
		   
		   
		   document.add(tableProduct);
		    document.add(tableProduct1);
		    sumTht=sum-sumTva+sumR;
		    float onecolall[]= {600f};
		    Table divider = new Table(onecolall);
		    Border br = new DashedBorder(Color.GRAY, 2f, 0.7f) ;
		    divider.setBorder(br);
		    float onecol[]= {145f,170f,120f,145f};
		    Table tableProduct2 = new Table(onecol);
		    tableProduct2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
			tableProduct2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		    tableProduct2.addCell(new Cell().add("Total HT : ").setBorder(Border.NO_BORDER));
			tableProduct2.addCell(new Cell().add(String.valueOf(sumTht)+" DH").setTextAlignment(TextAlignment.CENTER));
			
			tableProduct2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
			tableProduct2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
			tableProduct2.addCell(new Cell().add("Total Remise :").setBorder(Border.NO_BORDER));
			tableProduct2.addCell(new Cell().add(String.valueOf(sumR)+" DH").setTextAlignment(TextAlignment.CENTER));
		    tableProduct2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
			tableProduct2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		    tableProduct2.addCell(new Cell().add("Total TVA :").setBorder(Border.NO_BORDER));
			tableProduct2.addCell(new Cell().add(String.valueOf(sumTva)+" DH").setTextAlignment(TextAlignment.CENTER));
					
			tableProduct2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
			tableProduct2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
			tableProduct2.addCell(new Cell().add("Total TTC :").setBorder(Border.NO_BORDER));
			tableProduct2.addCell(new Cell().add(String.valueOf(sum)+" DH").setTextAlignment(TextAlignment.CENTER));
						
						
			document.add(tableProduct2);
			float f[]= {600f};
		Table tb = new Table(f);
		    var paragraph  = new Paragraph("Adresse : Aghbalou Centre Region Midelt.     Tél:0666666666.");
		    tb.setFixedPosition(document.getLeftMargin(), document.getBottomMargin(), ps.getWidth() - document.getLeftMargin() - document.getRightMargin());
		    tb.addCell(new Cell().add(paragraph.setTextAlignment(TextAlignment.CENTER)));
			document.add(tb);
			
			BigDecimal num = new BigDecimal(2718.28);
			RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);
			String result = formatter.format(num);
			System.out.println(result);
	 		    
		  
		    document.close();
		}

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
	    @FXML
	    private Label labelnet;

	   

	    @FXML
	    private Label labeltht;

	    @FXML
	    private Label labeltr;

	    @FXML
	    private Label labelttva;
	    
	    
	    
	    
	    

	    @FXML
	    private Label labeladr;

	    @FXML
	    private Label labelcr;


	    @FXML
	    private Label labelnom;

	    

	    @FXML
	    private Label labeltel;
	    
	    
	    
	    public void showClient() throws SQLException {
	    	String sql="select * from client where nom='"+cbclient.getValue()+"' or cin ='"+cbclient.getValue()+"'";
	    	 Statement statement =connectDB.createStatement();
	         ResultSet queryResult =statement.executeQuery(sql);
	         while (queryResult.next()){
	        	 labeltel.setText(queryResult.getString("numc"));
	        	 labelnom.setText(queryResult.getString("nom")+" "+queryResult.getString("prenom"));
	        	 labeladr.setText(queryResult.getString("adresse"));
	         }
	    	
	    }
	    public void Total() throws SQLException {
	    	Float tht=(float) 0;
	    	Float remise=(float) 0;
	    	Float tva=(float) 0;
	    	Float net=(float) 0;
	    	String sql ="SELECT sum(toHT) as tht,SUM(toTVA) as ttva,SUM(toR) as tr  from commande WHERE refF='"+txtrf.getText()+"'";
	    	if(txtrf.getText()!=null) {
	    		 Statement statement =connectDB.createStatement();
		         ResultSet queryResult =statement.executeQuery(sql);
		         while (queryResult.next()){
		        	 net =queryResult.getFloat("tht")-queryResult.getFloat("tr")+queryResult.getFloat("ttva");
		        	tva =(float) (Math.floor(queryResult.getFloat("ttva")*100) / 100);
		        	labeltht.setText(queryResult.getString("tht")+" DH");
		        	labeltr.setText(queryResult.getString("tr")+" DH");
		        	labelttva.setText(Float.toString(tva)+" DH");
		        	labelnet.setText(Float.toString(net));

		        	 
		         }}
		         else {
			        	labeltht.setText("0");

		         }
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
	    	   String sqlUpdateArticle="UPDATE article SET qte=qte-'"+qte+"' where ref ='"+refA+"'";
	    	   try {
		            //Statement statement =connectDB.createStatement();
		            Statement st =connectDB.prepareStatement(sql);
		            st.executeUpdate(sql);
		            Statement st1 =connectDB.prepareStatement(sqlUpdateArticle);
		            st1.executeUpdate(sqlUpdateArticle);
		            
		            Alert alert =new Alert(Alert.AlertType.CONFIRMATION,"succes",ButtonType.OK);
		            showCommande();
		            Total();
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
		        	
		      
		        	cbstatutr.setItems(FXCollections.observableArrayList(choix));
		        	cbstatutr.getSelectionModel().select(1);
	      }
	    
	    
	    
	    public void remplirStatutFacture() throws SQLException {
	    	
	    	
	    	
	    	List<String> choix = new ArrayList<String>();		   
		        	choix.add("A comptabiliser");
		        	choix.add("Comptabilisé");
		        	
		      
		        	cbstatut.setItems(FXCollections.observableArrayList(choix));
		        	cbstatut.getSelectionModel().select(0);
	      }
	    
	    
	    public void Key() {
	   	 String text=txfref.getText();
	   	 System.out.println(text);
	    }
	    
	    
	    public void Statut() {
	    	if(cbstatut.getValue()=="Comptabilisé") {
	    		cbstatutr.setDisable(false);
	    		System.out.println("done");
	    	}
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
	        String type=cbstatutr.getValue();
	        System.out.println(type);
	        String typer="";
	        if(type=="Non reglé") {
	        	typer="nr";
	        }
	        else if(type=="Reglé") {
	        	typer="r";
	        }
	        String statut=cbreference.getValue();
	        System.out.println(statut);
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
	        
	    
	        String    sql1="insert into facture(reff,libelle,datef,typer,statut,idc) values('" + reference + "','" + libelle + "','" + datef + "','" + typer + "','" + statut + "','" + idc + "');";
	        Statement st =connectDB.prepareStatement(sql1);
	        st.executeUpdate(sql1);
	        factureCounter++;
	        showClient();
	        
	        
	    
	    }
	    
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			try {
				remplirRechercherClient();
				remplirReglement();
				remplirStatut();
				remplirTva();
				remplirStatutFacture();
				txtrf.setText("Fa-"+factureCounter);
				cbstatut.setOnAction(event -> {
				    String selectedOption = cbstatut.getSelectionModel().getSelectedItem();
				    if(selectedOption=="Comptabilisé") {
			    		cbstatutr.setDisable(false);
				    }
				    if(selectedOption!="Comptabilisé") {
				    	cbstatutr.setDisable(true);
				    	cbreference.setDisable(true);
				    	
				    }
				});
				cbstatutr.setOnAction(event -> {
				    String selectedOption = cbstatutr.getSelectionModel().getSelectedItem();
				    if(selectedOption=="Reglé") {
			    		cbreference.setDisable(false);
				    }
				    if(selectedOption!="Reglé") {
				    	cbreference.setDisable(true);
				    }
				});
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	    }