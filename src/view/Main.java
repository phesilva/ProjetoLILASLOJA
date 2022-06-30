package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws IOException{
	
		TabPane pane = (TabPane)FXMLLoader.load(this.getClass().getResource("Cliente.fxml"));
		
		Scene cena = new Scene(pane);
		
		stage.setScene(cena);
		stage.setTitle("Lilas LOJA");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
