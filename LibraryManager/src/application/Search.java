package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Search {

	private ObservableList<Book> BookData = FXCollections.observableArrayList();
	private ObservableList<Author> AuthorData = FXCollections.observableArrayList();
	public ObservableList<Book> searchBook (String name) throws SQLException
	{
		KetNoiDuLieu con = new KetNoiDuLieu();
		//Kiem tra ten sach trong CSDL
		ResultSet result = con.selectOneColum("sach", "TenSach");
		boolean flag =false;
		while(result.next())
		{
			String ten = result.getString("TenSach");
			if(ten.equalsIgnoreCase(name))
			{
				flag = true;
				break;
			}
		}
		if(flag == false)
		{
			System.out.println("Tên sách không có trong CSDL!");
			return null;
		}
		result.close();
		//Lay thong tin sach
		result = con.selectOneRow("sach", "TenSach", name);
		while (result.next())
		{
			Book book = new Book(result.getString("ID_Sach"),result.getString("TenSach"), result.getString("LoaiSach")
					, result.getString("Gia"), result.getString("TacGia_ID_TacGia"));
			BookData.add(book);
		}
		result.close();
		return BookData;
	}
	public ObservableList<Author> searchAuthor(String name) throws SQLException
	{
		KetNoiDuLieu con = new KetNoiDuLieu();
		//Kiem tra ten sach trong CSDL
		ResultSet result = con.selectOneColum("tacgia", "TenTacGia");
		boolean flag =false;
		while(result.next())
		{
			String ten = result.getString("TenTacGia");
			if(ten.equalsIgnoreCase(name))
			{
				flag = true;
				break;
			}
		}
		if(flag == false)
		{
			System.out.println("Tên tác giả không có trong CSDL!");
			return null;
		}
		result.close();
		//Lay thong tin sach
		result = con.selectOneRow("tacgia", "TenTacGia", name);
		while (result.next())
		{
			Author author = new Author(result.getString("ID_TacGia"), result.getString("TenTacGia"), result.getString("NamSinh"));
			AuthorData.add(author);
		}
		result.close();
		return AuthorData;
	}
}
