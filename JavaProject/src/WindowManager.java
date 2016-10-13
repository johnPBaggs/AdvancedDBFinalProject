import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowManager extends JFrame
{

	private LoginPage currentPanel;
	
	public WindowManager()
	{
		this.setTitle("ADVDatabaseFinalProject");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(750, 800);
		
		setUpCurrent();
		this.setVisible(true);
	}
	
	
	private void setUpCurrent()
	{
		currentPanel = new LoginPage();
		this.add((Component)currentPanel);
	}
	
	public static void main(String args[])
	{
		new WindowManager();
	}
	
}
