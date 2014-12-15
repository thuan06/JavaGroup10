package application;

import Controller.MainController;
import Controller.RegisterController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Register extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	try {
				
				RegisterController controller = new RegisterController(primaryStage);
				primaryStage.setTitle("Đăng ký tài khoản");
				primaryStage.setScene(new Scene(controller));
				primaryStage.setWidth(438);
				primaryStage.setHeight(340);
				
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
	
		}

}
