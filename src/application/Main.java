package application;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
public class Main extends Application {

	


		@Override
		public void start(Stage stage) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/templates/main.fxml"));
				//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/templates/client.fxml"));
		        Scene scene = new Scene(root);
		        stage.setTitle("!");
		        stage.setScene(scene);
		        stage.show();

			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	

		public static void main(String[] args) {
			launch(args);
		}
	}


