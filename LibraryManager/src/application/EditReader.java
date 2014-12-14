package application;

import Controller.AddReaderController;
import Controller.EditReaderController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditReader extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
try {
			
			EditReaderController controller = new EditReaderController(primaryStage);
			primaryStage.setTitle("Sửa Độc giả");
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
