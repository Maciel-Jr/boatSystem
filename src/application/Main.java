package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override

	public void start(Stage primaryStage) {
		/*try {

			Parent parent = FXMLLoader.load(getClass().getResource("/gui/View.fxml"));
			Scene Scene = new Scene(parent);
			aplicacao.setScene(Scene);
			aplicacao.show();

		} catch (IOException e) {
			e.printStackTrace();
		}*/
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/View.fxml"));
			Parent parent = loader.load();
			Scene mainScene = new Scene(parent);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Boat System");
			primaryStage.show();
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

	

	public static void main(String[] args) {
		launch(args);

	}
}
