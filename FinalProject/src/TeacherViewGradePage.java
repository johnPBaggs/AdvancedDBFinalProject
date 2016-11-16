import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TeacherViewGradePage extends JPanel implements ActionListener{

	private JButton backButton;
	private JScrollPane scrollPane;
	private JPanel allTestGradesPanel;
	private WindowManager windowManager;
	private JPanel lastPage;
	private Connection con;
	private int teacherID;
	private int courseID;
	private TeacherTestGradesPanel[] testGradesPanels;
	
	/**
	 * Create the panel.
	 */
	public TeacherViewGradePage(int teacherID, int courseID, WindowManager manager, Connection con, JPanel lastPage) {
		this.teacherID = teacherID;
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
		ArrayList<Integer> testIDs = getAllTestIDs();
		CallableStatement cstmt = null;
		 ResultSet rs = null;
		 this.testGradesPanels = new TeacherTestGradesPanel[testIDs.size()];
		 for(int count = 0; count < testIDs.size(); count++)
		 {
			 ArrayList<TeacherOneStudentGradePanel> tempArrayList = new ArrayList<TeacherOneStudentGradePanel>();
			 try {
				 cstmt = con.prepareCall("{call TestDB.dbo.getStudentGrades(?, ?)}",
						 ResultSet.TYPE_SCROLL_INSENSITIVE,
						 ResultSet.CONCUR_READ_ONLY);
		 
				 cstmt.setInt(1, testIDs.get(count));
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
					 tempArrayList.add(new TeacherOneStudentGradePanel(rs.getString("studentName"),
							 rs.getString("grade")));
				 }
				 this.testGradesPanels[count] = new TeacherTestGradesPanel(getTestName(testIDs.get(count)), tempArrayList);
			 } catch (Exception ex) {
				 System.err.println(ex.toString());
			 }
		 }
		
	}
	
	
	private String getTestName(Integer testID) {
		String tempString = "";
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT testName FROM TestDB.dbo.test WHERE testId = " + testID.intValue());  
			tempString = rs.getString(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tempString;
	}





	private ArrayList<Integer> getAllTestIDs() {
		ArrayList<Integer> tempArrayList = new ArrayList<Integer>();
		
		
		return tempArrayList;
	}





	private void init()
	{
		setLayout(null);
		
		backButton = new JButton("Back");
		backButton.setBounds(6, 440, 117, 29);
		add(backButton);
		backButton.addActionListener(this);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 538, 422);
		add(scrollPane);
		
		allTestGradesPanel = new JPanel();
		scrollPane.setViewportView(allTestGradesPanel);
		allTestGradesPanel.setLayout(new GridLayout(this.testGradesPanels.length, 1, 0, 5));
		for(int count = 0; count < this.testGradesPanels.length; count++)
			allTestGradesPanel.add(this.testGradesPanels[count]);
	}


	

}
