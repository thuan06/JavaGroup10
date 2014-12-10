package application;

import Controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
try {
			
			LoginController controller = new LoginController(primaryStage);
			primaryStage.setTitle("Phần mềm quản lý thư viện");
			primaryStage.setScene(new Scene(controller));
			primaryStage.setWidth(485);
			primaryStage.setHeight(300);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
