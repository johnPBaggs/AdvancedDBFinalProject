import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class TeacherTestGradesPanel extends JPanel {
	private JTextField testNameField;
	private String testName;
	private ArrayList<TeacherOneStudentGradePanel> gradePanels;

	/**
	 * Create the panel.
	 * @param tempArrayList 
	 * @param string 
	 */
	public TeacherTestGradesPanel(String testName, ArrayList<TeacherOneStudentGradePanel> gradePanels) {
		this.testName = testName;
		this.gradePanels = gradePanels;
		init();
	}
	
	
	private void init()
	{
		setLayout(null);
		
		this.setMinimumSize(new Dimension(550, 300));
		this.setMaximumSize(new Dimension(550, 300));
		this.setPreferredSize(new Dimension(550, 300));
		
		testNameField = new JTextField(this.testName);
		testNameField.setHorizontalAlignment(SwingConstants.LEFT);
		testNameField.setBounds(6, 6, 538, 26);
		add(testNameField);
		testNameField.setColumns(500);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(6, 32, 538, 262);
		add(scrollPane);
		
		JPanel studentGradesPanel = new JPanel();
		scrollPane.setViewportView(studentGradesPanel);
		studentGradesPanel.setLayout(new GridLayout(this.gradePanels.size(), 1, 0, 2));
		for(int count = 0; count < this.gradePanels.size(); count++)
			studentGradesPanel.add(this.gradePanels.get(count));
	}

}
