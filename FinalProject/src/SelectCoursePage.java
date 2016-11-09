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
	
	/**
	 * Create the panel.
	 */
	public SelectCoursePage(int ID, String role, WindowManager manager, Connection con) {
		
		this.ID = ID;
		this.role = role;
		this.windowManager = manager;
		this.con = con;
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
		//ArrayList<Course> list = new ArrayList<Course>();
		/*list.add("Intro to C");
		list.add("12435");
		list.add("Data Structures and Algorithms I");
		list.add("43223");
		list.add("Programming Languages");
		list.add("12425");
		System.out.println(list.size());*/
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
		/*int size = list.size();
		this.courseNames = new String[size / 2];
		this.courseIDs = new int[size / 2];
		for(int count = 0, counter = 0; count < size / 2; count++) {
			
			courseNames[count] = list.get(counter++);
			courseIDs[count] = Integer.parseInt(list.get(counter++));
		}*/
			
		
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



	private ArrayList<String> dummyFunction() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Intro to C");
		list.add("12435");
		list.add("Data Structures and Algorithms I");
		list.add("43223");
		list.add("Programming Languages");
		list.add("12425");
		return list;
		
	}



	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource().equals(nextButton)) {
			int arrayIndex = this.courseChoiceBox.getSelectedIndex();
			System.out.println("arrayIndex = " + arrayIndex);
			chooseRoleToSetUP(arrayIndex);
		} else if(event.getSource().equals(backButton))
		{
			this.windowManager.setUpNextPage(this, new LoginPage(this.con, this.windowManager));
		}
		
	}
	
	
	private void chooseRoleToSetUP(int arrayIndex) {
		JPanel newPanel = null;
		if(this.role.equals("teacher")) {
			newPanel = setUpTeacherHomePage(ID, this.courseList.get(arrayIndex).getCourseId());
		} else if(this.role.equals("teachingAssistant")) {
			//this.windowManager.setUpTeacherAssistantHomePage(ID, this.courseIDs[arrayIndex], this);
		} else {
			//this.windowManager.setUpStudentHomePage(ID, this.courseIDs[arrayIndex], this);
		}
		this.windowManager.setUpNextPage(this, newPanel);
	}
	
	private TeacherHomePage setUpTeacherHomePage(int ID, int courseID)
	{
		return new TeacherHomePage(ID, courseID, this.windowManager, con);
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
