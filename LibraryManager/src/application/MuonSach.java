package application;

import Controller.AddBookController;
import Controller.MuonTraController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MuonSach extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			
			MuonTraController controller = new MuonTraController(primaryStage);
			primaryStage.setTitle("Đăng kí mượn sách");
			primaryStage.setScene(new Scene(controller));
			primaryStage.setWidth(574);
			primaryStage.setHeight(470);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
