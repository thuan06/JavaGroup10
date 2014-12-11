package Controller;

import java.io.IOException;








import java.sql.ResultSet;
import java.sql.Statement;

import application.AddAuthor;
import application.AddBook;
import application.AddReader;
import application.EditAuthor;
import application.EditBook;
import application.EditReader;
import application.KetNoiDuLieu;
import application.Register;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController extends Pane{
	
	@FXML
	private TextField accName;
	@FXML
	private PasswordField pass1;
	
	@FXML
	private Label tip;
	String acc, password;
	private Stage nowStage;
	KetNoiDuLieu connection = new KetNoiDuLieu();
	ResultSet result =null;
	Statement state = null;
	public MainController(Stage stage)
	{
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Layouts/MainGUI.fxml"));
				         fxmlLoader.setRoot(this);
				         fxmlLoader.setController(this);
				         nowStage = stage;
				         try {
				             fxmlLoader.load();
				         } catch (Exception exception) {
				             exception.printStackTrace();
				         }
	}
	@FXML
	private void addBook () throws Exception
	{
		new AddBook().start(new Stage());
	}
	@FXML
	private void editBook() throws Exception 
	{
		new EditBook().start(new Stage());
	}
	@FXML
	private void addAuthor() throws Exception
	{
		new AddAuthor().start(new Stage());
	}
	@FXML
	private void editAuthor() throws Exception
	{
		new EditAuthor().start(new Stage());
	}
	@FXML
	private void addReader() throws Exception
	{
		new AddReader().start(new Stage());
	}
	@FXML
	private void editReader() throws Exception
	{
		new EditReader().start(new Stage());
	}

}
