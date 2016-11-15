import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dbo.Question;
import dbo.Test;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

public class studentTakeTestpage extends JPanel implements ActionListener{
	
	private Test test;
	private int studentID;
	private WindowManager windowManager;
	private Connection con;
	private JPanel testNameAndTimePanel;
	private JLabel testNameLabel;
	private JLabel testTimer;
	private JScrollPane testQuestionsScrollPanel;
	private TestQuestionPanel[] testQuestionsPanel;
	private JButton submitButton;
	private JPanel lastPage;
	private Question[] testQuestions;
	private JPanel questionsPanelHolder;
	private int seconds;
	private int minutes;
	private Timer timer;
	

	/**
	 * Create the panel.
	 */
	public studentTakeTestpage(Test test, int studentID, WindowManager manager, Connection con, JPanel lastPage) {
		this.test = test;
		this.studentID = studentID;
		this.windowManager = manager;
		this.con = con;
		this.lastPage = lastPage;
		this.minutes = this.test.getTime();
		this.seconds = 0;
		insertIntoTestStagging();
		init();
		timer = new Timer(1000, this);
		timer.start();
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(timer))
		{
			seconds--;
			if(minutes <= 0)
			{
				if(seconds <= 0) {
					System.out.println("quiz is done");
					timer.stop();
					quizIsDone();
					this.windowManager.setUpNextPage(this, this.lastPage);
				}
			}
			else if(seconds <= 0)
			{
				minutes--;
				seconds = 59;
			}
			this.testTimer.setText(minutes + "." + seconds);
		} else if(event.getSource().equals(submitButton))
		{
			System.out.println("submit button hit");
			timer.stop();
			quizIsDone();
			this.windowManager.setUpNextPage(this, this.lastPage);
		}
		
	}
	
	
	private void quizIsDone() {
		for(int count = 0; count < 10; count++)
		{
			StringBuilder insertStmt = new StringBuilder("INSERT INTO [TestDB].[dbo].[answers]([testId],[studentId],[questionId],[answer]) VALUES(");
			insertStmt.append(this.test.getTestID());
			insertStmt.append("," + this.studentID);
			insertStmt.append("," + this.testQuestionsPanel[count].getQuestion().getQuestionId());
			insertStmt.append(",'" + this.testQuestionsPanel[count].getAnswer() + "')");
			Statement stmt;
			try {
				stmt = con.createStatement();
				System.out.println(insertStmt.toString());
				stmt.executeUpdate(insertStmt.toString());
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		updateTestStagging();
	}
	
	
	private void updateTestStagging()
	{
		StringBuilder updateStmt = new StringBuilder("update [TestDB].[dbo].[testStagging] set ");
		updateStmt.append("[completed] = 1");
		updateStmt.append(" where [studentId] = " + this.studentID);
		Statement stmt;
		try {
			stmt = con.createStatement();
			System.out.println(updateStmt.toString());
			stmt.executeUpdate(updateStmt.toString());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private void insertIntoTestStagging() {
		StringBuilder insertStmt = new StringBuilder("INSERT INTO [TestDB].[dbo].[testStagging]([testId],[studentId],[completed]) VALUES(");

		insertStmt.append(this.test.getTestID());
		insertStmt.append("," + this.studentID);
		insertStmt.append(",0)");
		Statement stmt;
		try {
			stmt = con.createStatement();
			System.out.println(insertStmt.toString());
			stmt.executeUpdate(insertStmt.toString());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	private void init()
	{
		setLayout(null);
		
		testNameAndTimePanel = new JPanel();
		testNameAndTimePanel.setBounds(6, 6, 538, 53);
		add(testNameAndTimePanel);
		testNameAndTimePanel.setLayout(null);
		
		testNameLabel = new JLabel(this.test.getTestName());
		testNameLabel.setBounds(6, 17, 259, 16);
		testNameAndTimePanel.add(testNameLabel);
		
		testTimer = new JLabel(this.minutes + "." + this.seconds);
		testTimer.setBounds(410, 17, 86, 16);
		testNameAndTimePanel.add(testTimer);
		
		testQuestionsScrollPanel = new JScrollPane();
		testQuestionsScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		testQuestionsScrollPanel.setViewportBorder(null);
		testQuestionsScrollPanel.setBounds(6, 71, 538, 368);
		add(testQuestionsScrollPanel);
		
		getTestQuestions();
		testQuestionsPanel = new TestQuestionPanel[10];
		questionsPanelHolder = new JPanel();
		testQuestionsScrollPanel.setViewportView(questionsPanelHolder);
		questionsPanelHolder.setLayout(new GridLayout(0, 1, 0, 5));
		int xCord = 0;
		for(int i=0;i<10;i++){
			testQuestionsPanel[i] = new TestQuestionPanel(testQuestions[i]);
			this.questionsPanelHolder.add(testQuestionsPanel[i]);
		}
		submitButton = new JButton("submitButton");
		submitButton.setBounds(427, 439, 117, 29);
		add(submitButton);
		this.submitButton.addActionListener(this);
	}

	private void getTestQuestions() {
		/* use the procedure to get all the questions
		 * I would use an arrayList just like in the getCourseInformation
		 */
		CallableStatement cstmt = null;
		 ResultSet rs = null;
		 this.testQuestions = new Question[10];
		  
		 try {
		        cstmt = con.prepareCall("{call TestDB.dbo.getTestQuestions(?)}",
		                ResultSet.TYPE_SCROLL_INSENSITIVE,
		                ResultSet.CONCUR_READ_ONLY);
		 
		        cstmt.setInt(1, this.test.getTestID());
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
		        int i=0;
		        while (rs.next()) {
		            testQuestions[i] = new Question(rs.getString("questionId"),
		            		rs.getString("question"),
		            		rs.getString("optionA"),
		            		rs.getString("optionB"),
		            		rs.getString("optionC"),
		            		rs.getString("optionD"),
		            		rs.getString("answer"));
		            System.out.println("got question:"+testQuestions[i].getQuestion());
		            i++;
		        }
		    } catch (Exception ex) {
		    	System.err.println(ex.toString());
		    }
	}

	
}
