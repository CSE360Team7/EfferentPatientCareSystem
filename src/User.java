
public abstract class User {

	private String firstName;
	private String lastName;
	private String userName;
	private String emailAddress;
	private char[] password;
	private int userType;
	
	User(){
		firstName = "";
		lastName = "";
		userName = "";
		emailAddress = "";
		password = null;
		userType = -1;
	}
	
	User(String fn, String ln, String un, String e, char[] p){
		firstName = fn;
		lastName = ln;
		userName = un;
		emailAddress = e;
		password = p;
	}


	void setFirstName(String a){
		firstName = a;
	}
	
	void setLastName(String a){
		lastName = a;
	}

	void setUserName(String a){
		userName = a;
	}

	void setEmail(String a){
		emailAddress = a;
	}

	void setPassword(char[] a){
		password = a;
	}
	
	void setUserType(int a){
		userType = a;
	}

	String getFirstName(){
		return firstName;
	}

	String getLastName(){
		return lastName;
	}

	String getUserName(){
		return userName;
	}

	String getEmail(){
		return emailAddress;
	}

	char[] getPassword(){
		return password;
	}

	int getUserType(){
		return userType;
	}

}