package application;

import Controller.AddBookController;
import Controller.EditBookController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditBook extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			
			EditBookController controller = new EditBookController(primaryStage);
			primaryStage.setTitle("Sửa thông tin sách");
			primaryStage.setScene(new Scene(controller));
			primaryStage.setWidth(318);
			primaryStage.setHeight(330);
			primaryStage.setResizable(false);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
