package application;
	
import Controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			MainController controller = new MainController(primaryStage);
			primaryStage.setTitle("Phần mềm quản lý thư viện");
			primaryStage.setScene(new Scene(controller));
			primaryStage.setWidth(707);
			primaryStage.setHeight(500);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
