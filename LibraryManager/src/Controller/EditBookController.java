package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.KetNoiDuLieu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditBookController extends Pane{
	private Stage nowStage;
	public EditBookController(Stage stage)
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Layouts/EditBookGUI.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        nowStage = stage;
        try {
            fxmlLoader.load();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        typeBook.getItems().addAll(
        		"Khoa Hoc",
    	        "Giao duc",
    	        "Tieu thuyet",
    	        "Truyen ngan",
    	        "Van hoc",
    	        "The loai khac"
        		);
       
	}
	@FXML
	private TextField ID_Book;
	@FXML
	private TextField nameBook;
	@FXML
	private ComboBox<String> typeBook;
	@FXML
	private TextField Price;
	@FXML
	private TextField ID_Author;
	@FXML
	private Label tip;
	@FXML
	private void edit() throws SQLException
	{
		String idB = ID_Book.getText();
		String name = nameBook.getText();
		String type = typeBook.getValue();
		String gia = Price.getText();
		String idA = ID_Author.getText();
		//Check nhập liệu
		if(idB.equals("")||name.equals("")||type.equals("")||gia.equals("")||idA.equals(""))
		{
			tip.setText("Không được để trống trường nào!");
			return;
		}
		//check ID Sách và ID tác giả
		KetNoiDuLieu con  = new KetNoiDuLieu();
		ResultSet resutl = con.selectOneColum("sach", "ID_Sach");
		boolean flag = false;
		while (resutl.next())
		{
			String id  = resutl.getString("ID_Sach");
			if(id.equals(idB))
			{
				flag = true;
				break;
			}
		}
		if(flag == false)
		{
			tip.setText("ID sách không có trong CSDL!");
			
			return;
		}
		flag = false;
		resutl.close();
		
		resutl = con.selectOneColum("tacgia", "ID_TacGia");
		
		while (resutl.next())
		{
			String id  = resutl.getString("ID_TacGia");
			if(id.equals(idB))
			{
				flag = true;
				break;
			}
		}
		if(flag == false)
		{
			tip.setText("ID tác giả không có trong CSDL!");
			
			return;
		}
		flag = false;
		resutl.close();
		//Sua thong tin sach
		String sql = "UPDATE `librarydb`.`sach` SET `TenSach`='" + name + "', `LoaiSach`='" + type + "', "
                + "`Gia`='" + gia + "', `TacGia_ID_TacGia`='" + idA + "' WHERE `ID_Sach`='" + idB + "';";
		Statement statement = con.getConn().createStatement();
		int n= statement.executeUpdate(sql);
		if(n==1)
		{
			System.out.println("Sửa sách thành công!");
			nowStage.close();
		}
		else
		{
			tip.setText("Sửa sách không thành công!");
		}
	}
	@FXML
	private void cancel()
	{
		nowStage.close();
	}
}
