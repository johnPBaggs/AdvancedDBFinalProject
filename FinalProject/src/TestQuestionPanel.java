import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dbo.Question;

import javax.swing.JRadioButton;
import javax.swing.JTextPane;

public class TestQuestionPanel extends JPanel {
	private ButtonGroup buttonGroup;
	private JRadioButton questionChoice1;
	private JRadioButton questionChoice2;
	private JRadioButton questionChoice3;
	private JRadioButton questionChoice4;
	private JTextPane questionTextPane;
	private Question question;

	/**
	 * Create the panel.
	 */
	public TestQuestionPanel(Question question) {
		this.question = question;
		init();
	}
	
	public String getAnswer()
	{
		if(this.questionChoice1.isSelected())
			return "A";
		else if (this.questionChoice2.isSelected())
			return "B";
		else if(this.questionChoice3.isSelected())
			return "C";
		else if(this.questionChoice4.isSelected())
			return "D";
		return "E";
		
	}
	
	public Question getQuestion()
	{
		return question;
	}
	
	
	private void init()
	{
		
		setLayout(null);
		this.setMaximumSize(new Dimension(550, 220));
		this.setPreferredSize(new Dimension(550, 220));
		this.setMinimumSize(new Dimension(550, 220));
		
		questionChoice1 = new JRadioButton(question.getOptionA());
		questionChoice1.setBounds(40, 76, 458, 23);
		add(questionChoice1);
		
		questionChoice2 = new JRadioButton(question.getOptionB());
		questionChoice2.setBounds(40, 111, 458, 23);
		add(questionChoice2);
		
		questionChoice3 = new JRadioButton(question.getOptionC());
		questionChoice3.setBounds(40, 146, 458, 23);
		add(questionChoice3);
		
		questionChoice4 = new JRadioButton(question.getOptionD());
		questionChoice4.setBounds(40, 182, 458, 23);
		add(questionChoice4);
		
		buttonGroup = new ButtonGroup();
		this.buttonGroup.add(questionChoice1);
		this.buttonGroup.add(questionChoice2);
		this.buttonGroup.add(questionChoice3);
		this.buttonGroup.add(questionChoice4);
		
		questionTextPane = new JTextPane();
		questionTextPane.setBounds(6, 6, 492, 58);
		add(questionTextPane);
		questionTextPane.setText(question.getQuestion());
	}
}
