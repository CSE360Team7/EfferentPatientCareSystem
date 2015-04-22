import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import jxl.*;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import jxl.read.biff.*;

public class AddEditPatientInfo extends JFrame
{
	private Integer counter;
	private String patientFirstName, patientLastName, patientFile;
	private JLabel nameLabel;
	private JLabel info1;
	private JLabel info2;
	private JLabel painLabel, drowsinessLabel, nauseaLabel, anxietyLabel, depressionLabel;
	private JSlider painSlider, drowsinessSlider, nauseaSlider, anxietySlider, depressionSlider;
	private JTextField painText, drowsinessText, nauseaText, anxietyText, depressionText;
	private JButton submit, reset, cancel;
	
	public AddEditPatientInfo(String patient)
	{
		super("Efferent Patient Care System - Add/Edit Patient Information");
		
		setSize(452,500);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		patientFile = patient;
		String fileName = patient + ".xls";
		try
		{
			Workbook workbook = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = workbook.getSheet(0);
			
			Cell firstNameCell = sheet.getCell(2,0);
			Cell lastNameCell = sheet.getCell(3,0);
			Cell counterCell = sheet.getCell(0,1);
			String firstName = firstNameCell.getContents();
			String lastName = lastNameCell.getContents();
			counter = Integer.parseInt(counterCell.getContents());
			patientFirstName = firstName;
			patientLastName = lastName;
		}
		catch(BiffException | IOException e)
		{
			e.printStackTrace();
		}

		nameLabel = new JLabel(patientFirstName + " " + patientLastName);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel.setBounds(10, 30, 150, 15);
		this.add(nameLabel);
		
		info1 = new JLabel("On a scale from 1-10 please rate the following in terms of severness.");
		info1.setBounds(10, 60, 450, 15);
		this.add(info1);
		
		info2 = new JLabel("You may use the slider or type a number from 1-10 in the text field.");
		info2.setBounds(10, 80, 450, 15);
		this.add(info2);
		
		painLabel = new JLabel("PAIN");
		painLabel.setBounds(10, 120, 50, 15);
		this.add(painLabel);

		painSlider = new JSlider();
		painSlider.setMinimum(0);
		painSlider.setMaximum(10);
		painSlider.setMajorTickSpacing(1);
		painSlider.setValue(0);
		painSlider.setPaintTicks(true);
		painSlider.setPaintLabels(true);
		painSlider.addChangeListener(new SliderChanged());
		painSlider.setBounds(100, 110, 200, 50);
		this.add(painSlider);
		
		painText = new JTextField("0");
		painText.addKeyListener(new KeyChanged());
		painText.setBounds(330, 120, 100, 20);
		this.add(painText);
		
		drowsinessLabel = new JLabel("DROWSINESS");
		drowsinessLabel.setBounds(10, 180, 100, 15);
		this.add(drowsinessLabel);
		
		drowsinessSlider = new JSlider();
		drowsinessSlider.setMinimum(0);
		drowsinessSlider.setMaximum(10);
		drowsinessSlider.setMajorTickSpacing(1);
		drowsinessSlider.setValue(0);
		drowsinessSlider.setPaintTicks(true);
		drowsinessSlider.setPaintLabels(true);
		drowsinessSlider.addChangeListener(new SliderChanged());
		drowsinessSlider.setBounds(100, 170, 200, 50);
		this.add(drowsinessSlider);
		
		drowsinessText = new JTextField("0");
		drowsinessText.addKeyListener(new KeyChanged());
		drowsinessText.setBounds(330, 180, 100, 20);
		this.add(drowsinessText);
		
		nauseaLabel = new JLabel("NAUSEA");
		nauseaLabel.setBounds(10, 240, 100, 15);
		this.add(nauseaLabel);
		
		nauseaSlider = new JSlider();
		nauseaSlider.setMinimum(0);
		nauseaSlider.setMaximum(10);
		nauseaSlider.setMajorTickSpacing(1);
		nauseaSlider.setValue(0);
		nauseaSlider.setPaintTicks(true);
		nauseaSlider.setPaintLabels(true);
		nauseaSlider.addChangeListener(new SliderChanged());
		nauseaSlider.setBounds(100, 230, 200, 50);
		this.add(nauseaSlider);
		
		nauseaText = new JTextField("0");
		nauseaText.addKeyListener(new KeyChanged());
		nauseaText.setBounds(330, 240, 100, 20);
		this.add(nauseaText);
		
		anxietyLabel = new JLabel("ANXIETY");
		anxietyLabel.setBounds(10, 300, 100, 15);
		this.add(anxietyLabel);
		
		anxietySlider = new JSlider();
		anxietySlider.setMinimum(0);
		anxietySlider.setMaximum(10);
		anxietySlider.setMajorTickSpacing(1);
		anxietySlider.setValue(0);
		anxietySlider.setPaintTicks(true);
		anxietySlider.setPaintLabels(true);
		anxietySlider.addChangeListener(new SliderChanged());
		anxietySlider.setBounds(100, 290, 200, 50);
		this.add(anxietySlider);
		
		anxietyText = new JTextField("0");
		anxietyText.addKeyListener(new KeyChanged());
		anxietyText.setBounds(330, 300, 100, 20);
		this.add(anxietyText);
		
		depressionLabel = new JLabel("DEPRESSION");
		depressionLabel.setBounds(10, 360, 100, 15);
		this.add(depressionLabel);
		
		depressionSlider = new JSlider();
		depressionSlider.setMinimum(0);
		depressionSlider.setMaximum(10);
		depressionSlider.setMajorTickSpacing(1);
		depressionSlider.setValue(0);
		depressionSlider.setPaintTicks(true);
		depressionSlider.setPaintLabels(true);
		depressionSlider.addChangeListener(new SliderChanged());
		depressionSlider.setBounds(100, 350, 200, 50);
		this.add(depressionSlider);
		
		depressionText = new JTextField("0");
		depressionText.addKeyListener(new KeyChanged());
		depressionText.setBounds(330, 360, 100, 20);
		this.add(depressionText);
		
		submit = new JButton("Submit");
		submit.addActionListener(new ButtonListener());
		submit.setBounds(40, 420, 100, 25);
		this.add(submit);
		
		reset = new JButton("Reset");
		reset.addActionListener(new ButtonListener());
		reset.setBounds(170, 420, 100, 25);
		this.add(reset);
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ButtonListener());
		cancel.setBounds(300, 420, 100, 25);
		this.add(cancel);
		
				
		setVisible(true);
	}
	
	private class KeyChanged implements KeyListener
	{
		@Override
		public void keyPressed(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) 
		{
			if(e.getSource() == painText)
			{
				String pain = painText.getText();
				int painLevel = Integer.parseInt(pain);
				if(painLevel > 10 || painLevel < 0)
				{
					JOptionPane.showMessageDialog(null, "Please enter a value between 0 and 10 only");
					painText.setText("");
				}
				else
				{
					painSlider.setValue(painLevel);
				}
			}
			
			if(e.getSource() == drowsinessText)
			{
				String drowsiness = drowsinessText.getText();
				int drowsinessLevel = Integer.parseInt(drowsiness);
				if(drowsinessLevel > 10 || drowsinessLevel < 0)
				{
					JOptionPane.showMessageDialog(null, "Please enter a value between 0 and 10 only");
					drowsinessText.setText("");
				}
				else
				{
					drowsinessSlider.setValue(drowsinessLevel);
				}
			}
			
			if(e.getSource() == nauseaText)
			{
				String nausea = nauseaText.getText();
				int nauseaLevel = Integer.parseInt(nausea);
				if(nauseaLevel > 10 || nauseaLevel < 0)
				{
					JOptionPane.showMessageDialog(null, "Please enter a value between 0 and 10 only");
					nauseaText.setText("");
				}
				else
				{
					nauseaSlider.setValue(nauseaLevel);
				}
			}
			
			if(e.getSource() == anxietyText)
			{
				String anxiety = anxietyText.getText();
				int anxietyLevel = Integer.parseInt(anxiety);
				if(anxietyLevel > 10 || anxietyLevel < 0)
				{
					JOptionPane.showMessageDialog(null, "Please enter a value between 0 and 10 only");
					anxietyText.setText("");
				}
				else
				{
					anxietySlider.setValue(anxietyLevel);
				}
			}
			
			if(e.getSource() == depressionText)
			{
				String depression = depressionText.getText();
				int depressionLevel = Integer.parseInt(depression);
				if(depressionLevel > 10 || depressionLevel < 0)
				{
					JOptionPane.showMessageDialog(null, "Please enter a value between 0 and 10 only");
					depressionText.setText("");
				}
				else
				{
					depressionSlider.setValue(depressionLevel);
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {}
	}
	
	private class SliderChanged implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent e) 
		{
			if(e.getSource() == painSlider)
			{
				Integer painLevel = painSlider.getValue();
				painText.setText(painLevel.toString());
			}
			
			if(e.getSource() == drowsinessSlider)
			{
				Integer drowsinessLevel = drowsinessSlider.getValue();
				drowsinessText.setText(drowsinessLevel.toString());
			}
			
			if(e.getSource() == nauseaSlider)
			{
				Integer nauseaLevel = nauseaSlider.getValue();
				nauseaText.setText(nauseaLevel.toString());
			}
			
			if(e.getSource() == anxietySlider)
			{
				Integer anxietyLevel = anxietySlider.getValue();
				anxietyText.setText(anxietyLevel.toString());
			}
			
			if(e.getSource() == depressionSlider)
			{
				Integer depressionLevel = depressionSlider.getValue();
				depressionText.setText(depressionLevel.toString());
			}
		}
		
	}
	
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			if(arg0.getSource() == submit)
			{
				String fileName = patientFile + ".xls";
				try
				{	
					String severity = "";
					int sum = 0;
					sum = (Integer.parseInt(painText.getText().trim()) + Integer.parseInt(drowsinessText.getText().trim()) + 
								Integer.parseInt(nauseaText.getText().trim()) + Integer.parseInt(anxietyText.getText().trim()) +
								Integer.parseInt(depressionText.getText().trim()));
					float average = sum/5;
					if(average < 2.5)
						severity = "Trivial";
					if(average >= 2.5 && average < 5)
						severity = "Minor";
					if(average >= 5 && average < 7.5)
						severity = "Major";
					if(average >= 7.5 && average <= 10)
						severity = "Critical";
					
					Workbook workbook = Workbook.getWorkbook(new File(fileName));
					WritableWorkbook copy = Workbook.createWorkbook(new File (fileName), workbook);
					WritableSheet data = copy.getSheet(0);
					String levels = painText.getText() + "/" + drowsinessText.getText() + "/" + nauseaText.getText() + 
													"/" + anxietyText.getText()+ "/"+ depressionText.getText();
					
					Label label = new Label(9, counter, levels);
					data.addCell(label);
					label = new Label (10, counter, severity);
					data.addCell(label);
					counter++;
					label = new Label(0, 1, counter.toString());
					data.addCell(label);
					copy.write();
					copy.close();
					JOptionPane.showMessageDialog(null, "Information Entered Correctly!");
					
				}
				catch(BiffException | IOException e)
				{
					e.printStackTrace();
				} 
				catch (RowsExceededException e) 
				{
					e.printStackTrace();
				} 
				catch (WriteException e) 
				{
					e.printStackTrace();
				}
			}
			if(arg0.getSource() == reset)
			{
				painText.setText("0");
				drowsinessText.setText("0"); 
				nauseaText.setText("0");
				anxietyText.setText("0");
				depressionText.setText("0");
				
				painSlider.setValue(0);
				drowsinessSlider.setValue(0);
				nauseaSlider.setValue(0);
				anxietySlider.setValue(0);
				depressionSlider.setValue(0);
			}
			if(arg0.getSource() == cancel)
			{
				new PatientOverview(patientFile);
				dispose();
			}
		}		
	}
}
