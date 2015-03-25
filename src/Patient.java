
public class Patient extends User{

	private int age, patientID;
	private String gender;
	
	Patient(){
		age = -1;
		gender = "";
		patientID = -1;
	}
	
	Patient(int a, String g, int pID){
		age = a;
		gender = g;
		patientID = pID;
	}
	
	void setAge(int a){
		age = a;
	}
	
	void setGender(String a){
		gender = a;
	}
	
	void setID(int a){
		patientID = a;
	}
	
	int getAge(){
		return age;
	}
	
	String getGender(){
		return gender;
	}

	int getID(){
		return patientID;
	}
	
}