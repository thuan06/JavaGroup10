package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class MainController extends Pane{
	
	public MainController()
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				 "MainGUI.fxml"));
				         fxmlLoader.setRoot(this);
				         fxmlLoader.setController(this);

				         try {
				             fxmlLoader.load();
				         } catch (Exception exception) {
				             exception.printStackTrace();
				         }
	}

}
