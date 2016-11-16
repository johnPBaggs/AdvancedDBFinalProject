import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dbo.Test;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentViewGradePage extends JPanel implements ActionListener{

	
	private JScrollPane scrollPane;
	private JPanel gradePanel;
	private JButton backButton;
	private int studentID;
	private int courseID;
	private WindowManager windowManager;
	private Connection con;
	private JPanel lastPage;
	private ArrayList<StudentTestGradePanel> tempArrayList;
	/**
	 * Create the panel.
	 */
	public StudentViewGradePage(int studentID, int courseID, WindowManager manager, Connection con, JPanel lastPage) {
		this.studentID = studentID;
		this.courseID = courseID;
		this.windowManager = manager;
		this.con = con;
		this.lastPage = lastPage;
		getAllTestGrades();
		init();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(backButton)) {
			this.windowManager.setUpNextPage(this, this.lastPage);
		}
		
	}
	
	
	private void getAllTestGrades() {
		CallableStatement cstmt = null;
		 ResultSet rs = null;
		 tempArrayList= new ArrayList<StudentTestGradePanel>();
		 try {
		        cstmt = con.prepareCall("{call TestDB.dbo.getStudentGrades(?, ?)}",
		                ResultSet.TYPE_SCROLL_INSENSITIVE,
		                ResultSet.CONCUR_READ_ONLY);
		 
		        cstmt.setInt(1, this.studentID);
		        cstmt.setInt(2, this.courseID);
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
		            tempArrayList.add(new StudentTestGradePanel(rs.getString("testName"),
		            		rs.getString("grade")));
		        }
		    } catch (Exception ex) {
		    	System.err.println(ex.toString());
		    }
		
	}


	private void init(){
		
		setLayout(null);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 538, 422);
		add(scrollPane);
		
		gradePanel = new JPanel();
		scrollPane.setViewportView(gradePanel);
		gradePanel.setLayout(new GridLayout(this.tempArrayList.size(), 1, 0, 10));
		for(int count = 0; count < this.tempArrayList.size(); count++)
			gradePanel.add(this.tempArrayList.get(count));
		
		backButton = new JButton("Back");
		backButton.setBounds(6, 440, 117, 29);
		add(backButton);
		backButton.addActionListener(this);
	}


	
}
