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
		super ("Efferent Patient Care System - Login");	

		setLocationRelativeTo(null);
		setLayout(null);
		
		userNameTextField = new JTextField("");
		passwordPasswordField = new JPasswordField("");
		
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setBounds(50, 25, 75, 25);
		add(userNameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(50, 60, 75, 25);
		add(passwordLabel);
		
		JLabel newUserLabel = new JLabel("New User? Click Register!");
		newUserLabel.setBounds(65, 130, 150, 25);
		add(newUserLabel);
		
		userNameTextField.requestFocus();
		userNameTextField.setText("");
		userNameTextField.setBounds(125, 25, 110, 25);
		add(userNameTextField);	
		
		passwordPasswordField.setText("");
		passwordPasswordField.setBounds(125, 60, 110, 25);
		passwordPasswordField.setEchoChar('*');
		add(passwordPasswordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(50, 100, 75, 25);
		loginButton.setActionCommand("Login");
		loginButton.addActionListener(this);
		add(loginButton);
		
		JButton newUserButton = new JButton("Register");
		newUserButton.setBounds(150, 100, 85, 25);
		newUserButton.setActionCommand("NewUser");
		newUserButton.addActionListener(this);
		add(newUserButton);
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
			}	
		}
		
		else if ("NewUser".equals(arg0.getActionCommand())) 
		{
			userNameTextField.setText("");
			passwordPasswordField.setText("");
			new RegisterScreen();
		}
	}
	
	public void LoginUser() throws BiffException, IOException, WriteException{
		
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
	        
	        // Convert to string
	        String userName = userNameCell.getContents();
	        String password = passwordCell.getContents();
	        
	        // Login
	        if (userName.equals(userNameTextField.getText()) && (password.equals(String.valueOf(passwordPasswordField.getPassword())))) 
	        {
	    		JOptionPane.showMessageDialog(alert, "Login successful!");
	    		
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
