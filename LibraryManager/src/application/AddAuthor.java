package application;

import Controller.AddAuthorController;
import Controller.EditBookController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddAuthor extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
try {
			
			AddAuthorController controller = new AddAuthorController(primaryStage);
			primaryStage.setTitle("Thêm tác giả");
			primaryStage.setScene(new Scene(controller));
			primaryStage.setWidth(315);
			primaryStage.setHeight(280);
			primaryStage.setResizable(false);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
