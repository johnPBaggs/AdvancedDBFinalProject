package dbo;

public class Test {
	
	private int testID;
	private String testName;
	private int time;
	
	public Test(int testID, String testName, int time)
	{
		this.setTestID(testID);
		this.setTestName(testName);
		this.setTime(time);
	}

	public int getTestID() {
		return testID;
	}

	public void setTestID(int testID) {
		this.testID = testID;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	

}
