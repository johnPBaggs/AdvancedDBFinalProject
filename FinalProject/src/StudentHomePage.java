import javax.swing.JPanel;

import dbo.Question;
import dbo.Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class StudentHomePage extends JPanel implements ActionListener{

	private int studentID;
	private int courseID;
	private WindowManager windowManager;
	private JPanel lastPage;
	private Connection con;
	private JButton takeTestButton;
	private JButton viewGradesButton;
	private JButton backButton;
	private Map<String,Test> testToTake;
	private String[] testNames;
	private JComboBox comboBox;
	
	
	
	/**
	 * Create the panel.
	 */
	public StudentHomePage(int studentID, int courseID, WindowManager manager, Connection con, JPanel lastpage) {
		this.studentID = studentID;
		this.courseID = courseID;
		this.windowManager = manager;
		this.con = con;
		this.lastPage = lastpage;
		getTestToTake();
		init();
	}
	
	private void getTestToTake() {
		/* use the procedure to get all the questions
		 * I would use an arrayList just like in the getCourseInformation
		 */
		CallableStatement cstmt = null;
		 ResultSet rs = null;
		 this.testToTake = new HashMap<String,Test>();
		  
		 try {
		        cstmt = con.prepareCall("{call TestDB.dbo.getCourseAvailableTests(?, ?)}",
		                ResultSet.TYPE_SCROLL_INSENSITIVE,
		                ResultSet.CONCUR_READ_ONLY);
		 
		        cstmt.setInt(1, this.studentID);
		        cstmt.setInt(2, courseID);
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
		            Test test = new Test(Integer.parseInt(rs.getString("testId")),
		            		rs.getString("testName"),
		            		Integer.parseInt(rs.getString("time")));
		            this.testToTake.put(test.getTestName(), test);
		        }
		    } catch (Exception ex) {
		    	System.err.println(ex.toString());
		    }
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(this.takeTestButton)) {
			this.windowManager.setUpNextPage(this, new studentTakeTestpage(this.testToTake.get(this.comboBox.getSelectedItem()),
					this.studentID, this.windowManager, this.con, this));
		} else if(event.getSource().equals(this.viewGradesButton)) {
			this.windowManager.setUpNextPage(this, new StudentViewGradePage(this.studentID, this.courseID, this.windowManager, this.con, this));
		} else if(event.getSource().equals(backButton)) {
			this.windowManager.setUpNextPage(this, this.lastPage);
		}
		
	}
	
	private void init()
	{
		setLayout(null);
		int i=0;
		testNames = new String[this.testToTake.size()];
		Iterator<String> keys = this.testToTake.keySet().iterator(); 
		
		while(keys.hasNext()){
			testNames[i++] = keys.next();
		}
		
		takeTestButton = new JButton("Take Test");
		takeTestButton.setBounds(201, 139, 117, 29);
		add(takeTestButton);
		this.takeTestButton.addActionListener(this);
		
		viewGradesButton = new JButton("View Grades");
		viewGradesButton.setBounds(201, 243, 117, 29);
		add(viewGradesButton);
		this.viewGradesButton.addActionListener(this);
		
		backButton = new JButton("Back");
		backButton.setBounds(6, 440, 117, 29);
		add(backButton);
		this.backButton.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("Tests");
		lblNewLabel.setBounds(87, 113, 61, 16);
		add(lblNewLabel);
		
		comboBox = new JComboBox(testNames);
		comboBox.setMaximumRowCount(200);
		comboBox.setBounds(149, 111, 295, 16);
		add(comboBox);
	}
	
}
