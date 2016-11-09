package dbo;

public class Course {
	
	private int courseId;
	private String courseName;

	public Course(String courseId, String courseName) {
		// TODO Auto-generated constructor stub
		this.courseId = Integer.parseInt(courseId);
		this.courseName = courseName;
	}

	public int getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}
	
}
