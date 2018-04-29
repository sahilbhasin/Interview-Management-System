package interviewmgmtsystem;

public class User {

	private String username;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private String role;
	private int userid;
	
	public User() { }
	
	public void setUserName(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}
	public void setLastName(String lastname) {
		this.lastname = lastname;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setUserID(int userid) {
		this.userid = userid;
	}		
	public String getRole() {
		return this.role;
	}
	public String getUserName() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	public String getEmail() {
		return this.email;
	}
	public String getFirstName() {
		return this.firstname;
	}
	public String getLastName() {
		return this.lastname;
	}	
	public int getUserID() {
		return this.userid;
	}	
	
	public boolean validate(String password) {
		if(password.equals(this.password)) {
			return true;
		}
		return false;
	}
	
}
