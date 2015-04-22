import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DoctorOverview extends JFrame  
{

	private String doctorName;
	private Integer alertCount;
	private JTable PatientEntry;
	private JLabel Alerts, Welcome, Entries;
	private JButton AdditionalInfo, Logout;
	
	public DoctorOverview(String doctor)
	{
		
		super("Efferent Patient Care System - Doctor Overview");
		
		setSize(400,250);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		
		LoadPatientFiles();
		
		// Load EXCEL information
		String fileName = doctor + ".xls";
		try
		{
			Workbook workbook = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = workbook.getSheet(0);
			
			Cell lastNameCell = sheet.getCell(3,0);
			doctorName = lastNameCell.getContents();
		}
		catch(BiffException | IOException e)
		{
			e.printStackTrace();
		}
		
		// Entries label
		Entries = new JLabel("Recent Patient Entries:");
		Entries.setBounds(10, 55, 150, 15);
		Entries.setFont(new Font(Entries.getFont().getFontName(), Font.BOLD, 12));
		this.add(Entries);
		
		// Add Patient Table to Scroll Pane
		JScrollPane scrollPane = new JScrollPane(PatientEntry);
		scrollPane.setBounds(10, 75, 365, 100);
		scrollPane.getViewport().setBackground(Color.WHITE);
		this.add(scrollPane);
		
		// Alert Label
		alertCount = 0; 
		Alerts = new JLabel("Alerts: " + alertCount);
		Alerts.setBounds(325, 30, 100, 15);
		this.add(Alerts);
		
		// Welcome Label
		Welcome = new JLabel("Welcome Dr. " + doctorName);
		Welcome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Welcome.setBounds(10, 30, 150, 15);
		this.add(Welcome);
		
		// Additional Information Button
		AdditionalInfo = new JButton("See Additional Information");
		AdditionalInfo.setBounds(115, 185, 175, 25);
		AdditionalInfo.addActionListener(new SeeAdditionalInfo());
		this.add(AdditionalInfo);
		
		// Logout Button
		Logout = new JButton("Logout");
		Logout.setBounds(300, 185, 75, 25);
		Logout.addActionListener(new LogoutListener());
		this.add(Logout);
		
		setVisible(true);
		
	}

	void LoadPatientFiles()
	{
	    ArrayList<String> excelFiles = new ArrayList<String>();
	    ArrayList<String> patientFiles = new ArrayList<String>();
	    
		// Find all EXCEL files
		Path currentRelativePath = Paths.get("");
		String dirpath = currentRelativePath.toAbsolutePath().toString();
		try
		{
		    File folder = new File(dirpath);
		    File[] listOfFiles = folder.listFiles();
		    
		    	for (int i = 0; i < listOfFiles.length; i++)
		    	{
		    		if (listOfFiles[i].getName().endsWith(".xls"))
		    		{
		    			String temp = listOfFiles[i].getName();
		    			excelFiles.add(temp);
		    		}
		    	}
		}catch (Exception e){}
		
		// Find Patient EXCEL files	
		for (int i =0; i < excelFiles.size(); i++)
		{
			try
			{
				String fileName = excelFiles.get(i);
				Workbook workbook = Workbook.getWorkbook(new File(fileName));
				Sheet sheet = workbook.getSheet(0);
				
				if (!sheet.getCell(8,0).getContents().toString().equals("1"))
				{
					patientFiles.add(excelFiles.get(i));
				}
			}
			catch(BiffException | IOException e)
			{
				e.printStackTrace();
			}
			
		}
		
		// Load Patient information from EXCEL patientFiles into Object[][]
		Object[][] patientData = new Object[patientFiles.size()][7];
		int step = 0;
		
		for (int i = 0; i < patientFiles.size(); i++)
		{
			try
			{
				String fileName = patientFiles.get(i);
				Workbook workbook = Workbook.getWorkbook(new File(fileName));
				Sheet sheet = workbook.getSheet(0);
				
				patientData[step][0] = sheet.getCell(0,0).getContents(); //Username
				patientData[step][1] = sheet.getCell(2,0).getContents(); //First name
				patientData[step][2] = sheet.getCell(3,0).getContents(); //Last name
				patientData[step][3] = sheet.getCell(4,0).getContents(); //Email
				patientData[step][4] = sheet.getCell(5,0).getContents(); //Age
				patientData[step][5] = sheet.getCell(6,0).getContents(); //Gender
				patientData[step][6] = sheet.getCell(7,0).getContents(); //Patient ID
			}
			
			catch(BiffException | IOException e)
			{
				e.printStackTrace();
			}
			
			step++;
		}
		
		// Generate table of patient information
		String[] columnNames = {"Username", "First Name", "Last Name", "Email", "Age", "Gender", "Patient ID"};
		PatientEntry = new JTable(patientData, columnNames);
		PatientEntry.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		PatientEntry.getTableHeader().setBackground(Color.GRAY);
	}
	
	private class LogoutListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			LoginScreen login = new LoginScreen();
			login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			login.setSize(300, 200);
			login.setResizable(false);
			login.setVisible(true);	
			dispose();			
		}		
	}
	
	private class SeeAdditionalInfo implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			new PatientFile();		
		}		
	}
}
