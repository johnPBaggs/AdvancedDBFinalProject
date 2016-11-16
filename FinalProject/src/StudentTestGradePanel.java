import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StudentTestGradePanel extends JPanel {

	private String testName;
	private String grade;
	private JTextField testNameField;
	private JTextField gradeField;
	
	/**
	 * Create the panel.
	 */
	public StudentTestGradePanel(String testName, String grade) {
		this.testName = testName;
		this.grade = grade;
		System.out.println("testName = [" + this.testName + "] grade = [" + this.grade + "]");
		init();
	}
	
	
	private void init()
	{
		setLayout(null);
		this.setMaximumSize(new Dimension(550, 40));
		this.setMinimumSize(new Dimension(550, 40));
		this.setPreferredSize(new Dimension(550, 40));
		gradeField = new JTextField(this.grade.trim());
		gradeField.setBounds(6, 6, 44, 26);
		add(gradeField);
		gradeField.setColumns(10);
		
		testNameField = new JTextField(this.testName);
		testNameField.setHorizontalAlignment(SwingConstants.LEFT);
		testNameField.setBounds(62, 6, 458, 26);
		add(testNameField);
		testNameField.setColumns(500);
	}
}
