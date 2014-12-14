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

public class AddReaderController extends Pane{
	private Stage nowStage;
	public AddReaderController(Stage stage)
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Layouts/AddReaderGUI.fxml"));
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
	Label tip;
	@FXML
	private void add() throws SQLException
	{
		String id = ID.getText();
		String name = Name.getText();
		String job = Job.getText();
		String date = Date.getText();
		KetNoiDuLieu connection = new KetNoiDuLieu();
		Statement statement;
		ResultSet result;
		//Kiểm tra dữ liệu nhập vào
		if(id.equals("")||name.equals("")||job.equals("")||Date.equals(""))
		{
			tip.setText("Không được để trống trường nào");
			return;
		}
		//Kiểm tra ID độc giả
		result = connection.selectOneColum("docgia", "ID_DocGia");
		while (result.next())
		{
			String Id = result.getString("ID_DocGia");
			if(Id.equalsIgnoreCase(id))
			{
				tip.setText("ID Độc giả đã tồn tại!");
				return;
			}
		}
		//Thêm tác giả vào CSDL
				String sql = "INSERT INTO `librarydb`.`docgia` "
			   	         + "(`ID_DocGia`, `TenDocGia`, `NgheNghiep`, `NgayCapThe`)"
			   	         + "VALUES ('"+id+"','" + name +"','"+job+"','"+date+"');";
				statement = (Statement) connection.getConn().createStatement();
				int n = statement.executeUpdate(sql);
				if(n==1)
				{
					System.out.println("Thêm Độc giả thành công!");
					nowStage.close();
				}
				else
				{
					System.out.println("Thêm Độc giả không thành công!");
					return;
				}
	}
	@FXML
	private void cancel()
	{
		nowStage.close();
	}
}
