
public class Patient extends User
{
	private int age, patientID;
	private String gender;
	
	Patient()
	{
		age = -1;
		gender = "";
		patientID = -1;
	}
	
	Patient(int a, String g , String username)
	{
		age = a;
		gender = g;
		patientID = generatePatientID(username);
	}
	
	int generatePatientID(String username)
	{
		int keyGen = 0;
		char[] name = username.toCharArray();
		for(int i = 0; i<name.length; i++)
		{
			keyGen += name[i] * i;
		}
		return keyGen;
	}
	
	void setAge(int a){
		age = a;
	}
	
	void setGender(String a){
		gender = a;
	}
	
	void setID(int a)
	{
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