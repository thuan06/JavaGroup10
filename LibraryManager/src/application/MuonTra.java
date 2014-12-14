package application;

import javafx.beans.property.SimpleStringProperty;

public class MuonTra {

	private final SimpleStringProperty ID_MuonTra;
	private final SimpleStringProperty ID_DocGia;
	private final SimpleStringProperty ID_Sach;
	private final SimpleStringProperty NgayMuon;
	private final SimpleStringProperty NgayTra;
	
	public MuonTra(String m, String d, String s, String nm, String nt)
	{
		ID_MuonTra = new SimpleStringProperty(m);
		ID_DocGia = new SimpleStringProperty(d);
		ID_Sach = new SimpleStringProperty(s);
		NgayMuon = new SimpleStringProperty(nm);
		NgayTra = new SimpleStringProperty(nt);
	}

	public SimpleStringProperty getID_MuonTra() {
		return ID_MuonTra;
	}

	public SimpleStringProperty getID_DocGia() {
		return ID_DocGia;
	}

	public SimpleStringProperty getID_Sach() {
		return ID_Sach;
	}

	public SimpleStringProperty getNgayMuon() {
		return NgayMuon;
	}

	public SimpleStringProperty getNgayTra() {
		return NgayTra;
	}
}
