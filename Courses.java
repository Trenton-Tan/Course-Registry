import java.util.ArrayList;
import java.io.Serializable;

public class Courses implements Serializable {
	private String courseName = "";
	private String courseID = "";
	private int maxStudents = 0;
	private int currentStudents = 0;
	private String courseInstructor = "";
	private int courseSectionNumber = 0;
	private String location = "";
	private ArrayList<String> studentNames = new ArrayList<>();	
	
	public Courses() {
	}

	//constructor
	public Courses(String cNa, String cID, int mS, int cS, String cI, int cNo,
			String l, ArrayList<String> sN) {
		this.courseName = cNa;
		this.courseID = cID;
		this.maxStudents = mS;
		this.currentStudents = cS;
		this.courseInstructor = cI;
		this.courseSectionNumber = cNo;
		this.location = l;
		this.studentNames = sN;
	}
	
	//printing out all variable information
/*	public void print() {
		
		System.out.println("Course Names: ");
		System.out.println(this.courseName);
		
		System.out.println("Student ID: ");
		System.out.println(this.courseID);
		
		System.out.println("Max Students: ");
		System.out.println(this.maxStudents);
		
		System.out.println("Current Student: ");
		System.out.println(this.currentStudents);
		
		System.out.println("Instructor Name: ");
		System.out.println(this.courseInstructor);

		System.out.println("Course Number: ");
		System.out.println(this.courseSectionNumber);

		System.out.println("Course Location: ");
		System.out.println(this.location);
		
		System.out.println("Student Names: ");
		for (int i = 0; i < studentNames.size(); i++) {
			System.out.println(studentNames.get(i));
		}
	}
	*/
	
	//getters and setters
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public int getMaxStudents() {
		return maxStudents;
	}
	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}
	public int getCurrentStudents() {
		return currentStudents;
	}
	public void setCurrentStudents(int currentStudents) {
		this.currentStudents = currentStudents;
	}
	public String getCourseInstructor() {
		return courseInstructor;
	}
	public void setCourseInstructor(String courseInstructor) {
		this.courseInstructor = courseInstructor;
	}
	public int getCourseSectionNumber() {
		return courseSectionNumber;
	}
	public void setCourseSectionNumber(int courseSectionNumber) {
		this.courseSectionNumber = courseSectionNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public ArrayList<String> getStudentNames() {
		return studentNames;
	}
	public void setStudentNames(ArrayList<String> studentNames) {
		this.studentNames = studentNames;
	}
	
	
}
