import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowManager extends JFrame
{

	private LoginPage currentPanel;
	private WindowListener windowListener;
	private Connection con;
	
	public WindowManager()
	{
		try{  //this should be placed at the very beginning of the application and should be performed only once for a run. 
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			con=DriverManager.getConnection(  
			"jdbc:sqlserver://projectdb.cpfuslnxnggh.us-east-1.rds.amazonaws.com:1433","admin","adminpassword");  
			//here sonoo is database name, root is username and password  
			
			  
			}catch(Exception e)
				{ System.out.println(e);} 
		this.setTitle("ADVDatabaseFinalProject");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(750, 800);
		this.addWindowListener(new WindowListener()
				{
					public void windowClosing(WindowEvent e)
					{
						try {
							if(con.isClosed() == false)
								con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
		setUpCurrent();
		this.setVisible(true);
	}
	
	
	private void setUpCurrent()
	{
		currentPanel = new LoginPage(con);
		this.add((Component)currentPanel);
	}
	
	public static void main(String args[])
	{
		new WindowManager();
	}
	
}
