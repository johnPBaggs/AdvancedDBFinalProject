import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class WindowManager{

	private JFrame frmAdvanceddbproject;
	private JPanel currentPanel;
	private Connection con;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowManager window = new WindowManager();
					window.frmAdvanceddbproject.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowManager() {
		try{  //this should be placed at the very beginning of the application and should be performed only once for a run. 
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			con=DriverManager.getConnection(  
			"jdbc:sqlserver://projectdb.cpfuslnxnggh.us-east-1.rds.amazonaws.com:1433","admin","adminpassword");  
			//here sonoo is database name, root is username and password  
			
			  
			}catch(Exception e)
				{ System.out.println(e);} 
		initialize();
		setUpLoginPage();
		
	}

	private void setUpLoginPage() {
		// TODO Auto-generated method stub
		currentPanel = new LoginPage(con, this);
		frmAdvanceddbproject.getContentPane().add(currentPanel, BorderLayout.CENTER);
	}
	
	public void setUpChooseCoursePage(int ID, String role, JPanel currentPanel)
	{
		currentPanel.setVisible(false);
		this.frmAdvanceddbproject.remove(currentPanel);
		this.currentPanel = new SelectCoursePage(ID, role, this);
		this.frmAdvanceddbproject.getContentPane().add(this.currentPanel, BorderLayout.CENTER);
		
	}
	
	public void setUpTeacherHomePage(int teacherID, int courseID, JPanel currentPanel)
	{
		currentPanel.setVisible(false);
		frmAdvanceddbproject.remove(currentPanel);
		this.currentPanel = new TeacherHomePage(teacherID, courseID, this);
		frmAdvanceddbproject.getContentPane().add(this.currentPanel, BorderLayout.CENTER);
		//set up new page
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdvanceddbproject = new JFrame();
		frmAdvanceddbproject.setTitle("AdvancedDBProject");
		frmAdvanceddbproject.setBounds(100, 100, 550, 500);
		frmAdvanceddbproject.addWindowListener(new WindowListener()
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
				
				frmAdvanceddbproject.setVisible(false);
				frmAdvanceddbproject.dispose();
				System.exit(0);
			}

			@Override
			public void windowOpened(WindowEvent e) {}
			@Override
			public void windowClosed(WindowEvent e) {}
			@Override
			public void windowIconified(WindowEvent e) {}
			@Override
			public void windowDeiconified(WindowEvent e) {}
			@Override
			public void windowActivated(WindowEvent e) {}
			@Override
			public void windowDeactivated(WindowEvent e) {}
		});
		//frmAdvanceddbproject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		currentPanel = new JPanel();
		
	}
	
	public static void errorMessage(String error)
	{
		JOptionPane.showMessageDialog(new JFrame(), error);
	}
	

}
