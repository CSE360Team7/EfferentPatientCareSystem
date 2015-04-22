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
		
		new LoginScreen();	
	}
}
	
