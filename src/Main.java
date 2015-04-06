import javax.swing.*;

import java.awt.event.*;
import java.io.*;
import java.io.File;

import jxl.*;
import jxl.write.*;
import jxl.read.biff.*;

public class Main implements ActionListener{

	// Global variables
	JFrame loginWindow = new JFrame("Login");
	JTextField userNameTextField = new JTextField("");
	JPasswordField passwordPasswordField = new JPasswordField("");
	
	JFrame registerWindow = new JFrame("Register");
	JTextField newUserNameTextField = new JTextField("");
	JTextField firstNameTextField = new JTextField("");
	JTextField lastNameTextField = new JTextField("");
	JTextField emailTextField = new JTextField("");
	JPasswordField newPasswordPasswordField = new JPasswordField("");
	JPasswordField confirmPasswordPasswordField = new JPasswordField("");
	
	JRadioButton patientRadioButton = new JRadioButton("Patient");
	JRadioButton doctorRadioButton = new JRadioButton("Practitioner");

	JLabel ageLabel = new JLabel("Age");
	JTextField ageTextField = new JTextField("");
	
	JRadioButton maleRadioButton = new JRadioButton("Male");
	JRadioButton femaleRadioButton = new JRadioButton("Female");	
	
	JButton registerButton = new JButton("Register");
	
	
	public static void main (String[] args) {
		Main mainInstance = new Main();
		mainInstance.LoginWindow();
	}
	
	public void LoginUser() throws BiffException, IOException, WriteException{
		
		JPanel alert = new JPanel();
		
		// Define file name
		String fileName = userNameTextField.getText() + ".xls";
		
		// Try to read excel file
		try{
        Workbook workbook = Workbook.getWorkbook(new File(fileName));
        Sheet sheet = workbook.getSheet(0);
		
        // Get username and password from file
        Cell userNameCell = sheet.getCell(0,0);
        Cell passwordCell = sheet.getCell(1,0);
        
        // Convert to string
        String userName = userNameCell.getContents();
        String password = passwordCell.getContents();
        
        // Login
        if (userName.equals(userNameTextField.getText()) && (password.equals(String.valueOf(passwordPasswordField.getPassword())))) {
    		JOptionPane.showMessageDialog(alert, "Login successful!");
    		LoginWindow();
    		
        } else {
        	JOptionPane.showMessageDialog(alert, "Password incorrect.");	
        }
        	
		} catch (FileNotFoundException notFound) {
    		JOptionPane.showMessageDialog(alert, "Username invalid.");	
		}
	}
	
	public void RegisterNewUser() {
			
		User newUser;
		
		// Set Patient attributes
		if (patientRadioButton.isSelected()) {
			
			String gender;
			int patientID = 123456789;										// Patient ID TBD
			int age = Integer.parseInt(ageTextField.getText());

			// Set gender
			if (maleRadioButton.isSelected()) {
				gender = "Male";
			} else {
				gender = "Female";
			}
			
			newUser = new Patient(age, gender, patientID);

		// Set Doctor attributes
		} else {
			
			newUser = new Doctor();
		}
		
		// Assign all user information
		newUser.setUserName(newUserNameTextField.getText());
		newUser.setFirstName(firstNameTextField.getText());
		newUser.setLastName(lastNameTextField.getText());
		newUser.setEmail(emailTextField.getText());
		newUser.setPassword(newPasswordPasswordField.getPassword());
		
		// Try to create user Excel file
		try {
			createNewUserFile(newUser);
		} catch (BiffException|WriteException|IOException e) {
			e.printStackTrace();
		}
	}
	
	public void validateRegistrationInfo() throws BiffException, IOException, WriteException{
		
		JFrame alert = new JFrame();
		
		// Check for incomplete information
		if ("".equals(newUserNameTextField.getText()) ||
		   ("".equals(firstNameTextField.getText())) ||
		   ("".equals(lastNameTextField.getText())) ||
		   ("".equals(emailTextField.getText())) ||
		   ("".equals(String.valueOf(newPasswordPasswordField.getPassword()))) ||
		   ("".equals(String.valueOf(confirmPasswordPasswordField.getPassword()))) ||
		   !(patientRadioButton.isSelected() || doctorRadioButton.isSelected()) ||
		   (patientRadioButton.isSelected() && ("".equals(ageTextField.getText()) ||
				                               !(maleRadioButton.isSelected() || femaleRadioButton.isSelected())))) {
			JOptionPane.showMessageDialog(alert, "Please complete all fields.");
			return;
		}
		
		
		// Check if username is already taken
		File f = new File(newUserNameTextField.getText() + ".xls");
		if (f.exists()) {
			JOptionPane.showMessageDialog(alert, "Username already taken. Please choose another.");
			return;
		}

		//Check password equivalency
		if(!(newPasswordPasswordField.getPassword()).equals(confirmPasswordPasswordField.getPassword()))
		{
			JOptionPane.showMessageDialog(alert, "Password and Confirm password fields do not match");
			return;
		}
		
		// No discrepancies in user information, attempt to register
		RegisterNewUser();
	}
	
