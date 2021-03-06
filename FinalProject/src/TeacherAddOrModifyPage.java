import javax.swing.JPanel;


import dbo.Question;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;

public class TeacherAddOrModifyPage extends JPanel implements ActionListener{

	private Connection con;
	private WindowManager windowManager;
	private int teacherID;
	private int courseID;
	private String[] questionNames;
	private JComboBox allQuestionsBox;
	private Map<String,Question> questions;
	private JButton modifyButton;
	private JButton addButton;
	private JButton backButton;
	private JPanel lastPage;

	
	/**
	 * Create the panel.
	 */
	public TeacherAddOrModifyPage(int teacherID, int courseID, WindowManager manager, Connection con, JPanel lastPage) {
		this.teacherID = teacherID;
		this.courseID = courseID;
		this.windowManager = manager;
		this.con = con;
		this.lastPage = lastPage;
		getQuestionsInformation();
		initThis();

	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(modifyButton)) {
			String currentQuestion = (String)this.allQuestionsBox.getSelectedItem();
			this.windowManager.setUpNextPage(this, new ModifyQuestionPage(questions.get(currentQuestion), this.windowManager, this.con, this));
		} else if(event.getSource().equals(addButton)) {
			this.windowManager.setUpNextPage(this, new AddQuestionPage(this.windowManager, this.con, this));
		} else if(event.getSource().equals(backButton)) {
			this.windowManager.setUpNextPage(this, this.lastPage);
		}
		
	}
	
	
	public int getCourseID()
	{
		return this.courseID;
	}
	
	public int getTeacherID()
	{
		return this.teacherID;
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

	
	
	private void initThis()
	{
		setLayout(null);
		int i=0;
		questionNames = new String[questions.size()];
		Iterator<String> keys = questions.keySet().iterator(); 
		
		while(keys.hasNext()){
			questionNames[i++] = keys.next();
		}
		
		allQuestionsBox = new JComboBox(questionNames);
		allQuestionsBox.setBounds(135, 149, 280, 27);
		add(allQuestionsBox);
		
		JLabel lblModifyExistingQuestion = new JLabel("Modify Existing Question");
		lblModifyExistingQuestion.setBounds(187, 121, 167, 16);
		add(lblModifyExistingQuestion);
		
		modifyButton = new JButton("Modify");
		modifyButton.setBounds(204, 188, 117, 29);
		add(modifyButton);
		modifyButton.addActionListener(this);
		
		addButton = new JButton("Add");
		addButton.setBounds(204, 285, 117, 29);
		add(addButton);
		addButton.addActionListener(this);
		
		JLabel lblAddNewQuestion = new JLabel("Add New Question");
		lblAddNewQuestion.setBounds(210, 257, 132, 16);
		add(lblAddNewQuestion);
		
		backButton = new JButton("Back");
		backButton.setBounds(6, 440, 116, 29);
		add(backButton);
		backButton.addActionListener(this);
	}


	
}
