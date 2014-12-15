package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.AddAuthor;
import application.KetNoiDuLieu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddBookController extends Pane{
	private Stage nowStage;
	@FXML
	private TextField idBook;
	@FXML
	private TextField name;
	@FXML
	private ComboBox<String> type;
	@FXML
	private TextField price;
	@FXML
	private TextField idAu;
	@FXML
	private Label tip;
	public AddBookController(Stage stage)
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Layouts/AddBookGUI.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        nowStage = stage;
        try {
            fxmlLoader.load();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        
        type.getItems().addAll(
        		"Khoa Hoc",
    	        "Giao duc",
    	        "Tieu thuyet",
    	        "Truyen ngan",
    	        "Van hoc",
    	        "The loai khac"
        		);
        
        
	}
	@FXML
	private void add() throws SQLException
	{	
		String idB = idBook.getText();
		String n = name.getText();
		String t = type.getValue();
		String p = price.getText();
		String idA = idAu.getText();
		KetNoiDuLieu connection = new KetNoiDuLieu();
		Statement statement;
		ResultSet result;
		//Kiểm tra nhập liệu IDBook và IDAuthor
		if(idB.equals("")||idA.equals(""))
		{
			tip.setText("Không được để trống ID Sách và ID Tác giả");
			return;
		}
		//Check ID
		result = connection.selectOneColum("sach", "ID_Sach");
		while (result.next())
		{
			String id = result.getString("ID_Sach");
			if(id.equalsIgnoreCase(idB))
			{
				tip.setText("ID Sách đã tồn tại!");
				return;
			}
			
		}
		result.close();
		//Check ID Tác giả
		result = connection.selectOneColum("tacgia", "ID_TacGia");
		boolean flag = false;
		while (result.next())
		{
			String id = result.getString("ID_TacGia");
			
			if(id.equalsIgnoreCase(idA))
			{
				flag = true;
				break;
			}
			
		}
		if(flag == false)
		{
		tip.setText("ID Tác giả không có trong database. Hãy thêm tác giả!");
		return;
		}
		else
		{
			String sql = "INSERT INTO `librarydb`.`sach` "
		   	         + "(`ID_Sach`, `TenSach`, `LoaiSach`,`Gia`,`TacGia_ID_TacGia`)"
		   	         + "VALUES ('"+idB+"','" + n +"','"+t+"','"
		   	         + p +"','"+ idA+"');";
			statement = connection.getConn().createStatement();
			statement.executeUpdate(sql);
			System.out.println("Thêm sách thành công!");
			nowStage.close();
		}
		
		
	}
	@FXML
	private void cancel()
	{
		nowStage.close();
	}
	@FXML
	private void addAuthor() throws Exception
	{
		new AddAuthor().start(new Stage());
	}
}
