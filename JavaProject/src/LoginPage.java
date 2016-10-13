import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.sql.*; 

public class LoginPage extends JPanel implements ActionListener
{

	private JComboBox<String> roleChoice;
	private JTextField userName;
	private JPasswordField password;
	private JButton loginButton;
	
	public LoginPage()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		initRoleChoice();
		initUserName();
		initPassword();
		initLoginButton();
		this.add(roleChoice);
		this.add(userName);
		this.add(password);
		this.add(loginButton);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://dbproject.cpfuslnxnggh.us-east-1.rds.amazonaws.com:1433","admin","adminpassword");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("CREATE TABLE TEMP");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
			
		
    }
	
	
	private void initLoginButton() {
		loginButton = new JButton("Login");
		loginButton.setMaximumSize(new Dimension(100, 30));
		loginButton.addActionListener(this);
		
	}

	private void initPassword() {
		password = new JPasswordField();
		password.setMaximumSize(new Dimension(250, 20));
	}

	private void initUserName() {
		userName = new JTextField();
		userName.setMaximumSize(new Dimension(250,20));
	}

	private void initRoleChoice()
	{
		String[] roleNames = {"Teacher", "Teacher Assistant", "Student"};
		roleChoice = new JComboBox<String>(roleNames);
		roleChoice.setMaximumSize(new Dimension(250, 250));
	}
	
}
