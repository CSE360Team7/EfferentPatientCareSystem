import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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
		setVisible(true);
		
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
		
		// Patient Table in Scroll Pane
		PatientEntry = new JTable(2, 2);
		PatientEntry.setAutoResizeMode(PatientEntry.AUTO_RESIZE_OFF);
		PatientEntry.getColumnModel().getColumn(0).setHeaderValue("Name");
		PatientEntry.getColumnModel().getColumn(1).setHeaderValue("Initial Assessment");
		PatientEntry.getColumnModel().getColumn(0).setPreferredWidth(125);
		PatientEntry.getColumnModel().getColumn(1).setPreferredWidth(238);
		PatientEntry.getTableHeader().setBackground(Color.GRAY);
		
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
