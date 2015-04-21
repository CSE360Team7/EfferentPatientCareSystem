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
	private JLabel Alerts, Welcome;
	private JButton AdditionalInfo, Logout;
		
	public DoctorOverview(String doctor)
	{
		super("Efferent Patient Care System - Doctor Overview");
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		setSize(400,250);
		
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
		
		// Patient Table in Scroll Pane
		PatientEntry = new JTable(5, 2);
		PatientEntry.setAutoResizeMode(PatientEntry.AUTO_RESIZE_OFF);
		PatientEntry.getColumnModel().getColumn(0).setHeaderValue("Name");
		PatientEntry.getColumnModel().getColumn(1).setHeaderValue("Initial Assessment");
		PatientEntry.getColumnModel().getColumn(0).setPreferredWidth(100);
		PatientEntry.getColumnModel().getColumn(1).setPreferredWidth(245);
		
		// Add Patient Table to Scroll Pane
		JScrollPane scrollPane = new JScrollPane(PatientEntry);
		scrollPane.setBounds(10, 65, 365, 100);
		this.add(scrollPane);
		
		// Alert Label
		alertCount = 0; 
		Alerts = new JLabel("Alerts: " + alertCount);
		Alerts.setBounds(300, 30, 100, 15);
		this.add(Alerts);
		
		// Welcome Label
		Welcome = new JLabel("Welcome Dr. " + doctorName);
		Welcome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Welcome.setBounds(10, 30, 150, 15);
		this.add(Welcome);
		
		// Additional Information Button
		AdditionalInfo = new JButton("See Additional Information");
		AdditionalInfo.setBounds(115, 175, 175, 25);
		AdditionalInfo.addActionListener(new SeeAdditionalInfo());
		this.add(AdditionalInfo);
		
		// Logout Button
		Logout = new JButton("Logout");
		Logout.setBounds(300, 175, 75, 25);
		Logout.addActionListener(new LogoutListener());
		this.add(Logout);
		
		setVisible(true);
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
