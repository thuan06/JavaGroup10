package application;

import javafx.beans.property.SimpleStringProperty;

public class DocGia {

	private final SimpleStringProperty ID_DocGia;
	private final SimpleStringProperty HoTen;
	private final SimpleStringProperty NgheNghiep;
	private final SimpleStringProperty NgayCapThe;
	
	public DocGia(String id, String name, String nghe, String ngay)
	{
		ID_DocGia = new SimpleStringProperty(id);
		HoTen = new SimpleStringProperty(name);
		NgheNghiep = new SimpleStringProperty(nghe);
		NgayCapThe = new SimpleStringProperty(ngay);
	}

	public SimpleStringProperty getID_DocGia() {
		return ID_DocGia;
	}

	public SimpleStringProperty getHoTen() {
		return HoTen;
	}

	public SimpleStringProperty getNgheNghiep() {
		return NgheNghiep;
	}

	public SimpleStringProperty getNgayCapThe() {
		return NgayCapThe;
	}
	
}
