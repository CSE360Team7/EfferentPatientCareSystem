import javax.swing.*;

public class Main
{
	public static void main (String[] args) 
	{
		LoginScreen login = new LoginScreen();
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setSize(300, 200);
		login.setResizable(false);
		login.setVisible(true);		
	}
}
	