	public void createNewUserFile(User newUser) throws BiffException, IOException, WriteException{
		
		// Define filename
		String fileName = newUser.getUserName() + ".xls";
		
		// Create new file
        WritableWorkbook workbook = Workbook.createWorkbook(new File(fileName));
        WritableSheet sheet = workbook.createSheet(newUser.getUserName(), 0);
 
        // Add user information to cells
        Label label = new Label(0, 0, newUser.getUserName());
        sheet.addCell(label);
        label = new Label(1, 0, String.valueOf(newUser.getPassword()));
        sheet.addCell(label);
        label = new Label(2, 0, newUser.getFirstName());
        sheet.addCell(label);
        label = new Label(3, 0, newUser.getLastName());
        sheet.addCell(label);
        label = new Label(4, 0, newUser.getEmail());
        sheet.addCell(label);

        
        // Try to access Patient specific methods if user is Patient
        try{
        label = new Label(5, 0, String.valueOf(((Patient) newUser).getAge()));
        sheet.addCell(label);
        label = new Label(6, 0, String.valueOf(((Patient) newUser).getGender()));
        sheet.addCell(label);
        label = new Label(7, 0, String.valueOf(((Patient) newUser).getID()));
        sheet.addCell(label);
        } catch(ClassCastException myException) { myException.printStackTrace(); }
        
        workbook.write();
        workbook.close();

        // Show registration confirmation
		JFrame alert = new JFrame();
		JOptionPane.showMessageDialog(alert, "Registration successful!");		

		// Show login window
		LoginWindow();
	}
	
	public void LoginWindow() {
		// Login Window size
		loginWindow.setResizable(false);
		loginWindow.setSize(300, 200);
		
		// Set center startup
		loginWindow.setLocationRelativeTo(null);
		
		// Set no layout
		loginWindow.setLayout(null);
				
		// UserName Label
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setBounds(50, 25, 75, 25);
		loginWindow.add(userNameLabel);
		
		// Password Label
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(50, 60, 75, 25);
		loginWindow.add(passwordLabel);
		
		// New User Label
		JLabel newUserLabel = new JLabel("New User? Click Register!");
		newUserLabel.setBounds(65, 130, 150, 25);
		loginWindow.add(newUserLabel);
		
		// UserName Text Field
		userNameTextField.requestFocus();
		userNameTextField.setText("");
		userNameTextField.setBounds(125, 25, 110, 25);
		loginWindow.add(userNameTextField);	
		
		// Password Password Field
		passwordPasswordField.setText("");
		passwordPasswordField.setBounds(125, 60, 110, 25);
		passwordPasswordField.setEchoChar('*');
		loginWindow.add(passwordPasswordField);
		
		// Login Button
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(50, 100, 75, 25);
		loginButton.setActionCommand("Login");
		loginButton.addActionListener(this);
		loginWindow.add(loginButton);
		
		// Register Button
		JButton newUserButton = new JButton("Register");
		newUserButton.setBounds(150, 100, 85, 25);
		newUserButton.setActionCommand("NewUser");
		newUserButton.addActionListener(this);
		loginWindow.add(newUserButton);
		
		
		loginWindow.setVisible(true);
		registerWindow.setVisible(false);
	}
	
