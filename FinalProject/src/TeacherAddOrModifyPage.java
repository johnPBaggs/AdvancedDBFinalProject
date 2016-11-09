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
	private JComboBox<String> allQuestionsBox;
	private Map<String,Question> questions;
	private JButton modifyButton;
	private JButton addButton;
	private JButton backButton;

	
	/**
	 * Create the panel.
	 */
	public TeacherAddOrModifyPage(int teacherID, int courseID, WindowManager manager, Connection con) {
		this.teacherID = teacherID;
		this.courseID = courseID;
		this.windowManager = manager;
		this.con = con;
		getQuestionsInformation();
		initThis();

	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(modifyButton)) {
			System.out.println("modifyButton");
		} else if(event.getSource().equals(addButton)) {
			System.out.println("addButton");
		} else if(event.getSource().equals(backButton)) {
			this.windowManager.setUpNextPage(this, new TeacherHomePage(this.teacherID, this.courseID, this.windowManager, this.con));
		}
		
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
		/*ArrayList<String> list = new ArrayList<String>();
		list.add("What is 2 + 2");
		list.add("12354");
		list.add("At what tempurature does water turn into a gas");
		list.add("12311");
		list.add("When did world war II end");
		list.add("19420");
		list.add("What is our star called");
		list.add("12316");
		
		int size = list.size() / 2;
		
		this.questionNames = new String[size];
		this.questionIDs = new int[size];
		
		for(int count = 0, counter = 0; count < size; count++) {
			this.questionNames[count] = list.get(counter++);
			this.questionIDs[count] = Integer.parseInt(list.get(counter++));
		}*/
		
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
		
		allQuestionsBox = new JComboBox<String>(questionNames);
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
		backButton.setBounds(6, 440, 117, 29);
		add(backButton);
		backButton.addActionListener(this);
	}


	
}
