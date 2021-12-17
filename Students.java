import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class Students extends User implements StudentInterface, java.io.Serializable{
	public String username = "";
	public String password = "";
	public String fName = "";
	public String lName = "";
	ArrayList<Courses> enrolledCourses = new ArrayList<>();

	public Students() {
	}
	
	//constructor
	public Students(String uN, String pW, String fN, String lN, ArrayList<Courses> eC) {
		this.username = uN;
		this.password = pW;
		this.fName = fN;
		this.lName = lN;
		this.enrolledCourses = eC;
	}

	
	//getters and setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public ArrayList<Courses> getEnrolledCourses() {
		return enrolledCourses;
	}
	public void setEnrolledCourses(ArrayList<Courses> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}

	//Check login
	public void studentLogin() {
		Scanner unPW = new Scanner(System.in);
		System.out.println("Enter your student username (case sensitive): ");
		String username = unPW.next();
		System.out.println("Enter your student password (case sensitive): ");
		String password = unPW.next();
		//Check username and password is correct for student name
		if (username.equals(Main.studentList.get(User.searchStudents()).getUsername()) && password.equals(Main.studentList.get(User.searchStudents()).getPassword())) {
			studentMenu();
		}
		else {
			System.out.println("Please re-enter your username and password");
			studentLogin();
		}
	}
	//View all courses
	public void viewCourses() {
		for(int i = 0; i < Main.courseList.size(); i++) {
			String courseNames = Main.courseList.get(i).getCourseName();
			System.out.println("Name: " + courseNames);
			String courseID = Main.courseList.get(i).getCourseID();
			System.out.println("CourseID: " + courseID);
			String courseInstructor = Main.courseList.get(i).getCourseInstructor();
			System.out.println("Course Instructor: " + courseInstructor);
			int courseSectionNumber = Main.courseList.get(i).getCourseSectionNumber();
			System.out.println("Course Section Number: " + courseSectionNumber);
			String courseLocation = Main.courseList.get(i).getLocation();
			System.out.println("Course Location: " + courseLocation);
		}
	}
	//View all courses that are not full
	public void viewOpenCourses() {
		for(int i = 0; i < Main.courseList.size(); i++) {
			if(Main.courseList.get(i).getCurrentStudents() <= Main.courseList.get(i).getMaxStudents()) {
				String courseNames = Main.courseList.get(i).getCourseName();
				System.out.println("Course name: " + courseNames);
				String courseID = Main.courseList.get(i).getCourseID();
				System.out.println("CourseID: " + courseID);
			}
		}
	}
	
	//Register in a student in a course
	public void registerCourse() {
		Scanner rc = new Scanner(System.in);	
		System.out.println("Enter the course name: ");
		String cn = rc.nextLine();
		System.out.println("Enter the course ID: ");
		String cID = rc.nextLine();
		System.out.println("Enter your first name: ");
		String firstName = rc.next().toLowerCase();
		System.out.println("Enter your last name: ");
		String lastName = rc.next().toLowerCase();

		String enrolledStudentString = firstName + " " + lastName;
		Students enrolledStudent = null;
		
		for(int i = 0; i < Main.studentList.size(); i++) {
			if(Main.studentList.get(i).getfName().equals(firstName) &&
					Main.studentList.get(i).getlName().equals(lastName)) {
				enrolledStudent = Main.studentList.get(i);
			}
		}
		if(enrolledStudent != null) {
			for(int i = 0; i < Main.courseList.size(); i++) {
				if(Main.courseList.get(i).getCourseID().equals(cID) && 
						Main.courseList.get(i).getCourseName().equals(cn)) {
					Main.courseList.get(i).getStudentNames().add(enrolledStudentString);
					enrolledStudent.enrolledCourses.add(Main.courseList.get(i));
					Main.courseList.get(i).setCurrentStudents(Main.courseList.get(i).getCurrentStudents()+1);

				}
			}
		}

//		Main.courseList.get(i).getStudentNames().add(studentName);
		
		System.out.println("You are now registered");
		
	}
	//Select action from student menu
	public void studentMenu() {
		Scanner sm = new Scanner(System.in);
		System.out.println("1. View all courses \n"
				+ "2. View all courses that are not full \n"
				+ "3. Register in a course \n"
				+ "4. Withdraw from a course \n"
				+ "5. View all courses that you are currently registered in \n"
				+ "6. Exit");
		System.out.println("What would you like to do?");
		String actionSelection = sm.next();
		if (actionSelection.equals("1")) {
			viewCourses();
			studentMenu();
		}
		if(actionSelection.equals("2")) {
			viewOpenCourses();
			studentMenu();
		}
		if(actionSelection.equals("3")) {
			registerCourse();
			studentMenu();
		}
		if(actionSelection.equals("5")) {
			viewRegistered();
			studentMenu();
		}
		if(actionSelection.equals("4")) {
			withdrawCourse();
			studentMenu();
		}
		if(actionSelection.equals("6")) {
			exit();
		}
		else {
			studentMenu();
		}
	}
	//Withdraw from a class
	public void withdrawCourse() {
		Scanner rc = new Scanner(System.in);	
		System.out.println("Enter the course name: ");
		String cn = rc.nextLine();
		System.out.println("Enter the course ID: ");
		String cID = rc.nextLine();
		System.out.println("Enter your first name: ");
		String firstName = rc.next().toLowerCase();
		System.out.println("Enter your last name: ");
		String lastName = rc.next().toLowerCase();

		String enrolledStudentString = firstName + " " + lastName;
		Students enrolledStudent = null;
		
		for(int i = 0; i < Main.studentList.size(); i++) {
			if(Main.studentList.get(i).getfName().equals(firstName) &&
					Main.studentList.get(i).getlName().equals(lastName)) {
				enrolledStudent = Main.studentList.get(i);
			}
		}
		if(enrolledStudent != null) {
			for(int i = 0; i < Main.courseList.size(); i++) {
				if(Main.courseList.get(i).getCourseID().equals(cID) && 
						Main.courseList.get(i).getCourseName().equals(cn)) {
					Main.courseList.get(i).getStudentNames().add(enrolledStudentString);
					enrolledStudent.enrolledCourses.remove(Main.courseList.get(i));
					Main.courseList.get(i).setCurrentStudents(Main.courseList.get(i).getCurrentStudents()-1);

				}
			}
		}
	}
	//View courses the current student is registered in 
	public void viewRegistered() {
		Scanner vr = new Scanner(System.in);
		System.out.println("Enter your first name: ");
		String firstName = vr.next().toLowerCase();
		System.out.println("Enter your last name: ");
		String lastName = vr.next().toLowerCase();
		Students enrolledStudent = null;
		String classes;
		for(int j = 0; j < Main.studentList.size(); j++) {
			if(Main.studentList.get(j).getfName().equals(firstName) &&
					Main.studentList.get(j).getlName().equals(lastName)) {
				enrolledStudent = Main.studentList.get(j);
			}
		}
		for(int i = 0; i < enrolledStudent.enrolledCourses.size(); i++) {
			System.out.println(enrolledStudent.enrolledCourses.get(i));
		}
 		
	}
	//Exit program
	public void exit() {
		Main.courseSerialize();
		Main.studentSerialize();
		System.out.println("Exiting");
		System.exit(0);
	}
}








