	public void RegistrationWindow() {
		// Registration Window
		registerWindow.setResizable(false);
		registerWindow.setSize(400, 355);
		registerWindow.setLocationRelativeTo(null);
		registerWindow.setLayout(null);
		
		// Username Label
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setBounds(50, 25, 75, 25);
		registerWindow.add(userNameLabel);
		
		// Username Text Field
		newUserNameTextField.setText("");
		newUserNameTextField.setBounds(175, 25, 160, 25);
		registerWindow.add(newUserNameTextField);
		
		// First Name Label
		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setBounds(50, 60, 75, 25);
		registerWindow.add(firstNameLabel);
	
		// First Name Text Field
		firstNameTextField.setText("");
		firstNameTextField.setBounds(175, 60, 160, 25);
		registerWindow.add(firstNameTextField);

		// Last Name Label
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setBounds(50, 95, 75, 25);
		registerWindow.add(lastNameLabel);
		
		// Last Name Text Field
		lastNameTextField.setText("");
		lastNameTextField.setBounds(175, 95, 160, 25);
		registerWindow.add(lastNameTextField);
		
		// Email Label
		JLabel emailLabel = new JLabel("Email Address");
		emailLabel.setBounds(50, 130, 100, 25);
		registerWindow.add(emailLabel);
	
		// Email Text Field
		emailTextField.setText("");
		emailTextField.setBounds(175, 130, 160, 25);
		registerWindow.add(emailTextField);
		
		// Password Label
		JLabel passwordLabel = new JLabel("Enter Password");
		passwordLabel.setBounds(50, 165, 125, 25);
		registerWindow.add(passwordLabel);
		
		// Password Password Field
		newPasswordPasswordField.setText("");
		newPasswordPasswordField.setBounds(175, 165, 160, 25);
		newPasswordPasswordField.setEchoChar('*');
		registerWindow.add(newPasswordPasswordField);
		
		// Confirm Password Label
		JLabel confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setBounds(50, 200, 125, 25);
		registerWindow.add(confirmPasswordLabel);

		// Confirm Password Password Field
		confirmPasswordPasswordField.setText("");
		confirmPasswordPasswordField.setBounds(175, 200, 160, 25);
		confirmPasswordPasswordField.setEchoChar('*');
		registerWindow.add(confirmPasswordPasswordField);
		
		// Age Label
		ageLabel.setBounds(50, 270, 125, 25);
		registerWindow.add(ageLabel);
		ageLabel.setVisible(false);
		
		// Age Text Field
		ageTextField.setText("");
		ageTextField.setBounds(175, 270, 160, 25);
		registerWindow.add(ageTextField);
		ageTextField.setVisible(false);
		
		// Male Radio Button
		maleRadioButton.setSelected(false);
		maleRadioButton.setBounds(50, 305, 100, 25);
		maleRadioButton.setActionCommand("Male");
		maleRadioButton.addActionListener(this);
		registerWindow.add(maleRadioButton);
		maleRadioButton.setVisible(false);
				
		// Female Radio Button
		femaleRadioButton.setSelected(false);
		femaleRadioButton.setBounds(150, 305, 100, 25);
		femaleRadioButton.setActionCommand("Female");
		femaleRadioButton.addActionListener(this);
		registerWindow.add(femaleRadioButton);
		femaleRadioButton.setVisible(false);
		
		// Patient Radio Button
		patientRadioButton.setSelected(false);
		patientRadioButton.setBounds(50, 235, 100, 25);
		patientRadioButton.setActionCommand("Patient");
		patientRadioButton.addActionListener(this);
		registerWindow.add(patientRadioButton);
		
		// Doctor Radio Button
		doctorRadioButton.setSelected(false);
		doctorRadioButton.setBounds(150, 235, 100, 25);
		doctorRadioButton.setActionCommand("Doctor");
		doctorRadioButton.addActionListener(this);
		registerWindow.add(doctorRadioButton);

		// Register Button
		registerButton.setBounds(50, 270, 285, 25);
		registerButton.setActionCommand("Register");
		registerButton.addActionListener(this);
		registerWindow.add(registerButton);

		registerWindow.setVisible(true);
	}

	// Handle action events
	public void actionPerformed(ActionEvent arg0) {
		
		// Login Window - Register Button
		if ("NewUser".equals(arg0.getActionCommand())) {
			userNameTextField.setText("");
			passwordPasswordField.setText("");
			RegistrationWindow();
		
		// Login Window - Login Button
		} else if("Login".equals(arg0.getActionCommand())) {
			try {
				LoginUser();
			} catch (BiffException | WriteException | IOException e) {
				e.printStackTrace();
			}

		// Register Window - Patient Radio Button
		} else if("Patient".equals(arg0.getActionCommand())) {
			patientRadioButton.setSelected(true);
			registerWindow.setSize(400, 425);
			doctorRadioButton.setSelected(false);
			registerButton.setBounds(50, 340, 285, 25);
			ageLabel.setVisible(true);
			ageTextField.setVisible(true);
			ageTextField.setText("");
			ageTextField.requestFocus();
			maleRadioButton.setVisible(true);
			maleRadioButton.setSelected(false);
			femaleRadioButton.setVisible(true);
			femaleRadioButton.setSelected(false);
			
		// Register Window - Practitioner Radio Button
		} else if ("Doctor".equals(arg0.getActionCommand())) {
			doctorRadioButton.setSelected(true);
			registerWindow.setSize(400, 355);
			patientRadioButton.setSelected(false);
			registerButton.setBounds(50, 270, 285, 25);
			ageLabel.setVisible(false);
			maleRadioButton.setVisible(false);
			femaleRadioButton.setVisible(false);
			ageTextField.setVisible(false);
			
		// Register Window - Male Radio Button
		} else if ("Male".equals(arg0.getActionCommand())) {
			femaleRadioButton.setSelected(false);
			maleRadioButton.setSelected(true);
			
		// Register Window - Female Radio Button
		} else if ("Female".equals(arg0.getActionCommand())) {
			maleRadioButton.setSelected(false);
			femaleRadioButton.setSelected(true);
		
		// Register Window - Register Button
		} else if ("Register".equals(arg0.getActionCommand())) {
			try {
				validateRegistrationInfo();
			} catch (BiffException|WriteException|IOException e) {
				e.printStackTrace();
			}
		}
	}
}