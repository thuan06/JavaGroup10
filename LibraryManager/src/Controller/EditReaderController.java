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
public class EditReaderController extends Pane{
	private Stage nowStage;
	public EditReaderController(Stage stage)
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Layouts/EditReaderGUI.fxml"));
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
	private TextField Job;
	@FXML
	private TextField Date;
	@FXML
	private Label tip;
	@FXML
	private void edit() throws SQLException
	{
		String id = ID.getText();
		String name = Name.getText();
		String job = Job.getText();
		String date = Date.getText();
		
		//Kiem tra nhap lieu
		if(id.equals("")||name.equals("")||job.equals("")||date.equals(""))
		{
			tip.setText("Không được bỏ trống trường nào!");
			return;
		}
		//Kiem tra ID
		KetNoiDuLieu con = new KetNoiDuLieu();
		ResultSet result = con.selectOneColum("docgia", "ID_DocGia");
		boolean flag = false;
		while (result.next())
		{
			String i = result.getString("ID_DocGia");
			if(i.equals(id))
			{
				flag = true;
				break;
			}
		}
		if(flag == false)
		{
			tip.setText("ID độc giả không có trong CSDL!");
			return;
		}
		flag = false;
		result.close();
		
		//Sửa độc giả
		String sql = "UPDATE `librarydb`.`docgia` SET `TenDocGia`='"+name+"', "
                + "`NgheNghiep`='"+job+"', `NgayCapThe`='"+date+"' WHERE `ID_DocGia`='"+id+"';";
		Statement statement = con.getConn().createStatement();
		int n= statement.executeUpdate(sql);
		if(n==1)
		{
			System.out.println("Sửa độc giả thành công!");
			nowStage.close();
		}
		else
		{
			tip.setText("Sửa độc giả không thành công!");
		}
		
	}
	@FXML
	private void cancel()
	{
		nowStage.close();
		
	}
}
