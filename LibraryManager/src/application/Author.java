package application;

import javafx.beans.property.SimpleStringProperty;

public class Author {

	private final SimpleStringProperty ID_Author;
	private final SimpleStringProperty Name;
	private final SimpleStringProperty Birth;
	
	public Author(String id, String n, String b)
	{
		ID_Author = new SimpleStringProperty(id);
		Name =  new SimpleStringProperty(n);
		Birth =  new SimpleStringProperty(b);
	}

	public SimpleStringProperty getID_Author() {
		return ID_Author;
	}

	public SimpleStringProperty getName() {
		return Name;
	}

	public SimpleStringProperty getBirth() {
		return Birth;
	}
	
}
