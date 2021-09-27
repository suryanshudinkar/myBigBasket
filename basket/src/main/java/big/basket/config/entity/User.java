package big.basket.config.entity;

import javax.persistence.Entity;

@Entity
public class User {
	
	private int id;
	private String name;
    private String username;
    private String password;
    private boolean adminStatus;
    
    public User() {}

    
	public User(int id, String name, String username, String password, boolean adminStatus) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.adminStatus = adminStatus;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(boolean adminStatus) {
		this.adminStatus = adminStatus;
	}

    
}
