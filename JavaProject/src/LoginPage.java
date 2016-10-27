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
	private Connection con;
	
	public LoginPage(Connection con)
	{
		this.con = con;
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
		//String[] roleNames = {"Teacher", "Teacher Assistant", "Student"};
		String[] roleNames = new String[3];
		Statement stmt;
		int i=0;
		try {
			stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT DISTINCT role FROM TestDB.dbo.roles");  
			
			while(rs.next()) { 
				System.out.println("RS:"+rs.getString(1));
				roleNames[i++] = rs.getString(1);
			}
			con.close();//not sure where to place this
		} catch (SQLException e) {
			e.printStackTrace();
		}
		roleChoice = new JComboBox<String>(roleNames);
		roleChoice.setMaximumSize(new Dimension(250, 250));
	}
	
}
