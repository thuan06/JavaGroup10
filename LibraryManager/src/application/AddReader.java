package application;

import Controller.AddReaderController;
import Controller.EditAuthorController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddReader extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
try {
			
			AddReaderController controller = new AddReaderController(primaryStage);
			primaryStage.setTitle("Thêm Độc giả");
			primaryStage.setScene(new Scene(controller));
			primaryStage.setWidth(327);
			primaryStage.setHeight(330);
			primaryStage.setResizable(false);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
