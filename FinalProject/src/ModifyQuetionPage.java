import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dbo.Question;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;

public class ModifyQuetionPage extends JPanel implements ActionListener{
	private JTextField questionField;
	private JTextField option1Field;
	private JTextField option2Field;
	private JTextField option3Field;
	private JTextField option4Field;
	private JComboBox answerChoiceBox;
	private JButton submitButton;
	private Question question;
	private WindowManager windowManager;
	private Connection con;
	private TeacherAddOrModifyPage  lastPage;
	private JButton backButton;

	

	/**
	 * Create the panel.
	 */
	public ModifyQuetionPage(Question question, WindowManager manager, Connection con, TeacherAddOrModifyPage  lastPage) 
	{
		this.question = question;
		this.windowManager = manager;
		this.con = con;
		this.lastPage = lastPage;
		init();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(submitButton)){
			StringBuilder updateStmt = new StringBuilder("update [TestDB].[dbo].question set ");
			updateStmt.append("[question] = '" + this.questionField.getText());
			updateStmt.append("',[optionA] = '" + this.option1Field.getText());
			updateStmt.append("',[optionB] = '" + this.option2Field.getText());
			updateStmt.append("',[optionC] = '" + this.option3Field.getText());
			updateStmt.append("',[optionD] = '" + this.option4Field.getText());
			updateStmt.append("',[answer] = '" + (String)this.answerChoiceBox.getSelectedItem());
			updateStmt.append("' where [questionId] = " + this.question.getQuestionId());
			Statement stmt;
			try {
				stmt = con.createStatement();
				System.out.println(updateStmt.toString());
				stmt.executeUpdate(updateStmt.toString());
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(event.getSource().equals(backButton))
		{
			this.windowManager.setUpNextPage(this, this.lastPage);
		}
		
	}
	
	private void init()
	{
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Question:");
		lblNewLabel.setBounds(30, 44, 61, 16);
		add(lblNewLabel);
		
		questionField = new JTextField();
		questionField.setText(this.question.getQuestion());
		questionField.setBounds(176, 39, 334, 26);
		add(questionField);
		questionField.setColumns(500);
		
		JLabel lblNewLabel_1 = new JLabel("Option1:");
		lblNewLabel_1.setBounds(30, 109, 61, 16);
		add(lblNewLabel_1);
		
		JLabel lblOption = new JLabel("Option2:");
		lblOption.setBounds(30, 155, 61, 16);
		add(lblOption);
		
		JLabel lblOption_1 = new JLabel("Option3:");
		lblOption_1.setBounds(30, 211, 61, 16);
		add(lblOption_1);
		
		JLabel lblOption_2 = new JLabel("Option4:");
		lblOption_2.setBounds(30, 258, 61, 16);
		add(lblOption_2);
		
		option1Field = new JTextField();
		option1Field.setText(this.question.getOptionA());
		option1Field.setBounds(176, 104, 334, 26);
		add(option1Field);
		option1Field.setColumns(500);
		
		option2Field = new JTextField();
		option2Field.setText(this.question.getOptionB());
		option2Field.setBounds(176, 150, 334, 26);
		add(option2Field);
		option2Field.setColumns(500);
		
		option3Field = new JTextField();
		option3Field.setText(this.question.getOptionC());
		option3Field.setBounds(176, 206, 334, 26);
		add(option3Field);
		option3Field.setColumns(10);
		
		option4Field = new JTextField();
		option4Field.setText(this.question.getOptionD());
		option4Field.setBounds(176, 253, 334, 26);
		add(option4Field);
		option4Field.setColumns(500);
		
		JLabel lblNewLabel_2 = new JLabel("Answer");
		lblNewLabel_2.setBounds(30, 317, 61, 16);
		add(lblNewLabel_2);
		
		answerChoiceBox = new JComboBox(new String[] {"A", "B", "C", "D"});
		answerChoiceBox.setBounds(176, 313, 154, 27);
		add(answerChoiceBox);
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(176, 370, 117, 29);
		add(submitButton);
		submitButton.addActionListener(this);
		
		backButton = new JButton("Back");
		backButton.setBounds(6, 440, 116, 29);
		add(backButton);
		backButton.addActionListener(this);
	}


	
}
