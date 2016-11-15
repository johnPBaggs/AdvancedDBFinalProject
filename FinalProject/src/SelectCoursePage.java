import javax.swing.JPanel;

import dbo.Course;

import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class SelectCoursePage extends JPanel implements ActionListener {

	
	private List<Course> courseList;
	private int ID;
	private String role;
	private WindowManager windowManager;
	private Connection con;
	private JComboBox courseChoiceBox;
	private JButton nextButton;
	private JButton backButton;
	private JPanel lastPage;
	
	/**
	 * Create the panel.
	 */
	public SelectCoursePage(int ID, String role, WindowManager manager, Connection con, JPanel lastPage) {
		
		this.ID = ID;
		this.role = role;
		this.windowManager = manager;
		this.con = con;
		this.lastPage = lastPage;
		getCoursesInformation(); //get arrays of Course information
		initThis();
	}
	
	
	
	private void getCoursesInformation() {
		//here call procedure to get the course information for each course;
		//I recommend using an ArrayList to hold the values
		
		//use three different procedures call for each role
		//getCoursesTeacher
		//getCoursesTeacherAssistant
		//getCoursesStudent
		String callStmt = new String(); 
		if(role.equalsIgnoreCase("Teacher")) {
			callStmt = "{call TestDB.dbo.getCoursesThought(?)}";
		} else if(role.equalsIgnoreCase("Teacher Assistant")) {
			callStmt = "{call TestDB.dbo.getCoursesAssisted(?)}";
		} else {
			callStmt = "{call TestDB.dbo.getCoursesTaken(?)}";
		}
		System.out.println("call stmt:"+callStmt);
		CallableStatement cstmt = null;
		 ResultSet rs = null;
		 courseList = new ArrayList<Course>();
		  
		 try {
		        cstmt = con.prepareCall(callStmt,
		                ResultSet.TYPE_SCROLL_INSENSITIVE,
		                ResultSet.CONCUR_READ_ONLY);
		 
		        cstmt.setInt(1, ID);
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
		            Course course = new Course(
		                    rs.getString("courseId"),
		                    rs.getString("courseName"));
					courseList.add(course);
		        }
		    } catch (Exception ex) {
		    	System.err.println(ex.toString());
		    }
		
	}



	/*private void getCoursesStudent() {
		 
	}



	private ArrayList<String> getCoursesTeacherAssistant() {
		// TODO Auto-generated method stub
		return null;
	}



	private ArrayList<String> getCoursesTeacher() {
		// TODO Auto-generated method stub
		return null;
	}*/



	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource().equals(nextButton)) {
			int arrayIndex = this.courseChoiceBox.getSelectedIndex();
			System.out.println("arrayIndex = " + arrayIndex);
			chooseRoleToSetUP(arrayIndex);
		} else if(event.getSource().equals(backButton))
		{
			this.windowManager.setUpNextPage(this, this.lastPage);
		}
		
	}
	
	
	private void chooseRoleToSetUP(int arrayIndex) {
		JPanel newPanel = null;
		if(this.role.equals("teacher")) {
			newPanel = setUpTeacherHomePage(ID, this.courseList.get(arrayIndex).getCourseId());
		} else if(this.role.equals("teachingAssistant")) {
			//this.windowManager.setUpTeacherAssistantHomePage(ID, this.courseIDs[arrayIndex], this);
		} else {
			newPanel = new StudentHomePage(ID, this.courseList.get(arrayIndex).getCourseId(), this.windowManager, this.con, this);
		}
		this.windowManager.setUpNextPage(this, newPanel);
	}
	
	private TeacherHomePage setUpTeacherHomePage(int ID, int courseID)
	{
		return new TeacherHomePage(ID, courseID, this.windowManager, con, this);
	}



	private void initThis()
	{
		setLayout(null);
		String[] courseNames = new String[courseList.size()];
		for(int i=0;i<courseList.size();i++){
			courseNames[i] = courseList.get(i).getCourseName();
		}
		courseChoiceBox = new JComboBox(courseNames);
		courseChoiceBox.setBounds(145, 176, 281, 27);
		add(courseChoiceBox);
		
		nextButton = new JButton("Next");
		nextButton.setBounds(211, 235, 117, 29);
		add(nextButton);
		nextButton.addActionListener(this);
		
		backButton = new JButton("Back");
		backButton.setBounds(6, 440, 117, 29);
		add(backButton);
		backButton.addActionListener(this);
	}
}
