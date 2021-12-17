
import java.util.*;
import java.io.*;

public class User implements Serializable{

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	public User() {
	}
	public User(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	//search for courses with section number and return courseList index
	public static int searchCourse() {	
		Scanner sc = new Scanner(System.in);
		System.out.println("Course instructor: ");
		String courseInstructor = sc.nextLine().toLowerCase();
		System.out.println("Course name: ");
		String courseName = sc.nextLine().toLowerCase();

				
		for(int i = 0; i < Main.courseList.size(); i++) {
			if(Main.courseList.get(i).getCourseName().toLowerCase().equals(courseName) && Main.courseList.get(i).getCourseInstructor().toLowerCase().equals(courseInstructor)) {
				return i;
			}
		}
		return -1;
	}
	//Print course information given course ID
	public static void viewCourses(int i) {
		String courseNames = Main.courseList.get(i).getCourseName();
		System.out.println("Name: " + courseNames);
		String courseID = Main.courseList.get(i).getCourseID();
		System.out.println("CourseID: " + courseID);
		int maxStudents = Main.courseList.get(i).getMaxStudents();
		System.out.println("Max Students: " + maxStudents);	
		int currentStudents = Main.courseList.get(i).getCurrentStudents();
		System.out.println("Current Students: " + currentStudents);
		String courseInstructor = Main.courseList.get(i).getCourseInstructor();
		System.out.println("Course Instructor: " + courseInstructor);
		int courseSectionNumber = Main.courseList.get(i).getCourseSectionNumber();
		System.out.println("Course Section Number: " + courseSectionNumber);
		String courseLocation = Main.courseList.get(i).getLocation();
		System.out.println("Course Location: " + courseLocation);
	}
	//search for courses with id and display course information
	public static void searchCourse(String id, String courseName) {
		for(int i = 0; i < Main.courseList.size(); i++) {
			if(Main.courseList.get(i).getCourseID().equals(id) && Main.courseList.get(i).getCourseName().equals(courseName)) {
				viewCourses(i);
			}
		}
	}
	//search for student first and last name and return index
	public static int searchStudents() {
		Scanner sn = new Scanner(System.in);
		System.out.println("Student first name: ");
		String studentFName = sn.nextLine();
		System.out.println("Student last name: ");
		String studentLName = sn.nextLine();
				
		for(int i = 0; i < Main.studentList.size(); i++) {
			if(Main.studentList.get(i).getfName().equals(studentFName) && Main.studentList.get(i).getlName().equals(studentLName)) {
				return i;
			}
		}	
		return -1;
	}
	//Exit program
	public void exit() {
		Main.courseSerialize();
		Main.studentSerialize();
		System.out.println("Exiting");
		System.exit(0);
	}
}














