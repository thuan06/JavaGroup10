package application;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class KetNoiDuLieu {

    private Connection connection = null;
    private ResultSet resultSet;
    private Statement statement;
    private String sql;
    public KetNoiDuLieu() {
        String drv = "com.mysql.jdbc.Driver";
      //  connection = null;
        try {
            Class.forName(drv);
            String user = "root";
            String pass = "12345";
            String url = "jdbc:mysql://127.0.0.1:3306/librarydb";
            connection =  DriverManager.getConnection(url, user, pass);
            System.out.println("Ket noi thanh cong");

        } catch( ClassNotFoundException | SQLException e){
            System.out.println("Loi xay ra"+ e );
        } 
    }
// tra ve du lieu 1 bang
    public ResultSet selectAll(String tableName) throws SQLException{
        sql = "SELECT * FROM " + tableName;
        statement = (Statement) connection.createStatement();
        resultSet = statement.executeQuery(sql);
        return resultSet;
    }
    // tra va du lieu 1 cot
     public ResultSet selectOneColum(String tableName, String colName) throws SQLException{
        sql = "SELECT " + colName + " FROM " + tableName;
        statement = (Statement) connection.createStatement();
        resultSet = statement.executeQuery(sql);
        return resultSet;
    }
     // tra ve du lieu 1 hang
    public ResultSet selectOneRow(String nameTable, String colName, String keyWord) throws SQLException{
        sql = "SELECT * FROM " + nameTable + " WHERE " + nameTable + "." + colName + " LIKE '%" + keyWord + "%'" ; 
        statement = (Statement) connection.createStatement();
        resultSet = statement.executeQuery(sql);
        return resultSet;
    }
    // tra ve du lieu hon hop
    public ResultSet selectThongTinSach() throws SQLException{
        sql = "SELECT sach.ID_Sach, sach.TenSach, sach.LoaiSach, sach.TinhTrang,"
                + " sach.TacGia_ID_TacGia, tacgia.TenTacGia, xuatban.ID_XuatBan"
                + "FROM sach,tacgia,xuatban"
                + "WHERE (sach.ID_Sach = xuatban.Sach_ID_Sach) AND (tacgia.ID_TacGia = sach.TacGia_ID_TacGia)"
                + "ORDER BY sach.TenSach ASC";
        statement = (Statement) connection.createStatement();
        resultSet = statement.executeQuery(sql);
        return resultSet;
    }
     public ResultSet selectThongTinSachMuonTra(String idMuonTra) throws SQLException{
        sql = "SELECT sach.ID_Sach, sach.TenSach, sach.LoaiSach, muontra.ID_MuonTra FROM sach,muontra,sach_has_muontra WHERE (sach.ID_Sach = sach_has_muontra.Sach_ID_Sach) AND (sach_has_muontra.MuonTra_ID_MuonTra = muontra.ID_MuonTra) AND (muontra.ID_MuonTra = '"+idMuonTra+"') ORDER BY sach.TenSach ASC";
        statement = (Statement) connection.createStatement();
        resultSet = statement.executeQuery(sql);
        return resultSet;
    }
    public Connection getConn(){
       return connection;
    }
    
    public static void main(String[] args) throws SQLException {
        KetNoiDuLieu a = new KetNoiDuLieu();
        try{
        ResultSet b = a.selectAll("sach");
        ResultSet c = a.selectOneColum("docgia", "TenDocGia");
        ResultSet d = a.selectOneRow("taikhoandangnhap", "HoTen", "minh");
        while(b.next()){
          String idSach = b.getString("ID_Sach");
          String tenSach = b.getString("TenSach");
          System.out.println("ID_Sach "+ idSach + "Tên sách là : "+ tenSach); 
        }
        while(c.next()){          
          String tenDocGia = c.getString("TenDocGia");
          System.out.println("Ten doc gia la : "+ tenDocGia); 
        }
        while(d.next()){          
          String hoTen = d.getString("HoTen");
          System.out.println("Ho ten tai khoan dang nhap la : "+ hoTen); 
        }
        } catch ( Exception e){
            e.printStackTrace();
        }
       
    }
}