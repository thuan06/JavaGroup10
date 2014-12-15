package application;

import javafx.beans.property.SimpleStringProperty;

public class Book {
	
	private final SimpleStringProperty ID_Book;
	private final SimpleStringProperty Name;
	private final SimpleStringProperty Type;
	private final SimpleStringProperty Status;
	private final SimpleStringProperty ID_Author;
	
	public Book (String idB, String n, String t, String s, String idA)
	{
		ID_Book = new SimpleStringProperty(idB);
		Name = new SimpleStringProperty(n);
		Type =  new SimpleStringProperty(t);
		Status = new SimpleStringProperty(s);
		ID_Author = new SimpleStringProperty(idA);
		
	}

	public SimpleStringProperty getID_Book() {
		return ID_Book;
	}

	public SimpleStringProperty getName() {
		return Name;
	}

	public SimpleStringProperty getType() {
		return Type;
	}

	public SimpleStringProperty getStatus() {
		return Status;
	}

	public SimpleStringProperty getID_Author() {
		return ID_Author;
	}
	
}
