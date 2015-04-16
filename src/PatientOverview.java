import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PatientOverview extends JFrame
{
	private JLabel welcome;
	private String patientName;
	private JLabel recentMessages;
	private JTable recentMessageTable;
	private JLabel recentHistory;
	private JTable recentHistoryTable;
	private JLabel options;
	private JButton enterNewInfo;
	private JButton editExistingInfo;
	private JButton logout;
	
	public PatientOverview()
	{
		super("Efferent Patient Care System - Patient Overview");
		setSize(500,475);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
		
		patientName = "Mr. X";	//We need to retrieve the Last name from the excel file
		welcome = new JLabel ("Welcome " + patientName);
		welcome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		welcome.setBounds(10, 30, 150, 15);
		this.add(welcome);
		
		recentMessages = new JLabel("Your Recent Messages");
		recentMessages.setBounds(10, 55, 150, 15);
		this.add(recentMessages);
				
		recentHistory = new JLabel("Your Recent History");
		recentHistory.setBounds(10,200,150,15);
		this.add(recentHistory);
		
		options = new JLabel("What would you like to do?");
		options.setBounds(10, 340, 200, 15);
		this.add(options);
		
		enterNewInfo = new JButton("Enter New Information");
		enterNewInfo.setBounds(10, 380, 175, 25);
		this.add(enterNewInfo);
		
		editExistingInfo = new JButton("Edit Existing Information");
		editExistingInfo.setBounds(190, 380, 175, 25);
		this.add(editExistingInfo);
		
		logout = new JButton("Logout");
		logout.setBounds(370, 380, 100, 25);
		logout.addActionListener(new LogoutListener());
		this.add(logout);
		
		recentMessageTable = new JTable(5, 2);
		recentMessageTable.setAutoResizeMode(recentMessageTable.AUTO_RESIZE_OFF);
		recentMessageTable.getColumnModel().getColumn(0).setHeaderValue("Doctor Name");
		recentMessageTable.getColumnModel().getColumn(1).setHeaderValue("Message");
		recentMessageTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		recentMessageTable.getColumnModel().getColumn(1).setPreferredWidth(362);
		
		JScrollPane scrollPaneMessage = new JScrollPane(recentMessageTable);
		scrollPaneMessage.setBounds(10, 75, 465, 103);
		this.add(scrollPaneMessage);

		recentHistoryTable = new JTable(5,6);
		recentHistoryTable.setAutoResizeMode(recentHistoryTable.AUTO_RESIZE_OFF);
		recentHistoryTable.getColumnModel().getColumn(0).setHeaderValue("Pain");
		recentHistoryTable.getColumnModel().getColumn(1).setHeaderValue("Drowsiness");
		recentHistoryTable.getColumnModel().getColumn(2).setHeaderValue("Nausea");
		recentHistoryTable.getColumnModel().getColumn(3).setHeaderValue("Anxiety");
		recentHistoryTable.getColumnModel().getColumn(4).setHeaderValue("Depression");
		recentHistoryTable.getColumnModel().getColumn(5).setHeaderValue("Final Evaluation");	
		recentHistoryTable.getColumnModel().getColumn(0).setPreferredWidth(75);
		recentHistoryTable.getColumnModel().getColumn(1).setPreferredWidth(75);
		recentHistoryTable.getColumnModel().getColumn(2).setPreferredWidth(75);
		recentHistoryTable.getColumnModel().getColumn(3).setPreferredWidth(75);
		recentHistoryTable.getColumnModel().getColumn(4).setPreferredWidth(75);
		recentHistoryTable.getColumnModel().getColumn(5).setPreferredWidth(87);
		
		JScrollPane scrollPaneHistory = new JScrollPane(recentHistoryTable);
		scrollPaneHistory.setBounds(10, 220, 465, 103);
		this.add(scrollPaneHistory);
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
}
