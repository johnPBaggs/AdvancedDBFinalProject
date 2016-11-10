import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import dbo.Question;

import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;

public class AddTestPage extends JPanel implements ActionListener{
	private JTextField testNameField;
	private JButton backButton;
	private JButton submitButton;
	private JComboBox timeComboBox;
	private JComboBox[] questionComboBoxes;
	private int courseID;
	private WindowManager windowManager;
	private Connection con;
	private JPanel lastPage;
	private Map<String,Question> questions;
	private String[] questionNames;
	
	/**
	 * Create the panel.
	 */
	public AddTestPage(int courseID, WindowManager manager, Connection con, JPanel lastPage) {
		this.courseID = courseID;
		this.windowManager = manager;
		this.con = con;
		this.lastPage = lastPage;
		getQuestionsInformation();
		init();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(submitButton)){
			int testID = getTestID();
			System.out.println("TestID = " + testID);
			insertNewTest(testID);
			insertTestQuestions(testID);
			this.windowManager.setUpNextPage(this, this.lastPage);
		} else if(event.getSource().equals(backButton))
		{
			this.windowManager.setUpNextPage(this, this.lastPage);
		}
		
	}
	
	
	private void insertTestQuestions(int testID) {
		for(int count = 0; count < 10; count++)
		{
			StringBuilder insertStmt = new StringBuilder("INSERT INTO [TestDB].[dbo].[testQuestions]([testId],[questionId]) VALUES(");

			insertStmt.append(testID);
			insertStmt.append("," + this.questions.get((String)this.questionComboBoxes[count].getSelectedItem()).getQuestionId() + ")");
			Statement stmt;
			try {
				stmt = con.createStatement();
				System.out.println(insertStmt.toString());
				stmt.executeUpdate(insertStmt.toString());
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void insertNewTest(int testID) {
		StringBuilder insertStmt = new StringBuilder("INSERT INTO [TestDB].[dbo].[test]([testId],[testName],[time],[courseId]) VALUES(");

		insertStmt.append(testID);
		insertStmt.append(",'"+this.testNameField.getText());
		insertStmt.append("',"+Integer.parseInt((String)this.timeComboBox.getSelectedItem()));
		insertStmt.append(","+this.courseID + ")");
		Statement stmt;
		try {
			stmt = con.createStatement();
			System.out.println(insertStmt.toString());
			stmt.executeUpdate(insertStmt.toString());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private int getTestID() {
		Statement stmt;
		int testID = 0;
		int i=0;
		try {
			stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("Select Next Value For [TestDB].[dbo].[testIds] as testId");  
			if(rs.next() == true)
				testID = Integer.parseInt(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return testID;
		
	}

	private void init()
	{
		setLayout(null);
		int i=0;
		questionNames = new String[questions.size()];
		Iterator<String> keys = questions.keySet().iterator(); 
		
		while(keys.hasNext()){
			questionNames[i++] = keys.next();
		}
		this.questionComboBoxes = new JComboBox[10];
		
		JLabel lblNewLabel = new JLabel("Question1");
		lblNewLabel.setBounds(18, 6, 73, 16);
		add(lblNewLabel);
		
		JLabel lblQuestion = new JLabel("Question2");
		lblQuestion.setBounds(18, 34, 73, 16);
		add(lblQuestion);
		
		JLabel lblQuestion_1 = new JLabel("Question3");
		lblQuestion_1.setBounds(18, 62, 73, 16);
		add(lblQuestion_1);
		
		JLabel lblQuestion_2 = new JLabel("Question4");
		lblQuestion_2.setBounds(18, 90, 73, 16);
		add(lblQuestion_2);
		
		JLabel lblQuestion_3 = new JLabel("Question5");
		lblQuestion_3.setBounds(18, 118, 73, 16);
		add(lblQuestion_3);
		
		JLabel lblQuestion_4 = new JLabel("Question6");
		lblQuestion_4.setBounds(18, 146, 73, 16);
		add(lblQuestion_4);
		
		JLabel lblQuestion_5 = new JLabel("Question7");
		lblQuestion_5.setBounds(18, 174, 73, 16);
		add(lblQuestion_5);
		
		JLabel lblQuestion_6 = new JLabel("Question8");
		lblQuestion_6.setBounds(18, 202, 73, 16);
		add(lblQuestion_6);
		
		JLabel lblQuestion_7 = new JLabel("Question9");
		lblQuestion_7.setBounds(18, 230, 73, 16);
		add(lblQuestion_7);
		
		JLabel lblQuestion_8 = new JLabel("Question10");
		lblQuestion_8.setBounds(18, 258, 73, 16);
		add(lblQuestion_8);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(18, 303, 73, 16);
		add(lblTime);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(18, 345, 73, 16);
		add(lblName);
		
		backButton = new JButton("Back");
		backButton.setBounds(6, 440, 117, 29);
		add(backButton);
		backButton.addActionListener(this);
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(248, 440, 117, 29);
		add(submitButton);
		submitButton.addActionListener(this);
		
		testNameField = new JTextField();
		testNameField.setBounds(115, 340, 375, 26);
		add(testNameField);
		testNameField.setColumns(500);
		
		timeComboBox = new JComboBox();
		timeComboBox.setModel(new DefaultComboBoxModel(new String[] {"10", "30", "60"}));
		timeComboBox.setBounds(115, 299, 117, 27);
		add(timeComboBox);
		
		int y = 254;
		for(int count = 9; count >= 0; count--)
		{
			this.questionComboBoxes[count] = new JComboBox(questionNames);
			this.questionComboBoxes[count].setBounds(115, y, 375, 27);
			
			add(this.questionComboBoxes[count]);
			y -= 28;
		}
		
		/*this.questionComboBoxes[9] = new JComboBox();
		this.questionComboBoxes[9].setBounds(115, 254, 375, 27);
		add(this.questionComboBoxes[9]);
		
		this.questionComboBoxes[8] = new JComboBox();
		this.questionComboBoxes[8].setBounds(115, 226, 375, 27);
		add(this.questionComboBoxes[8]);
		
		question8ComboBox = new JComboBox();
		question8ComboBox.setBounds(115, 198, 375, 27);
		add(question8ComboBox);
		
		question7ComboBox = new JComboBox();
		question7ComboBox.setBounds(115, 170, 375, 27);
		add(question7ComboBox);
		
		question6ComboBox = new JComboBox();
		question6ComboBox.setBounds(115, 142, 375, 27);
		add(question6ComboBox);
		
		question5ComboBox = new JComboBox();
		question5ComboBox.setBounds(115, 114, 375, 27);
		add(question5ComboBox);
		
		question4ComboBox = new JComboBox();
		question4ComboBox.setBounds(115, 86, 375, 27);
		add(question4ComboBox);
		
		question3ComboBox = new JComboBox();
		question3ComboBox.setBounds(115, 58, 375, 27);
		add(question3ComboBox);
		
		question2ComboBox = new JComboBox();
		question2ComboBox.setBounds(115, 30, 375, 27);
		add(question2ComboBox);
		
		question1ComboBox = new JComboBox();
		question1ComboBox.setBounds(115, 2, 375, 27);
		add(question1ComboBox);*/
	}
	private void getQuestionsInformation() {
		/* use the procedure to get all the questions
		 * I would use an arrayList just like in the getCourseInformation
		 */
		CallableStatement cstmt = null;
		 ResultSet rs = null;
		 questions = new HashMap<String,Question>();
		  
		 try {
		        cstmt = con.prepareCall("{call TestDB.dbo.getCourseQuestions(?)}",
		                ResultSet.TYPE_SCROLL_INSENSITIVE,
		                ResultSet.CONCUR_READ_ONLY);
		 
		        cstmt.setInt(1, courseID);
		        boolean results = cstmt.execute();
		        int rowsAffected = 0;
		 
		        // Protects against lack of SET NOCOUNT in stored prodedure
		        while (results || rowsAffected != -1) {
		            if (results) {
		                rs = cstmt.getResultSet();
		                break;
		            } else {
		                rowsAffected = cstmt.getUpdateCount();
		            }
		            results = cstmt.getMoreResults();
		        }
		 
		        while (rs.next()) {
		            Question question = new Question(rs.getString("questionId"),
		            		rs.getString("question"),
		            		rs.getString("optionA"),
		            		rs.getString("optionB"),
		            		rs.getString("optionC"),
		            		rs.getString("optionD"),
		            		rs.getString("answer"));
		            questions.put(question.getQuestion(), question);
		        }
		    } catch (Exception ex) {
		    	System.err.println(ex.toString());
		    }
	}
}
