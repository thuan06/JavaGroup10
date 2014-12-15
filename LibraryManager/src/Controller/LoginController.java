package Controller;

import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import application.KetNoiDuLieu;
import application.Main;
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

public class LoginController extends Pane{
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
	public LoginController(Stage stage)
	{
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Layouts/LoginGUI.fxml"));
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
	private void registerClick() throws Exception
	{
		new Register().start(new Stage());
	}
	@FXML
	private void loginClick() throws Exception
	{
		acc = accName.getText();
		password = pass1.getText();
		
		boolean flag = false;
		//Kiem tra nhap lieu
		if(acc.equals("")||password.equals(""))
		{
			tip.setText("Không được để trống trường nào!");
			return;
		}
		//Kiem tra tai khoan va mat khau
		String sql = "SELECT * FROM taikhoandangnhap";
		try{
		state = connection.getConn().createStatement();
		result = state.executeQuery(sql);
		
		//result = connection.selectOneColum("taikhoandangnhap", "TenDangNhap");
		while (result.next())
		{
			String tenDangNhap = result.getString("TenDangNhap");
			if (tenDangNhap.equalsIgnoreCase(acc)) {
	               flag = true;
	               break;
	            }
			else flag = false;
		}
		if(flag == false)
		{
			tip.setText("Tên đăng nhập không tồn tại!");
			return;
		}
		else{
		//Kiem tra mat khau
			
			String matkhau = null;			
			matkhau = result.getString("MatKhau");
			if(matkhau.equalsIgnoreCase(password))
			{
				System.out.println(acc+" đăng nhập thành công!");
				new Main().start(new Stage());
				nowStage.close();
				
			}
			else
			{
				tip.setText("Sai mật khẩu!");
			}
			
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
