import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import jxl.*;
import jxl.write.*;
import jxl.read.biff.*;

public class RegisterScreen extends JFrame implements ActionListener
{
	JTextField newUserNameTextField;
	JTextField firstNameTextField;
	JTextField lastNameTextField;
	JTextField emailTextField;
	JPasswordField newPasswordPasswordField;
	JPasswordField confirmPasswordPasswordField;
	JRadioButton patientRadioButton;
	JRadioButton doctorRadioButton;
	JLabel ageLabel;
	JTextField ageTextField;	
	JRadioButton maleRadioButton;
	JRadioButton femaleRadioButton;
	JButton registerButton;
	
	public RegisterScreen()
	{
		super("Efferent Patient Care System - Register");
		
		setResizable(false);
		setSize(400, 355);
		setLocationRelativeTo(null);
		setLayout(null);
		
		newUserNameTextField = new JTextField("");
		firstNameTextField = new JTextField("");
		lastNameTextField = new JTextField("");
		emailTextField = new JTextField("");
		newPasswordPasswordField = new JPasswordField("");
		confirmPasswordPasswordField = new JPasswordField("");
		
		patientRadioButton = new JRadioButton("Patient");
		patientRadioButton.setToolTipText(patientRadioButton.getText());
		doctorRadioButton = new JRadioButton("Practitioner");
		doctorRadioButton.setToolTipText(doctorRadioButton.getText());

		ageLabel = new JLabel("Age");
		ageLabel.setToolTipText(ageLabel.getText());
		ageTextField = new JTextField("");
		
		maleRadioButton = new JRadioButton("Male");
		maleRadioButton.setToolTipText(maleRadioButton.getText());
		femaleRadioButton = new JRadioButton("Female");
		femaleRadioButton.setToolTipText(femaleRadioButton.getText());
		
		registerButton = new JButton("Register");
		registerButton.setToolTipText(registerButton.getText());
		
		
		// Username Label
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setToolTipText(userNameLabel.getText());
		userNameLabel.setBounds(50, 25, 75, 25);
		add(userNameLabel);

		// Username Text Field
		newUserNameTextField.setText("");
		newUserNameTextField.setBounds(175, 25, 160, 25);
		add(newUserNameTextField);

		// First Name Label
		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setToolTipText(firstNameLabel.getText());
		firstNameLabel.setBounds(50, 60, 75, 25);
		add(firstNameLabel);

		// First Name Text Field
		firstNameTextField.setText("");
		firstNameTextField.setBounds(175, 60, 160, 25);
		add(firstNameTextField);

		// Last Name Label
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setToolTipText(lastNameLabel.getText());
		lastNameLabel.setBounds(50, 95, 75, 25);
		add(lastNameLabel);

		// Last Name Text Field
		lastNameTextField.setText("");
		lastNameTextField.setBounds(175, 95, 160, 25);
		add(lastNameTextField);

		// Email Label
		JLabel emailLabel = new JLabel("Email Address");
		emailLabel.setToolTipText(emailLabel.getText());
		emailLabel.setBounds(50, 130, 100, 25);
		add(emailLabel);

		// Email Text Field
		emailTextField.setText("");
		emailTextField.setBounds(175, 130, 160, 25);
		add(emailTextField);

		// Password Label
		JLabel passwordLabel = new JLabel("Enter Password");
		passwordLabel.setToolTipText(passwordLabel.getText());
		passwordLabel.setBounds(50, 165, 125, 25);
		add(passwordLabel);

		// Password Password Field
		newPasswordPasswordField.setText("");
		newPasswordPasswordField.setBounds(175, 165, 160, 25);
		newPasswordPasswordField.setEchoChar('*');
		add(newPasswordPasswordField);

		// Confirm Password Label
		JLabel confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setToolTipText(confirmPasswordLabel.getText());
		confirmPasswordLabel.setBounds(50, 200, 125, 25);
		add(confirmPasswordLabel);

		// Confirm Password Password Field
		confirmPasswordPasswordField.setText("");
		confirmPasswordPasswordField.setBounds(175, 200, 160, 25);
		confirmPasswordPasswordField.setEchoChar('*');
		add(confirmPasswordPasswordField);

		// Age Label
		ageLabel.setBounds(50, 270, 125, 25);
		add(ageLabel);
		ageLabel.setVisible(false);

		// Age Text Field
		ageTextField.setText("");
		ageTextField.setBounds(175, 270, 160, 25);
		add(ageTextField);
		ageTextField.setVisible(false);

		// Male Radio Button
		maleRadioButton.setSelected(false);
		maleRadioButton.setBounds(50, 305, 100, 25);
		maleRadioButton.setActionCommand("Male");
		maleRadioButton.addActionListener(this);
		add(maleRadioButton);
		maleRadioButton.setVisible(false);

		// Female Radio Button
		femaleRadioButton.setSelected(false);
		femaleRadioButton.setBounds(150, 305, 100, 25);
		femaleRadioButton.setActionCommand("Female");
		femaleRadioButton.addActionListener(this);
		add(femaleRadioButton);
		femaleRadioButton.setVisible(false);

		// Patient Radio Button
		patientRadioButton.setSelected(false);
		patientRadioButton.setBounds(50, 235, 100, 25);
		patientRadioButton.setActionCommand("Patient");
		patientRadioButton.addActionListener(this);
		add(patientRadioButton);

		// Doctor Radio Button
		doctorRadioButton.setSelected(false);
		doctorRadioButton.setBounds(150, 235, 100, 25);
		doctorRadioButton.setActionCommand("Doctor");
		doctorRadioButton.addActionListener(this);
		add(doctorRadioButton);

		// Register Button
		registerButton.setBounds(50, 270, 285, 25);
		registerButton.setActionCommand("Register");
		registerButton.addActionListener(this);
		add(registerButton);

		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if("Patient".equals(arg0.getActionCommand()))
		{
			patientRadioButton.setSelected(true);
			setSize(400, 425);
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
		}
		
		else if ("Doctor".equals(arg0.getActionCommand()))
		{
			doctorRadioButton.setSelected(true);
			setSize(400, 355);
			patientRadioButton.setSelected(false);
			registerButton.setBounds(50, 270, 285, 25);
			ageLabel.setVisible(false);
			maleRadioButton.setVisible(false);
			femaleRadioButton.setVisible(false);
			ageTextField.setVisible(false);
		}
		else if ("Male".equals(arg0.getActionCommand()))
		{
			femaleRadioButton.setSelected(false);
			maleRadioButton.setSelected(true);
		}
		else if ("Female".equals(arg0.getActionCommand()))
		{
			maleRadioButton.setSelected(false);
			femaleRadioButton.setSelected(true);
		}
		else if ("Register".equals(arg0.getActionCommand()))
		{
			try
			{
				validateRegistrationInfo();
			}
			catch (BiffException|WriteException|IOException e)
			{
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
	public void RegisterNewUser()
	{
		User newUser;

		// Set Patient attributes
		if (patientRadioButton.isSelected()) {

			String gender;
			int age = Integer.parseInt(ageTextField.getText());

			// Set gender
			if (maleRadioButton.isSelected())
			{
				gender = "Male";
			}
			else
			{
				gender = "Female";
			}
			String username = newUserNameTextField.getText();
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
		newUser.setUserName(newUserNameTextField.getText());
		newUser.setFirstName(firstNameTextField.getText());
		newUser.setLastName(lastNameTextField.getText());
		newUser.setEmail(emailTextField.getText());
		newUser.setPassword(newPasswordPasswordField.getPassword());

		// Try to create user Excel file
		try
		{
			createNewUserFile(newUser);
		}
		catch (BiffException|WriteException|IOException e) {
			e.printStackTrace();
			System.exit(-1);
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

		if(!(emailTextField.getText().contains("@")))
		{
			JOptionPane.showMessageDialog(alert, "Please enter a valid email address");
			return;
		}

		//Check password equivalency
		if (!String.valueOf(newPasswordPasswordField.getPassword()).equals(String.valueOf(confirmPasswordPasswordField.getPassword()))) {
			JOptionPane.showMessageDialog(alert, "Passwords do not match.");
			return;
		}

		// No discrepancies in user information, attempt to register
		RegisterNewUser();
	}

	public void createNewUserFile(User newUser) throws BiffException, IOException, WriteException
	{
		// Define filename
		String fileName = newUser.getUserName() + ".xls";

		// Create new file
		WritableWorkbook workbook = Workbook.createWorkbook(new File(fileName));
		WritableSheet sheet = workbook.createSheet(newUser.getUserName(), 0);
		Integer counter = 0;
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
		label = new Label (8 ,0, Integer.toString(newUser.getUserType()));
		sheet.addCell(label);

		// Add Patient specific data if user is Patient
		if (newUser.getUserType() == 0)
		{
			label = new Label(5, 0, String.valueOf(((Patient) newUser).getAge()));
			sheet.addCell(label);
			label = new Label(6, 0, String.valueOf(((Patient) newUser).getGender()));
			sheet.addCell(label);
			label = new Label(7, 0, String.valueOf(((Patient) newUser).getID()));
			sheet.addCell(label);
			label = new Label (0, 1, counter.toString()); // Symptom counter
			sheet.addCell(label);
			label = new Label (1, 1, counter.toString()); // Messages counter
			sheet.addCell(label);
		}

		workbook.write();
		workbook.close();

		// Show registration confirmation
		JFrame alert = new JFrame();
		JOptionPane.showMessageDialog(alert, "Registration successful!");		
		dispose();
		// Show login window
		new LoginScreen();
	}

}
