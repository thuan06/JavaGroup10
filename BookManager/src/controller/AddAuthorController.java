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

public class AddAuthorController extends Pane{
	private Stage nowStage;
	@FXML
	private TextField id;
	@FXML
	private TextField name;
	@FXML
	private TextField birth;
	@FXML
	private Label tip;
	
	public AddAuthorController(Stage stage)
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Layouts/AddAuthorGUI.fxml"));
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
	private void add() throws SQLException
	{
		String ID = id.getText();
		String Name = name.getText();
		String Birth = birth.getText();
		
		KetNoiDuLieu connection = new KetNoiDuLieu();
		Statement statement;
		ResultSet result;
		//Kiem tra nhap lieu
		if(ID.equals("")||Name.equals("")||Birth.equals(""))
		{
			tip.setText("Không được để trống trường nào!");
			return;
		}
		//Check ID
		result = connection.selectOneColum("tacgia", "ID_TacGia");
		while (result.next())
		{
			String idAu = result.getString("ID_TacGia");
			if(idAu.equalsIgnoreCase(ID))
			{
				tip.setText("ID Tác giả đã tồn tại!");
				return;
			}
		}
		//Thêm tác giả vào CSDL
		String sql = "INSERT INTO `databasebookmanager`.`tacgia` "
	   	         + "(`ID_TacGia`, `TenTacGia`, `NamSinh`)"
	   	         + "VALUES ('"+ID+"','" + Name +"','"+Birth+"');";
		statement = (Statement) connection.getConn().createStatement();
		int n = statement.executeUpdate(sql);
		if(n==1)
		{
			System.out.println("Thêm Tác giả thành công!");
			nowStage.close();
		}
		else
		{
			System.out.println("Thêm Tác giả không thành công!");
			return;
		}
	}
	
	@FXML
	private void cancel()
	{
		nowStage.close();
		
	}
}
