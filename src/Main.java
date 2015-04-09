import javax.swing.*;

public class Main
{
	public static void main (String[] args) 
	{
		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
		{
	            	if ("Windows".equals(info.getName())) 
	        	{
	                	try 
	                	{
					UIManager.setLookAndFeel(info.getClassName());
		                	break;
				} 
		                catch (ClassNotFoundException | InstantiationException
								| IllegalAccessException
								| UnsupportedLookAndFeelException e) 
		                {
					e.printStackTrace();
				}
	        	}
        	}
		LoginScreen login = new LoginScreen();
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setSize(300, 200);
		login.setResizable(false);
		login.setVisible(true);		
	}
}
	
