package Controller;
import java.io.IOException;






import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.KetNoiDuLieu;
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

public class RegisterController extends Pane{
	@FXML
	private TextField loginName;
	@FXML
	private PasswordField pass;
	@FXML
	private PasswordField repass;
	@FXML
	private TextField Name;
	@FXML
	private Label tip;
	String _loginName, _pass, _repass, _Name;
	private Stage nowStage;
	
	public RegisterController(Stage stage)
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Layouts/RegisterGUI.fxml"));
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
	private void adduser() throws SQLException
	{
		Statement statement;
	    ResultSet result;
	    KetNoiDuLieu connection = new KetNoiDuLieu();
	    _loginName = loginName.getText();
        _pass = pass.getText();
        _repass = repass.getText();
        _Name = Name.getText();
	    //Kiểm tra việc trống trường
	    if(_loginName.equals("")||_pass.equals("")||_Name.equals(""))
	    {
	    	tip.setText("Tất cả các trường đều phải được nhập!");
	    	return;
	    }
		//Kiem tra tinh duy nhat cua ten tai khoan
	    result = connection.selectOneColum("taikhoandangnhap", "TenDangNhap");
        while (result.next()) {
            String tenDangNhap = result.getString("TenDangNhap");
            if (tenDangNhap.equalsIgnoreCase(_loginName)) {
                tip.setText("Tên tài khoản này đã tồn tại!");
                return;
            }
          //Kiem tra pass va repass
            
            
        }
        if(!_pass.equals(_repass))
        {
        	tip.setText("Mật khẩu nhập lại không trùng khớp!");
        	return;
        }
        //thêm tài khoản
        String sqlInsert = "INSERT INTO `librarydb`.`taikhoandangnhap` "
   	         + "(`TenDangNhap`, `MatKhau`, `HoTen`)"
   	         + "VALUES ('"+_loginName+"','" + _pass +"','"+_Name+"');";
        statement = (Statement) connection.getConn().createStatement();
        int n = statement.executeUpdate(sqlInsert);
        if (n == 1) {
            
        	
            System.out.println( "Thêm tài khoản người dùng thành công!");
            nowStage.close();
            
        }else {
            tip.setText("Thêm không thành công!Kiểm tra lại dữ liệu nhập vào.");
        }
        try {
            if (result!= null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.getConn().close();
            }
          } catch (Exception e) {
            System.out.println("Lỗi xảy ra " + e);
          }
	}
	@FXML
	private void cancel()
	{
		nowStage.close();
	}
	

}
