package application;

import Controller.AddBookController;
import Controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddBook extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
				// TODO Auto-generated method stub
		try {
					
					AddBookController controller = new AddBookController(primaryStage);
					primaryStage.setTitle("Phần mềm quản lý thư viện");
					primaryStage.setScene(new Scene(controller));
					primaryStage.setWidth(316);
					primaryStage.setHeight(330);
					primaryStage.setResizable(false);
					primaryStage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
		
	}

}
