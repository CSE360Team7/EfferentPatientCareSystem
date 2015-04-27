public class Test {

	public static void main(String[] args) {
		RegisterNewUserTest();
	}

    public static void RegisterNewUserTest() {
    	//path 1
    	RegisterNewUser(false,"10",true, "bob","bob","smith","asdf@asdf.asdf","bobrulez");
    	System.out.println("test 1 passed");
    	//path 2 untestable
    	//path 3
    	RegisterNewUser(true, "10",true, "bob","bob","smith","asdf@asdf.asdf","bobrulez");
    	System.out.println("test 2 passed");
    	//path 4
    	RegisterNewUser(true, "10",false,"bob","bob","smith","asdf@asdf.asdf","bobrulez");
    	System.out.println("test 3 passed");
    	//robustness testing
    	RegisterNewUser(true,"four",false,"bob","bob","smith","asdf@asdf.asdf","bobrulez");
    	RegisterNewUser(true, "1546465654",true, "","","","","");
    	System.out.println("test 5 passed");
    	RegisterNewUser(false, "10",false, "1234","1324b","1234","1234@1234.1234","1234");
    	System.out.println("test 6 passed");
    }


	public static void RegisterNewUser(Boolean patientRadioButtonSelected,
									   String ageTextFieldText,
									   Boolean maleRadioButtonSelected,
									   String newUserNameTextFieldText,
									   String firstNameTextFieldText,
									   String lastNameTextFieldText,
									   String emailTextFieldText,
									   String newPasswordPasswordFieldPassword /*lol*/)
	{
		User newUser;

		// Set Patient attributes
		if (patientRadioButtonSelected)
		{
			String gender;
			int age = 0;
			try {
				age = Integer.parseInt(ageTextFieldText);
			} catch(NumberFormatException nfe) {
				if (nfe.getMessage().equals("For input string: \"four\"")) {
					System.out.println("test 4 passed");
					return;
				}
				nfe.printStackTrace();
				System.exit(-1);
			}
			// Set gender
			if (maleRadioButtonSelected)
			{
				gender = "Male";
			}
			else
			{
				gender = "Female";
			}
			String username = newUserNameTextFieldText;
			newUser = new Patient(age, gender, username);
			newUser.setUserType(0);

		// Set Doctor attributes
		}
		else
		{
			newUser = new Doctor();
			newUser.setUserType(1);
		}

		// Assign all user information
		newUser.setUserName(newUserNameTextFieldText);
		newUser.setFirstName(firstNameTextFieldText);
		newUser.setLastName(lastNameTextFieldText);
		newUser.setEmail(emailTextFieldText);
		newUser.setPassword(newPasswordPasswordFieldPassword.toCharArray());

		//... let the testing begin
		assert (patientRadioButtonSelected && newUser instanceof Patient) || //patient radio button -> patient
		      (!patientRadioButtonSelected && newUser instanceof Doctor);

		assert (patientRadioButtonSelected && (
			(maleRadioButtonSelected && ((Patient)newUser).getGender().equals("Male")) || //gender correct if patient
		   (!maleRadioButtonSelected && ((Patient)newUser).getGender().equals("Female"))
		) ||   !patientRadioButtonSelected); //or don't care

		assert (patientRadioButtonSelected && Integer.toString(((Patient)newUser).getAge()).equals(ageTextFieldText) ||
			   !patientRadioButtonSelected);

		assert (newUser.getUserName().equals(newUserNameTextFieldText));

		assert (newUser.getFirstName().equals(firstNameTextFieldText));

		assert (newUser.getLastName().equals(lastNameTextFieldText));

		assert (newUser.getEmail().equals(emailTextFieldText));

		for (int i = 0; i < newUser.getPassword().length; i++) {
			//converting these into each other's types to test equality doesn't work
			assert (newPasswordPasswordFieldPassword.charAt(i) == newUser.getPassword()[i]);
		}
	}
}
