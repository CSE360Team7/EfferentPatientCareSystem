import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PatientFile extends JFrame {

	private JLabel lblFile, lblPain, lblDrowsiness, lblNausea, lblAnxiety, lblDepression, lblMessage, lblEntries, lblSeverity, lblAction;
	private JTextField txtPain, txtDrowsiness, txtNausea, txtAnxiety, txtDepression;
	private JTextArea txtMessage;
	private JButton btnConfirm, btnSendMessage, btnClose;
	private JComboBox cbSeverity;
	
	public PatientFile()
	{
		
	super("Efferent Patient Care System - Patient File");
	
	// Window layout
	setSize(500,400);
	setResizable(false);
	setLayout(null);
	setLocationRelativeTo(null);
	setVisible(true);
	
	// Patient name label
	lblFile = new JLabel("File for <Patient Name>");
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
	txtPain = new JTextField();
	txtPain.setBounds(150, 84, 40, 17);
	this.add(txtPain);

	// Drowsiness label
	lblDrowsiness = new JLabel("DROWSINESS");
	lblDrowsiness.setFont(new Font(lblDrowsiness.getFont().getFontName(), Font.BOLD, 12));
	lblDrowsiness.setBounds(10, 110, 100, 15);
	this.add(lblDrowsiness);

	// Drowsiness textfield
	txtDrowsiness = new JTextField();
	txtDrowsiness.setBounds(150, 109, 40, 17);
	this.add(txtDrowsiness);
	
	// Nausea label
	lblNausea = new JLabel("NAUSEA");
	lblNausea.setFont(new Font(lblNausea.getFont().getFontName(), Font.BOLD, 12));
	lblNausea.setBounds(10, 135, 100, 15);
	this.add(lblNausea);
	
	// Nausea textfield
	txtNausea = new JTextField();
	txtNausea.setBounds(150, 134, 40, 17);
	this.add(txtNausea);
		
	// Anxiety label
	lblAnxiety = new JLabel("ANXIETY");
	lblAnxiety.setFont(new Font(lblAnxiety.getFont().getFontName(), Font.BOLD, 12));
	lblAnxiety.setBounds(10, 160, 100, 15);
	this.add(lblAnxiety);

	// Anxiety textfield
	txtAnxiety = new JTextField();
	txtAnxiety.setBounds(150, 159, 40, 17);
	this.add(txtAnxiety);
	
	// Depression label
	lblDepression = new JLabel("DEPRESSION");
	lblDepression.setFont(new Font(lblDepression.getFont().getFontName(), Font.BOLD, 12));
	lblDepression.setBounds(10, 185, 150, 15);
	this.add(lblDepression);

	// Depression textfield
	txtDepression = new JTextField();
	txtDepression.setBounds(150, 184, 40, 17);
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
	btnConfirm.setBounds(20, 330, 150, 25);
	this.add(btnConfirm);
	
	// Send message button
	btnSendMessage = new JButton("Send Message to Patient");
	btnSendMessage.setBounds(180, 330, 180, 25);
	this.add(btnSendMessage);
	
	// Close button
	btnClose = new JButton("Close");
	btnClose.setBounds(370, 330, 105, 25);
	btnClose.addActionListener(new Close());
	this.add(btnClose);
	
	}
	
	private class Close implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			dispose();
		}
	}
}
