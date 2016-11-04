import javax.swing.JPanel;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class SelectCoursePage extends JPanel implements ActionListener {

	
	private String[] courseNames;
	private int[] courseIDs;
	private int ID;
	private WindowManager windowManager;
	private JComboBox courseChoiceBox;
	private JButton nextButton;
	
	/**
	 * Create the panel.
	 */
	public SelectCoursePage(int ID, String role, WindowManager manager) {
		
		this.ID = ID;
		this.windowManager = manager;
		getCoursesInformation(role); //get arrays of Course information
		initThis();
	}
	
	
	
	private void getCoursesInformation(String role) {
		//here call procedure to get the course information for each course;
		//I recommend using an ArrayList to hold the values
		
		//use three different procedures call for each role
		//getCoursesTeacher
		//getCoursesTeacherAssistant
		//getCoursesStudent
		ArrayList<String> list = new ArrayList<String>();
		list.add("Intro to C");
		list.add("12435");
		list.add("Data Structures and Algorithms I");
		list.add("43223");
		list.add("Programming Languages");
		list.add("12425");
		System.out.println(list.size());
		
		/*if(role.equals("Teacher")) {
			list = getCoursesTeacher();
		} else if(role.equals("Teacher Assistant")) {
			list = getCoursesTeacherAssistant();
		} else {
			list = getCoursesStudent();
		}*/
		
		int size = list.size();
		this.courseNames = new String[size / 2];
		this.courseIDs = new int[size / 2];
		for(int count = 0, counter = 0; count < size / 2; count++) {
			
			courseNames[count] = list.get(counter++);
			courseIDs[count] = Integer.parseInt(list.get(counter++));
		}
			
		
	}



	private ArrayList<String> getCoursesStudent() {
		// TODO Auto-generated method stub
		return null;
	}



	private ArrayList<String> getCoursesTeacherAssistant() {
		// TODO Auto-generated method stub
		return null;
	}



	private ArrayList<String> getCoursesTeacher() {
		// TODO Auto-generated method stub
		return null;
	}



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
		
		if(event.getSource().equals(nextButton))
		{
			
			
		}
		
	}
	
	
	private void initThis()
	{
		setLayout(null);
		
		courseChoiceBox = new JComboBox(courseNames);
		courseChoiceBox.setBounds(145, 176, 281, 27);
		add(courseChoiceBox);
		
		nextButton = new JButton("Next");
		nextButton.setBounds(211, 235, 117, 29);
		add(nextButton);
		nextButton.addActionListener(this);
	}
}
