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

	private String lastName = "", doctorFile = "";
	private Integer alertCount = 0;
	private JTable PatientEntry;

	public DoctorOverview(String doctor)
	{
		super("Efferent Patient Care System - Doctor Overview");
		JLabel Alerts, Welcome, Entries;
		JButton AdditionalInfo, Logout;
		setSize(400,300);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		
		doctorFile = doctor;
		
		// Load patient EXCEL data into table
		LoadPatientFiles();
		
		// Load EXCEL information
		String fileName = doctor + ".xls";
		try
		{
			Workbook workbook = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = workbook.getSheet(0);
			
			Cell lastNameCell = sheet.getCell(3,0);
			lastName = lastNameCell.getContents();
		}
		catch(BiffException | IOException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		
		// Entries label
		Entries = new JLabel("Recent Patient Entries:");
		Entries.setBounds(10, 55, 150, 15);
		Entries.setFont(new Font(Entries.getFont().getFontName(), Font.BOLD, 12));
		this.add(Entries);
		
		// Add Patient Table to Scroll Pane
		JScrollPane scrollPane = new JScrollPane(PatientEntry);
		scrollPane.setBounds(10, 75, 365, 150);
		scrollPane.getViewport().setBackground(Color.WHITE);
		this.add(scrollPane);
		
		// Alert Label
		Alerts = new JLabel("Alerts: " + alertCount);
		Alerts.setBounds(325, 30, 100, 15);
		if (alertCount > 0)
			Alerts.setForeground(Color.RED);
		this.add(Alerts);
		
		// Welcome Label
		Welcome = new JLabel("Welcome Dr. " + lastName);
		Welcome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Welcome.setBounds(10, 30, 150, 15);
		this.add(Welcome);
		
		// Additional Information Button
		AdditionalInfo = new JButton("See Additional Information");
		AdditionalInfo.setBounds(115, 235, 175, 25);
		AdditionalInfo.addActionListener(new SeeAdditionalInfo());
		this.add(AdditionalInfo);
		
		// Disable button if no Patients exist
		if (PatientEntry.getRowCount() <= 0)
			AdditionalInfo.setEnabled(false);
		
		// Logout Button
		Logout = new JButton("Logout");
		Logout.setBounds(300, 235, 75, 25);
		Logout.addActionListener(new LogoutListener());
		this.add(Logout);
		
		setVisible(true);
		
	}

	void LoadPatientFiles()
	{
	    ArrayList<String> excelFiles = new ArrayList<>();
	    ArrayList<String> patientFiles = new ArrayList<>();
	    
		// Find all EXCEL files
		Path currentRelativePath = Paths.get("");
		String dirpath = currentRelativePath.toAbsolutePath().toString();
		try
		{
		    File folder = new File(dirpath);
		    File[] listOfFiles = folder.listFiles();
			if (listOfFiles != null) {
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].getName().endsWith(".xls")) {
						String temp = listOfFiles[i].getName();
						excelFiles.add(temp);
					}
				}
			}
			else
			{
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		// Find Patient EXCEL files	
		for (int i  =0; i < excelFiles.size(); i++)
		{
			try
			{
				String fileName = excelFiles.get(i);
				Workbook workbook = Workbook.getWorkbook(new File(fileName));
				Sheet sheet = workbook.getSheet(0);
				
				if (!sheet.getCell(8,0).getContents().equals("1"))
				{
					patientFiles.add(excelFiles.get(i));
				}
			}
			catch(BiffException | IOException e)
			{
				e.printStackTrace();
				System.exit(-1);
			}
			
		}
		
		// Load Patient information from EXCEL patientFiles into Object[][]
		Object[][] patientData = new Object[patientFiles.size()][8];
		int j = 0;
		
		for (int i = 0; i < patientFiles.size(); i++)
		{
			try
			{
				String fileName = patientFiles.get(i);
				Workbook workbook = Workbook.getWorkbook(new File(fileName));
				Sheet sheet = workbook.getSheet(0);
				
				patientData[j][0] = "-";
				patientData[j][1] = sheet.getCell(0,0).getContents(); //Username
				patientData[j][2] = sheet.getCell(2,0).getContents(); //First name
				patientData[j][3] = sheet.getCell(3,0).getContents(); //Last name
				patientData[j][4] = sheet.getCell(5,0).getContents(); //Age
				patientData[j][5] = sheet.getCell(6,0).getContents(); //Gender
				patientData[j][6] = sheet.getCell(4,0).getContents(); //Email
				patientData[j][7] = sheet.getCell(7,0).getContents(); //Patient ID
				
				// Update Alert count
				Integer latestEntry = Integer.parseInt(sheet.getCell(0,1).getContents());
				if (latestEntry > 0)
				{
					String severity = sheet.getCell(10, latestEntry-1).getContents();
					patientData[j][0] = severity;
					if (severity.equals("Critical"))
					{
						alertCount++;
					}
				}
			}
			
			catch(BiffException | IOException e)
			{
				e.printStackTrace();
				System.exit(-1);
			}
			
			j++;
		}
		
		// Generate table of patient information
		String[] columnNames = {"Status", "Username", "First Name", "Last Name", "Age", "Gender", "Email", "Patient ID"};
		PatientEntry = new JTable(patientData, columnNames);
		PatientEntry.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		PatientEntry.getTableHeader().setBackground(Color.GRAY);
		PatientEntry.getColumnModel().getColumn(0).setPreferredWidth(45);
		PatientEntry.getColumnModel().getColumn(1).setPreferredWidth(100);
		PatientEntry.getColumnModel().getColumn(4).setPreferredWidth(50);
		PatientEntry.getColumnModel().getColumn(6).setPreferredWidth(125);
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
			if (PatientEntry.getSelectedRow() > -1)
			{
				String patientFile = PatientEntry.getValueAt(PatientEntry.getSelectedRow(), 1).toString();
				new PatientFile(patientFile, doctorFile, lastName);
				dispose();
			}else{
				JOptionPane.showMessageDialog(null, "Please select a patient record!");
			}
		}		
	}
}
