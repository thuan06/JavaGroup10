package Controller;

import java.io.IOException;








import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.AddAuthor;
import application.AddBook;
import application.AddReader;
import application.Author;
import application.Book;
import application.DataTable;
import application.DocGia;
import application.EditAuthor;
import application.EditBook;
import application.EditReader;
import application.KetNoiDuLieu;
import application.MuonSach;
import application.MuonTra;
import application.Register;
import application.Search;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
		initTable();		         
				         
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
	@FXML
	private TableView<Book> BookTable;
	@FXML
	private TableColumn<Book, String> idBook;
	@FXML
	private TableColumn<Book, String> nameBook;
	@FXML
	private TableColumn<Book, String> typeBook;
	@FXML
	private TableColumn<Book, String> statusBook;
	@FXML
	private TableColumn<Book, String> Book_ID_Author;
	
	@FXML
	private TableView<Author> AuthorTable;
	@FXML
	private TableColumn<Author, String> ID_Author;
	@FXML
	private TableColumn<Author, String> authorName;
	@FXML
	private TableColumn<Author, String> authorBirth;
	
	@FXML
	private TableView<DocGia> DocGiaTable;
	@FXML
	private TableColumn<DocGia, String> ID_DocGia;
	@FXML
	private TableColumn<DocGia, String> nameDocGia;
	@FXML
	private TableColumn<DocGia, String> job;
	@FXML
	private TableColumn<DocGia, String> NgayCap;
	
	@FXML
	private TableView<MuonTra> MuonTraTable;
	@FXML
	private TableColumn<MuonTra, String> idMuonTra;
	@FXML
	private TableColumn<MuonTra, String> MT_ID_DocGia;
	@FXML
	private TableColumn<MuonTra, String> MT_ID_Sach;
	@FXML
	private TableColumn<MuonTra, String> NgayMuon;
	@FXML
	private TableColumn<MuonTra, String> NgayTra;
	@FXML
	private void refresh()
	{
		BookTable.setItems(new DataTable().getBookData());
		AuthorTable.setItems(new DataTable().getAuthorData());
		DocGiaTable.setItems(new DataTable().getDocGiaData());
		MuonTraTable.setItems(new DataTable().getMuonTraData());
		
	}
	@FXML
	private TextField searchBook;
	@FXML
	private void timsach()
	{
		String name = searchBook.getText();
		try {
			BookTable.setItems(new Search().searchBook(name));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@FXML
	private TextField searchAuthor;
	@FXML
	private void timTacGia()
	{
		String name = searchAuthor.getText();
		try {
			AuthorTable.setItems(new Search().searchAuthor(name));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private TextField searchDocGia;
	@FXML
	private void timDocGia()
	{
		String name = searchDocGia.getText();
		try {
			DocGiaTable.setItems(new Search().searchDocGia(name));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML 
	private TextField delIDDocGia;
	@FXML
	private void delDocGia() throws SQLException
	{
		String delID = delIDDocGia.getText();
		String sqlDelete = "DELETE FROM `librarydb`.`docgia` WHERE `ID_DocGia`='" + delID + "';";
		Statement statement = connection.getConn().createStatement();
		int n = statement.executeUpdate(sqlDelete);
		if(n==1)
		{
			System.out.println("Đã xóa độc giả ID: "+ delID);
			BookTable.setItems(new DataTable().getBookData());
		}
		
		else 
			System.out.println("Xóa độc giả không thành công!");
	}
	@FXML
	private TextField delIDBook;
	@FXML
	private void delBook() throws SQLException
	{
		String delID = delIDBook.getText();
		String sqlDelete = "DELETE FROM `librarydb`.`sach` WHERE `ID_Sach`='" + delID + "';";
		Statement statement = connection.getConn().createStatement();
		int n = statement.executeUpdate(sqlDelete);
		if(n==1)
		{
			System.out.println("Đã xóa sách ID: "+ delID);
			BookTable.setItems(new DataTable().getBookData());
		}
		
		else 
			System.out.println("Xóa sách không thành công!");
	}
	@FXML
	private TextField delIDAuthor;
	@FXML
	private void delAuthor() throws SQLException
	{
		String delID = delIDAuthor.getText();
		String sqlDelete = "DELETE FROM `librarydb`.`tacgia` WHERE `ID_TacGia`='" + delID + "';";
		Statement statement = connection.getConn().createStatement();
		int n = statement.executeUpdate(sqlDelete);
		if(n==1)
		{
			System.out.println("Đã xóa tác giả ID: "+ delID);
			AuthorTable.setItems(new DataTable().getAuthorData());
		}
		
		else 
			System.out.println("Xóa tác giả không thành công!");
	}
	@FXML
	private void muonSach() throws Exception
	{
		new MuonSach().start(new Stage());
	}
	private void initTable()
	{
		//init Book table
		idBook.setCellValueFactory(cellData -> cellData.getValue().getID_Book());
        nameBook.setCellValueFactory(cellData -> cellData.getValue().getName());
        typeBook.setCellValueFactory(cellData -> cellData.getValue().getType());
        statusBook.setCellValueFactory(cellData -> cellData.getValue().getStatus());
        Book_ID_Author.setCellValueFactory(cellData -> cellData.getValue().getID_Author());
        BookTable.setItems(new DataTable().getBookData());
        
        //init Author table
        ID_Author.setCellValueFactory(cellData -> cellData.getValue().getID_Author());
        authorName.setCellValueFactory(cellData -> cellData.getValue().getName());
        authorBirth.setCellValueFactory(cellData -> cellData.getValue().getBirth());
        AuthorTable.setItems(new DataTable().getAuthorData());
        
        //init DocGia Table
        ID_DocGia.setCellValueFactory(cellData -> cellData.getValue().getID_DocGia());
        nameDocGia.setCellValueFactory(cellData -> cellData.getValue().getHoTen());
        job.setCellValueFactory(cellData -> cellData.getValue().getNgheNghiep());
        NgayCap.setCellValueFactory(cellData -> cellData.getValue().getNgayCapThe());
        DocGiaTable.setItems(new DataTable().getDocGiaData());
        
        //init MuonTra Table
        idMuonTra.setCellValueFactory(cellData -> cellData.getValue().getID_MuonTra());
        MT_ID_DocGia.setCellValueFactory(cellData -> cellData.getValue().getID_DocGia());
        MT_ID_Sach.setCellValueFactory(cellData -> cellData.getValue().getID_Sach());
        NgayMuon.setCellValueFactory(cellData -> cellData.getValue().getNgayMuon());
        NgayTra.setCellValueFactory(cellData -> cellData.getValue().getNgayTra());
		MuonTraTable.setItems(new DataTable().getMuonTraData());

	}
	
	@FXML
	private TextField idTraSach;
	@FXML
	private void traSach()
	{
		
	}
	
}
