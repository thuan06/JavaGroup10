package application;

public class AccountAttribute {
	
	private String accName;
    private String password;
    private String name;
    
    public AccountAttribute(String acc, String pass, String name)
    {
    	accName = acc;
    	password = pass;
    	this.name = name;
    }

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}
