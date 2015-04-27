import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import jxl.*;
import jxl.write.*;
import jxl.read.biff.*;

public class LoginScreen extends JFrame implements ActionListener
{
	JTextField userNameTextField;
	JPasswordField passwordPasswordField ;
	
	public LoginScreen()
	{
		super("Efferent Patient Care System - Login");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);

		this.userNameTextField = new JTextField("");
		this.passwordPasswordField = new JPasswordField("");

		this.userNameTextField.setText("");
		this.passwordPasswordField.setText("");
		this.passwordPasswordField.setEchoChar('*');

		JLabel userNameLabel = new JLabel("Username");
		JLabel passwordLabel = new JLabel("Password");
		JLabel newUserLabel = new JLabel("New User? Click Register!");


		JButton loginButton = new JButton("Login");
			loginButton.setActionCommand("Login");
			loginButton.addActionListener(this);
		JButton newUserButton = new JButton("Register");
			newUserButton.setActionCommand("NewUser");
			newUserButton.addActionListener(this);

		userNameLabel.setBounds(50, 25, 75, 25);
		passwordLabel.setBounds(50, 60, 75, 25);
		newUserLabel.setBounds(85, 130, 150, 25);
		this.userNameTextField.setBounds(125, 25, 110, 25);
		this.passwordPasswordField.setBounds(125, 60, 110, 25);
		loginButton.setBounds(50, 100, 75, 25);
		newUserButton.setBounds(150, 100, 85, 25);

		//with some default fonts, the ends of words will get cut off
		//add tooltips so users can at least see what the labels are supposed to say
		userNameLabel.setToolTipText(userNameLabel.getText());
		passwordLabel.setToolTipText(passwordLabel.getText());
		newUserLabel.setToolTipText(newUserLabel.getText());
		loginButton.setToolTipText(loginButton.getText());
		newUserButton.setToolTipText(newUserButton.getText());

		add(userNameLabel);
		add(passwordLabel);
		add(newUserLabel);
		add(userNameTextField);
		add(passwordPasswordField);
		add(loginButton);
		add(newUserButton);

		userNameTextField.requestFocus();

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if("Login".equals(arg0.getActionCommand())) 
		{
			try 
			{
				LoginUser();
			} 
			catch (BiffException | WriteException | IOException e) 
			{
				e.printStackTrace();
				System.exit(-1);
			}	
		}
		
		else if ("NewUser".equals(arg0.getActionCommand())) 
		{
			userNameTextField.setText("");
			passwordPasswordField.setText("");
			new RegisterScreen();
		}
	}
	
	public void LoginUser() throws BiffException, IOException, WriteException {
		
		JPanel alert = new JPanel();		
		// Define file name
		String fileName = userNameTextField.getText() + ".xls";
		
		// Try to read excel file
		try
		{
			Workbook workbook = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = workbook.getSheet(0);
			
	        // Get username and password from file
	        Cell userNameCell = sheet.getCell(0,0);
	        Cell passwordCell = sheet.getCell(1,0);
	        Cell markerCell = sheet.getCell(8,0);
	        
	        // Convert to string
	        String userName = userNameCell.getContents();
	        String password = passwordCell.getContents();
	        String marker = markerCell.getContents();
	        
	        // Login
	        if (userName.equals(userNameTextField.getText()) && (password.equals(String.valueOf(passwordPasswordField.getPassword())))) 
	        {
	    		if(marker.equals("0"))
	    		{	  
	        		//JOptionPane.showMessageDialog(alert, "Patient Overview");
	        		new PatientOverview(userName);
	        		dispose();
	        	}
	        	else
				{
					//JOptionPane.showMessageDialog(alert, "Doctor Overview");
					new DoctorOverview(userName);
					dispose();
				}
	        } 
	        else 
	        {
	        	JOptionPane.showMessageDialog(alert, "Password incorrect.");	
	        }
		}
		catch (FileNotFoundException notFound) 
		{
			JOptionPane.showMessageDialog(alert, "Username invalid.");
		}
	}
}
