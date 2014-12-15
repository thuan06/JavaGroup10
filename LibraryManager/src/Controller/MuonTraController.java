package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Book;
import application.DataTable;
import application.DocGia;
import application.KetNoiDuLieu;
import application.Search;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MuonTraController extends Pane{
	
	private Stage nowStage;
	public MuonTraController(Stage stage)
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Layouts/MuonSachGUI.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        nowStage = stage;
        try {
            fxmlLoader.load();
            initTable();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        
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
	private TableView<DocGia> DocGiaTable;
	@FXML
	private TableColumn<DocGia, String> ID_DocGia;
	@FXML
	private TableColumn<DocGia, String> nameDocGia;
	@FXML
	private TableColumn<DocGia, String> job;
	@FXML
	private TableColumn<DocGia, String> NgayCap;
	
	private void initTable()
	{
		
			  //init Book table
				idBook.setCellValueFactory(cellData -> cellData.getValue().getID_Book());
		        nameBook.setCellValueFactory(cellData -> cellData.getValue().getName());
		        typeBook.setCellValueFactory(cellData -> cellData.getValue().getType());
		        statusBook.setCellValueFactory(cellData -> cellData.getValue().getStatus());
		        Book_ID_Author.setCellValueFactory(cellData -> cellData.getValue().getID_Author());
		        BookTable.setItems(new DataTable().getBookData());
		        
		      //init DocGia Table
		        ID_DocGia.setCellValueFactory(cellData -> cellData.getValue().getID_DocGia());
		        nameDocGia.setCellValueFactory(cellData -> cellData.getValue().getHoTen());
		        job.setCellValueFactory(cellData -> cellData.getValue().getNgheNghiep());
		        NgayCap.setCellValueFactory(cellData -> cellData.getValue().getNgayCapThe());
		        DocGiaTable.setItems(new DataTable().getDocGiaData());
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
	private TextField idMuon;
	@FXML
	private TextField idDocGia;
	@FXML
	private TextField idSach;
	@FXML
	private TextField ngaymuon;
	@FXML
	private TextField ngaytra;
	@FXML
	private Label tip;
	@FXML
	private void muonSachClick() throws SQLException
	{
		//Kiem tra nhap lieu
		if(idMuon.getText().equals("")||idDocGia.getText().equals("")||
		   idSach.getText().equals("")||ngaymuon.getText().equals("")||	
		   ngaytra.getText().equals("")	)
		{
			tip.setText("Phải nhập tất cả các trường!");
			return;
		}
		//Kiem tra ID muontra
		KetNoiDuLieu con = new KetNoiDuLieu();
		Statement statement;
		ResultSet result;
		result = con.selectOneColum("muontra", "ID_MuonTra");
		while(result.next())
		{
			String id = result.getString("ID_MuonTra");
			if(id.equalsIgnoreCase(idMuon.getText()))
			{
				tip.setText("ID Mượn Trả đã tồn tại!");
				return;
			}
		}
		result.close();
		//Kiem tra ID Doc gia
		result = con.selectOneColum("docgia", "ID_DocGia");
		boolean flag = false;
		while ( result.next())
		{
			String id = result.getString("ID_DocGia");
			if(id.equalsIgnoreCase(idDocGia.getText()))
			{
				flag = true;
				break;
			}
		}
		if(flag == false)
		{
			tip.setText("ID Độc giả không có trong CSDL!");
			return;
		}
		flag = false;
		result.close();
		
		//Kiem tra ID Sach
		result = con.selectOneColum("sach", "ID_Sach");
		while (result.next())
		{
			String id = result.getString("ID_Sach");
			if(id.equalsIgnoreCase(idSach.getText()))
			{
				flag = true;
				break;
			}
		}
		if(flag == false)
		{
			tip.setText("ID Sách không có trong CSDL!");
			return;
		}
		result.close();
		String sql = "INSERT INTO `librarydb`.`muontra` "
	   	         + "(`ID_MuonTra`, `DocGia_ID_DocGia`, `Sach_ID_Sach`,`NgayMuon`,`NgayTra`)"
	   	         + "VALUES ('"+idMuon.getText()+"','" + idDocGia.getText() +"','"+idSach.getText()+"','"
	   	         + ngaymuon.getText() +"','"+ ngaytra.getText()+"');";
		statement = con.getConn().createStatement();
		int n = statement.executeUpdate(sql);
		if(n==1)
		{
			System.out.println("Mượn sách thành công!");
			nowStage.close();
		}
		else
		{
			tip.setText("Mượn sách không thành công!");
			return;
		}
	}
	@FXML
	private void cancel()
	{
		nowStage.close();
	}
}
