import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;

public class TeacherHomePage extends JPanel implements ActionListener {

	private JButton changeQuestionButton;
	private JButton addTestButton;
	private JButton viewGradesButton;
	private JButton backButton;
	private int teacherID;
	private int courseID;
	private WindowManager windowManager;
	private Connection con;
	
	/**
	 * Create the panel.
	 */
	public TeacherHomePage(int teacherID, int courseID, WindowManager manager, Connection con) {
		this.teacherID = teacherID;
		this.courseID = courseID;
		windowManager = manager;
		this.con = con;
		initialize();

	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource().equals(changeQuestionButton)) {
			System.out.println("changeQuestionButton");
		}
		else if(event.getSource().equals(addTestButton)) {
			System.out.println("addTestButton");
		}
		else if(event.getSource().equals(viewGradesButton)) {
			System.out.println("viewGradesButton");
		} else if(event.getSource().equals(backButton)) {
			this.windowManager.setUpNextPage(this, new SelectCoursePage(teacherID, "teacher", this.windowManager, this.con));
		}
		
	}

	private void initialize() {
		setLayout(null);
		
		changeQuestionButton = new JButton("Change Questions");
		changeQuestionButton.setBounds(178, 136, 167, 29);
		add(changeQuestionButton);
		changeQuestionButton.addActionListener(this);
		
		addTestButton = new JButton("Add Test");
		addTestButton.setBounds(178, 211, 167, 29);
		add(addTestButton);
		addTestButton.addActionListener(this);
		
		viewGradesButton = new JButton("View Grades");
		viewGradesButton.setBounds(178, 295, 167, 29);
		add(viewGradesButton);
		viewGradesButton.addActionListener(this);
		
		backButton = new JButton("Back");
		backButton.setBounds(6, 440, 117, 29);
		add(backButton);
		backButton.addActionListener(this);
	}

	

}
