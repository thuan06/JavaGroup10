package application;

import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataTable {

	private ObservableList<Book> BookData = FXCollections.observableArrayList();
	private ObservableList<Author> AuthorData = FXCollections.observableArrayList();
	private ObservableList<DocGia> DocGiaData = FXCollections.observableArrayList();
	private ObservableList<MuonTra> MuonTraData = FXCollections.observableArrayList();
	KetNoiDuLieu con = new KetNoiDuLieu();
	public ObservableList<Book> getBookData()
	{
		
		String sql = "SELECT * FROM sach";
		try
		{
			Statement statement = con.getConn().createStatement();
			ResultSet result = statement.executeQuery(sql);
			Book book;
			while (result.next())
			{
				
				book = new Book (result.getString("ID_Sach"),result.getString("TenSach"), result.getString("LoaiSach")
								, result.getString("Gia"),result.getString("TacGia_ID_TacGia"));
				
				BookData.add(book);
			}
			
			result.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	
		return BookData;
	}
	
	public ObservableList<Author> getAuthorData()
	{
		String sql = "SELECT * FROM tacgia";
		try
		{
			Statement statement = con.getConn().createStatement();
			ResultSet result = statement.executeQuery(sql);
			Author author;
			while (result.next())
			{
				author = new Author(result.getString("ID_TacGia"), result.getString("TenTacGia"), result.getString("NamSinh"));
				AuthorData.add(author);
			}
			
			result.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	
		return AuthorData;
	}
	public ObservableList<DocGia> getDocGiaData()
	{
		String sql = "SELECT * FROM docgia";
		try
		{
			Statement statement = con.getConn().createStatement();
			ResultSet result = statement.executeQuery(sql);
			DocGia docgia;
			while (result.next())
			{
				docgia = new DocGia(result.getString("ID_DocGia"), result.getString("TenDocGia"), result.getString("NgheNghiep"), result.getString("NgayCapThe"));
				DocGiaData.add(docgia);
			}
			
			result.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	
		return DocGiaData;
	}
	public ObservableList<MuonTra> getMuonTraData()
	{
		String sql = "SELECT * FROM muontra";
		try
		{
			Statement statement = con.getConn().createStatement();
			ResultSet result = statement.executeQuery(sql);
			MuonTra muontra;
			while (result.next())
			{
				muontra = new MuonTra(result.getString("ID_MuonTra"), result.getString("DocGia_ID_DocGia"), result.getString("Sach_ID_Sach"), result.getString("NgayMuon"), result.getString("NgayTra"));
				MuonTraData.add(muontra);
			}
			
			result.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	
		return MuonTraData;
	}
}
