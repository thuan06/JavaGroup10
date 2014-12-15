package application;

import Controller.AddAuthorController;
import Controller.EditAuthorController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditAuthor extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
try {
			
			EditAuthorController controller = new EditAuthorController(primaryStage);
			primaryStage.setTitle("Sửa tác giả");
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
