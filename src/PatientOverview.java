import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import jxl.*;
import jxl.write.*;
import jxl.read.biff.*;

public class PatientOverview extends JFrame
{
	private JLabel welcome;
	private String patientFirstName, patientLastName, patientFile;
	private JLabel recentMessages;
	private JTable recentMessageTable;
	private JLabel recentHistory;
	private JTable recentHistoryTable;
	private JLabel options;
	private JButton enterNewInfo;
	private JButton logout;
	
	public PatientOverview(String patient)
	{
		super("Efferent Patient Care System - Patient Overview");
		setSize(500,440);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
			
		patientFile = patient;
		String fileName = patient + ".xls";
		
		// Load Patient Messages into table
		LoadMessages();
		loadPatientData();
		
		
		try
		{
			Workbook workbook = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = workbook.getSheet(0);
			
			Cell firstNameCell = sheet.getCell(2,0);
			Cell lastNameCell = sheet.getCell(3,0);
			String firstName =firstNameCell.getContents();
			String lastName = lastNameCell.getContents();
			patientFirstName = firstName;
			patientLastName = lastName;
		}
		catch(BiffException | IOException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		
		welcome = new JLabel ("Welcome " + patientFirstName + " " + patientLastName);
		welcome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		welcome.setBounds(10, 30, 490, 15);
		this.add(welcome);
		
		recentMessages = new JLabel("Your Recent Messages");
		recentMessages.setFont(new Font(recentMessages.getFont().getFontName(), Font.BOLD, 12));
		recentMessages.setBounds(10, 55, 490, 15);
		this.add(recentMessages);
				
		recentHistory = new JLabel("Your Recent History");
		recentHistory.setFont(new Font(recentHistory.getFont().getFontName(), Font.BOLD, 12));
		recentHistory.setBounds(10, 200, 150, 15);
		this.add(recentHistory);
		
		options = new JLabel("What would you like to do?");
		options.setBounds(10, 350, 200, 15);
		this.add(options);
		
		enterNewInfo = new JButton("Enter New Information");
		enterNewInfo.setBounds(180, 370, 175, 25);
		enterNewInfo.addActionListener(new AddInfoListener());
		this.add(enterNewInfo);
		
		
		logout = new JButton("Logout");
		logout.setBounds(370, 370, 100, 25);
		logout.addActionListener(new LogoutListener());
		this.add(logout);
		
		// Messages table
		recentMessageTable.getTableHeader().setBackground(Color.GRAY);
		recentMessageTable.getColumnModel().getColumn(0).setPreferredWidth(120);
		recentMessageTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		recentMessageTable.getColumnModel().getColumn(2).setPreferredWidth(750);	
		
		// Create ScrollPane for Messages table
		JScrollPane scrollPaneMessage = new JScrollPane(recentMessageTable);
		scrollPaneMessage.setBounds(10, 75, 465, 103);
		this.add(scrollPaneMessage);
	
		recentHistoryTable.getTableHeader().setBackground(Color.GRAY);
		recentHistoryTable.getColumnModel().getColumn(0).setPreferredWidth(120);
		recentHistoryTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		recentHistoryTable.getColumnModel().getColumn(2).setPreferredWidth(70);
		recentHistoryTable.getColumnModel().getColumn(3).setPreferredWidth(70);
		recentHistoryTable.getColumnModel().getColumn(4).setPreferredWidth(70);
		recentHistoryTable.getColumnModel().getColumn(5).setPreferredWidth(70);
		recentHistoryTable.getColumnModel().getColumn(6).setPreferredWidth(96);
		
		JScrollPane scrollPaneHistory = new JScrollPane(recentHistoryTable);
		scrollPaneHistory.setBounds(10, 220, 465, 103);
		this.add(scrollPaneHistory);
		
		setVisible(true);
	}
	
	void LoadMessages()
	{
		try
		{
			String fileName = patientFile + ".xls";
			Workbook workbook = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = workbook.getSheet(0);
			
			// Get message count from EXCEL file
			int messageCount = Integer.parseInt(sheet.getCell(1,1).getContents());
			
			// If no messages exist create empty table and return
			if (messageCount <= 0)
			{
				recentMessageTable = new JTable(0,3);
				recentMessageTable.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Time");
				recentMessageTable.getTableHeader().getColumnModel().getColumn(1).setHeaderValue("Doctor");
				recentMessageTable.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("Message");
				return;
			}
	
			// Load Patient information from EXCEL patientFiles into Object[][]
			Object[][] patientData = new Object[messageCount][3];
			
			for (int i = messageCount - 1; i > -1 ; i--)
			{
				String message = sheet.getCell(11, i).getContents();
				String fromDoctor = sheet.getCell(12, i).getContents();
				String messageTime = sheet.getCell(14, i).getContents();
				patientData[messageCount - i - 1][0] = messageTime;
				patientData[messageCount - i - 1][1] = fromDoctor;
				patientData[messageCount - i - 1][2] = message;
			}
			
			// Create table of Messages
			String[] columns = { "Time", "Doctor", "Message" };
			recentMessageTable = new JTable(patientData, columns);
			recentMessageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}
		catch(BiffException | IOException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	void loadPatientData()
	{
		try
		{
			String fileName = patientFile + ".xls";
			Workbook workbook = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = workbook.getSheet(0);
			
			int dataCount = Integer.parseInt(sheet.getCell(0,1).getContents());
			
			if(dataCount<=0)
			{
				recentHistoryTable = new JTable(0,7);
				recentHistoryTable.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Time");
				recentHistoryTable.getTableHeader().getColumnModel().getColumn(1).setHeaderValue("Pain");
				recentHistoryTable.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("Drowsiness");
				recentHistoryTable.getTableHeader().getColumnModel().getColumn(3).setHeaderValue("Nausea");
				recentHistoryTable.getTableHeader().getColumnModel().getColumn(4).setHeaderValue("Anxiety");
				recentHistoryTable.getTableHeader().getColumnModel().getColumn(5).setHeaderValue("Depression");
				recentHistoryTable.getTableHeader().getColumnModel().getColumn(6).setHeaderValue("Final Evaluation");
				return;
			}
			Object[][] patientSymptoms = new Object[dataCount][7];
			for (int i = dataCount-1; i > -1 ; i--)
			{
				String levels = sheet.getCell(9, i).getContents();
				String[] parser = levels.split("/");
				String finalEval = sheet.getCell(10, i).getContents();
				String time = sheet.getCell(13, i).getContents();
				patientSymptoms[dataCount -1 -i][0] = time;
				patientSymptoms[dataCount -1 -i][1] = parser[0];
				patientSymptoms[dataCount -1 -i][2] = parser[1];
				patientSymptoms[dataCount -1 -i][3] = parser[2];
				patientSymptoms[dataCount -1 -i][4] = parser[3];
				patientSymptoms[dataCount -1 -i][5] = parser[4];
				patientSymptoms[dataCount -1 -i][6] = finalEval;
				
			}
			String[] columns = {"Time", "Pain", "Drowsiness", "Nausea", "Anxiety", "Depression", "Final Evaluation"};
			recentHistoryTable = new JTable(patientSymptoms, columns);
			recentHistoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}
		catch(BiffException | IOException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
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
	
	private class AddInfoListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			new AddEditPatientInfo(patientFile);
			dispose();
		}
	}
}
