import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TeacherOneStudentGradePanel extends JPanel {
	private JTextField studentNameField;
	private JTextField gradeField;
	private String studentName;
	private String grade;

	/**
	 * Create the panel.
	 */
	public TeacherOneStudentGradePanel(String studentName, String grade) {
		this.studentName = studentName;
		this.grade = grade;
		init();
	}
	
	
	private void init()
	{
		setLayout(null);
		
		this.setMaximumSize(new Dimension(550, 40));
		this.setMinimumSize(new Dimension(550, 40));
		this.setPreferredSize(new Dimension(550, 40));
		
		studentNameField = new JTextField(this.studentName);
		studentNameField.setHorizontalAlignment(SwingConstants.LEFT);
		studentNameField.setBounds(64, 6, 321, 26);
		add(studentNameField);
		studentNameField.setColumns(255);
		
		gradeField = new JTextField(this.grade);
		gradeField.setHorizontalAlignment(SwingConstants.LEFT);
		gradeField.setBounds(6, 6, 46, 26);
		add(gradeField);
		gradeField.setColumns(1);
	}

}
