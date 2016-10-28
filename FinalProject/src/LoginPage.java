import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

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
		System.out.println("button Pressed ");
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
		windowManager.setUpTeacherHomePage(-10, this);
	}

	private String[] getLoginInformation() {
		String[] array = new String[3];
		array[0] = userNameField.getText();
		array[1] = getPasswordString(passwordField.getPassword());
		array[2] = roleChoiceField.getSelectedItem().toString();
		System.out.println("userName = " + array[0] + "  password = " + array[1] + "   roleChoice = " + array[2]);
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
		roleChoiceField.setModel(new DefaultComboBoxModel(new String[] {"Teacher", "Teacher Assistant", "Student"}));
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
