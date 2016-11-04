import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TeacherHomePage extends JPanel implements ActionListener {

	private JButton changeQuestionButton;
	private JButton addTestButton;
	private JButton viewGradesButton;
	private int teacherID;
	private int courseID;
	private WindowManager windowManager;
	
	/**
	 * Create the panel.
	 */
	public TeacherHomePage(int teacherID, int courseID, WindowManager manager) {
		this.teacherID = teacherID;
		this.courseID = courseID;
		windowManager = manager;
		initialize();

	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource().equals(changeQuestionButton))
		{
			System.out.println("changeQuestionButton");
		}
		else if(event.getSource().equals(addTestButton))
		{
			System.out.println("addTestButton");
		}
		else if(event.getSource().equals(viewGradesButton))
		{
			System.out.println("viewGradesButton");
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
	}

	

}
