import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginPage extends JPanel implements ActionListener {
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JComboBox<String> roleChoiceField;
	private JButton loginButton;
	private WindowManager windowManager;
	private Connection con;

	/**
	 * Create the panel.
	 */
	public LoginPage(Connection con, WindowManager manager) {
		this.con = con;
		this.windowManager = manager;
		initialize();

	}
	
	public void actionPerformed(ActionEvent event) {	
		if(event.getSource().equals(loginButton))
		{
			callLoginProcedure(getLoginInformation());
		}

}


	private void callLoginProcedure(String[] loginInformation) {
		/*
		 * Send Procedure
		 * 
		 * create a callable statement
		 * pass in procedure name
		 * execute the statement
		 * get return
		 * 
		 */
		CallableStatement cstmt = null;
	    
		int id = -1;
	 
	    try {
	        cstmt = con.prepareCall(
	                "{call testDB.dbo.verifyLogin(?,?,?,?)}");
	        cstmt.registerOutParameter(1, java.sql.Types.INTEGER); //ID That is being Recieved from SQL Procedure
	        cstmt.setNString(2, loginInformation[0]); //Input 1: UserName
	        cstmt.setNString(3, loginInformation[1]); //Input 2: Password
	        cstmt.setNString(4, loginInformation[2]); //Input 3: Role
	        cstmt.execute();
	        id = cstmt.getInt(1); //Gets the return value
	    } catch (Exception ex) {System.out.println(ex);}
	    System.out.println("id:"+id);
	    if(id != -1){
	    	if(loginInformation[2].equalsIgnoreCase("teacher"))
	    		windowManager.setUpNextPage(this, setUpChooseCoursePage(id, loginInformation[2]));;
	    }
	    else
	    	WindowManager.errorMessage("Login Failed");
	    	
	}

	
	private SelectCoursePage setUpChooseCoursePage(int id, String role)
	{
		return new SelectCoursePage(id, role, this.windowManager, con);
	}
	
	private String[] getLoginInformation() {
		String[] array = new String[3];
		array[0] = userNameField.getText();
		array[1] = getPasswordString(passwordField.getPassword());
		array[2] = roleChoiceField.getSelectedItem().toString();
		//System.out.println("userName = " + array[0] + "  password = " + array[1] + "   roleChoice = " + array[2]);
		return array;
	}
	
	private String getPasswordString(char[] password) {
		String tempString = "";
		for(char character: password)
			tempString += character;
		return tempString;
	}

	private void initialize()
	{
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Role");
		lblNewLabel.setBounds(200, 166, 118, 16);
		add(lblNewLabel);
		
		roleChoiceField = new JComboBox();
		Statement stmt;
		String[] roleNames = new String[3];
		int i=0;
		try {
			stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT DISTINCT role FROM TestDB.dbo.roles");  
			
			while(rs.next()) { 
				System.out.println("RS:"+rs.getString(1));
				roleNames[i++] = rs.getString(1);
			}
			//con.close();//not sure where to place this
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//roleChoiceField.setModel(new DefaultComboBoxModel(new String[] {"Teacher", "Teacher Assistant", "Student"}));
		roleChoiceField.setModel(new DefaultComboBoxModel(roleNames));
		roleChoiceField.setBounds(200, 192, 130, 27);
		add(roleChoiceField);
		
		JLabel lblNewLabel_1 = new JLabel("UserName");
		lblNewLabel_1.setBounds(200, 231, 118, 16);
		add(lblNewLabel_1);
		
		userNameField = new JTextField();
		userNameField.setBounds(200, 247, 130, 26);
		add(userNameField);
		userNameField.setColumns(255);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(200, 280, 118, 16);
		add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(255);
		passwordField.setBounds(200, 296, 130, 26);
		add(passwordField);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(201, 334, 117, 29);
		add(loginButton);
		loginButton.addActionListener(this);
	}
}
