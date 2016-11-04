import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.sql.Connection;

import javax.swing.JButton;

public class AddOrModifyPage extends JPanel {

	private Connection con;
	private WindowManager windowManager;
	
	/**
	 * Create the panel.
	 */
	public AddOrModifyPage() {
		setLayout(null);
		
		JComboBox allQuestionsBox = new JComboBox();
		allQuestionsBox.setBounds(135, 149, 280, 27);
		add(allQuestionsBox);
		
		JLabel lblModifyExistingQuestion = new JLabel("Modify Existing Question");
		lblModifyExistingQuestion.setBounds(187, 121, 167, 16);
		add(lblModifyExistingQuestion);
		
		JButton modifyButton = new JButton("Modify");
		modifyButton.setBounds(204, 188, 117, 29);
		add(modifyButton);
		
		JButton addButton = new JButton("Add");
		addButton.setBounds(204, 285, 117, 29);
		add(addButton);
		
		JLabel lblAddNewQuestion = new JLabel("Add New Question");
		lblAddNewQuestion.setBounds(210, 257, 132, 16);
		add(lblAddNewQuestion);
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(6, 440, 117, 29);
		add(backButton);

	}
}
