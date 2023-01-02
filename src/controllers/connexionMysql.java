package controllers;


	import java.sql.Connection;
	import java.sql.DriverManager;
	public class connexionMysql {
	public Connection databaselink;
	public Connection getConnection(){
	

	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        databaselink=DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion?useUnicode=true&characterEncoding=UTF-8","root","");
	        
	    } catch (Exception e) {
	       e.printStackTrace();
	    }
	    
	    return databaselink;
	}
	

}
