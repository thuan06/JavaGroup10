package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.KetNoiDuLieu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditAuthorController extends Pane{
	private Stage nowStage;
	public EditAuthorController(Stage stage)
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Layouts/EditAuthorGUI.fxml"));
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
	private TextField ID;
	@FXML
	private TextField Name;
	@FXML
	private TextField Birth;
	@FXML
	private Label tip;
	@FXML
	private void edit() throws SQLException
	{
		String id = ID.getText();
		String name = Name.getText();
		String b = Birth.getText();
		//Kiểm tra nhập liệu
		if(id.equals("")||name.equals("")||b.equals(""))
		{
			tip.setText("Không được để trống trường nào!");
			return;
		}
		//Kiểm tra ID
		KetNoiDuLieu con = new KetNoiDuLieu();
		ResultSet result = con.selectOneColum("tacgia", "ID_TacGia");
		boolean flag = false;
		while (result.next())
		{
			String i = result.getString("ID_TacGia");
			if(i.equals(id))
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
		result.close();
		//Sửa tác giả
		String sql = "UPDATE `librarydb`.`tacgia` SET `TenTacGia`='" + name + "', `NamSinh`='" + b + "'"
                + " WHERE `ID_TacGia`='" + id + "';";
		Statement statement = con.getConn().createStatement();
		int n= statement.executeUpdate(sql);
		if(n==1)
		{
			System.out.println("Sửa tác giả thành công!");
			nowStage.close();
		}
		else
		{
			tip.setText("Sửa tác giả không thành công!");
		}
		
					
				
	}
	@FXML
	private void cancel()
	{
		nowStage.close();
	}
}
