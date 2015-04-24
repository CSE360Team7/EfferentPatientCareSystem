import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PatientFile extends JFrame {

	private JLabel lblFile, lblPain, lblDrowsiness, lblNausea, lblAnxiety, lblDepression, lblMessage, lblEntries, lblSeverity, lblAction;
	private JTextField txtPain, txtDrowsiness, txtNausea, txtAnxiety, txtDepression;
	private JTextArea txtMessage;
	private JButton btnConfirm, btnSendMessage, btnClose;
	private JComboBox cbSeverity;

	String firstName = "", lastName = "", pain = "", drowsiness = "", nausea = "", anxiety = "", depression = "", severity = "";
	String fileName;
	String doctorFile, doctorLastName;
	int latestEntry = 0;
	
	public PatientFile(String patientFile, String doctor, String doctorName)
	{
	
	super("Efferent Patient Care System - Patient File");
	
	// Set returning DoctorOverview doctor name
	doctorFile = doctor;
	doctorLastName = doctorName;
	
	fileName = patientFile + ".xls";
	try
	{
		Workbook workbook = Workbook.getWorkbook(new File(fileName));
		Sheet sheet = workbook.getSheet(0);
		
		firstName = sheet.getCell(2,0).getContents();
		lastName = sheet.getCell(3,0).getContents();

		// Get levels count cell number (0,1)
		latestEntry = Integer.parseInt(sheet.getCell(0,1).getContents());
		
		// Load values if Patient has entered symptom data
		if (latestEntry > 0)
		{
			severity = sheet.getCell(10, latestEntry-1).getContents();
			String parseLevels = sheet.getCell(9, latestEntry-1).getContents();
			String[] delim = parseLevels.split("/");
			pain = delim[0];
			drowsiness = delim[1];
			nausea = delim[2];
			anxiety = delim[3];
			depression = delim[4];
		}
	}
	catch(BiffException | IOException e)
	{
		e.printStackTrace();
	}
	
	// Window layout
	setSize(500,400);
	setResizable(false);
	setLayout(null);
	setLocationRelativeTo(null);
	setVisible(true);
	
	// Patient name label
	lblFile = new JLabel("File for " + firstName + " " + lastName);
	lblFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblFile.setBounds(10, 30, 150, 15);
	this.add(lblFile);
	
	// Entries label
	lblEntries = new JLabel("Entries:");
	lblEntries.setBounds(10, 60, 50, 15);
	this.add(lblEntries);
	
	// Pain label
	lblPain = new JLabel("PAIN");
	lblPain.setFont(new Font(lblPain.getFont().getFontName(), Font.BOLD, 12));
	lblPain.setBounds(10, 85, 50, 15);
	this.add(lblPain);
	
	// Pain textfield
	txtPain = new JTextField(pain);
	txtPain.setBounds(150, 84, 40, 17);
	txtPain.setEditable(false);
	this.add(txtPain);

	// Drowsiness label
	lblDrowsiness = new JLabel("DROWSINESS");
	lblDrowsiness.setFont(new Font(lblDrowsiness.getFont().getFontName(), Font.BOLD, 12));
	lblDrowsiness.setBounds(10, 110, 100, 15);
	this.add(lblDrowsiness);

	// Drowsiness textfield
	txtDrowsiness = new JTextField(drowsiness);
	txtDrowsiness.setBounds(150, 109, 40, 17);
	txtDrowsiness.setEditable(false);
	this.add(txtDrowsiness);
	
	// Nausea label
	lblNausea = new JLabel("NAUSEA");
	lblNausea.setFont(new Font(lblNausea.getFont().getFontName(), Font.BOLD, 12));
	lblNausea.setBounds(10, 135, 100, 15);
	this.add(lblNausea);
	
	// Nausea textfield
	txtNausea = new JTextField(nausea);
	txtNausea.setBounds(150, 134, 40, 17);
	txtNausea.setEditable(false);
	this.add(txtNausea);
		
	// Anxiety label
	lblAnxiety = new JLabel("ANXIETY");
	lblAnxiety.setFont(new Font(lblAnxiety.getFont().getFontName(), Font.BOLD, 12));
	lblAnxiety.setBounds(10, 160, 100, 15);
	this.add(lblAnxiety);

	// Anxiety textfield
	txtAnxiety = new JTextField(anxiety);
	txtAnxiety.setBounds(150, 159, 40, 17);
	txtAnxiety.setEditable(false);
	this.add(txtAnxiety);
	
	// Depression label
	lblDepression = new JLabel("DEPRESSION");
	lblDepression.setFont(new Font(lblDepression.getFont().getFontName(), Font.BOLD, 12));
	lblDepression.setBounds(10, 185, 150, 15);
	this.add(lblDepression);

	// Depression textfield
	txtDepression = new JTextField(depression);
	txtDepression.setBounds(150, 184, 40, 17);
	txtDepression.setEditable(false);
	this.add(txtDepression);
	
	// Severity label
	lblSeverity = new JLabel("Change Severity");
	lblSeverity.setBounds(10, 220, 150, 15);
	this.add(lblSeverity);
	
	// Severity combobox
	cbSeverity = new JComboBox();
	cbSeverity.setBounds(10, 245, 175, 25);
	cbSeverity.addItem("Trivial");
	cbSeverity.addItem("Minor");
	cbSeverity.addItem("Major");
	cbSeverity.addItem("Critical");
	cbSeverity.setSelectedItem(severity);
	if (severity.equals(""))
		cbSeverity.setEnabled(false);
	
	this.add(cbSeverity);
	
	// Send message to patient label
	lblMessage = new JLabel("Send Message To Patient");
	lblMessage.setBounds(250, 60, 150, 15);
	this.add(lblMessage);
	
	// Message textfield
	txtMessage = new JTextArea();
	txtMessage.setBounds(250, 85, 200, 180);
	txtMessage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	txtMessage.setLineWrap(true);
	txtMessage.setWrapStyleWord(true);
	this.add(txtMessage);
	txtMessage.requestFocus();
	
	// Actions label
	lblAction = new JLabel("What Action Would You Like To Take?");
	lblAction.setBounds(10, 305, 250, 15);
	this.add(lblAction);
	
	// Confirm changes button
	btnConfirm = new JButton("Confirm Changes");
	btnConfirm.addActionListener(new ChangeSeverityAction());
	btnConfirm.setBounds(20, 330, 150, 25);
	if (!cbSeverity.isEnabled())
		btnConfirm.setEnabled(false);
	this.add(btnConfirm);
	
	// Send message button
	btnSendMessage = new JButton("Send Message to Patient");
	btnSendMessage.setBounds(180, 330, 180, 25);
	btnSendMessage.addActionListener(new SendMessageAction());
	this.add(btnSendMessage);
	
	// Close button
	btnClose = new JButton("Close");
	btnClose.setBounds(370, 330, 105, 25);
	btnClose.addActionListener(new Close());
	this.add(btnClose);
	
	}
	
	boolean ChangeSeverity()
	{
		try{
		Workbook workbook = Workbook.getWorkbook(new File(fileName));
		WritableWorkbook copy = Workbook.createWorkbook(new File (fileName), workbook);
		WritableSheet data = copy.getSheet(0);
		Label label = new Label(10, latestEntry-1, (String) cbSeverity.getItemAt(cbSeverity.getSelectedIndex()));
		data.addCell(label);
		copy.write();
		copy.close();
		return true;
		
		}catch(BiffException | IOException | WriteException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	boolean SendMessage()
	{
		try{
		Workbook workbook = Workbook.getWorkbook(new File(fileName));
		WritableWorkbook copy = Workbook.createWorkbook(new File (fileName), workbook);
		WritableSheet data = copy.getSheet(0);
		int messageCount = Integer.parseInt(data.getCell(1,1).getContents());
		Label label = new Label(11, messageCount, txtMessage.getText());
		data.addCell(label);
		label = new Label(12, messageCount, "Dr. " + doctorLastName);
		data.addCell(label);
		messageCount++;
		label = new Label(1,1, String.valueOf(messageCount));
		data.addCell(label);
		copy.write();
		copy.close();
		return true;
		
		}catch(BiffException | IOException | WriteException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	private class Close implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			new DoctorOverview(doctorFile);
			dispose();
		}
	}
	
	private class SendMessageAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			
			if (txtMessage.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please enter a message to send!");
			}else
			{	
				if (SendMessage())
				{
					JOptionPane.showMessageDialog(null, "Message sent successfully!");
				}else
				{
					JOptionPane.showMessageDialog(null, "Failed to send message.");
				}
				
				txtMessage.setText("");
			}
			txtMessage.requestFocus();
		}
	}
	
	private class ChangeSeverityAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			
			if (cbSeverity.getItemAt(cbSeverity.getSelectedIndex()).equals(severity))
			{
				JOptionPane.showMessageDialog(null, "Severity level matches previous level.");
			}else
			{	
				if (ChangeSeverity())
				{
					JOptionPane.showMessageDialog(null, "Severity level changed successfully!");
				}else
				{
					JOptionPane.showMessageDialog(null, "Failed to change severity level.");
				}
				
			}
		}
	}
}
