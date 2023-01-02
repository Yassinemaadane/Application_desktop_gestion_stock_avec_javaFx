package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;




public class OpendialogController implements Initializable  {
	@FXML
	private Button btnfacture;
	public void openDocFacture(MouseEvent event) throws IOException {
		
		
		Scene scene1 = btnfacture.getScene();
		Stage stage = (Stage) scene1.getWindow();
		stage.close();
		
		
		 Stage secondStage = new Stage();
		 Parent root = FXMLLoader.load(getClass().getResource("/templates/Document.fxml"));
		 Scene scene = new Scene(root);
		 secondStage.setScene(scene);
		 secondStage.show();



	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
