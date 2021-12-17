import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Collections;

public class Admin extends User implements AdminInterface, java.io.Serializable{
	public Admin() {
		super();
	}
	//Create student object with username, password, first name, and last name
	public void newStudent() {
		Scanner ns = new Scanner(System.in);
		System.out.println("Enter student first name: ");
		String fn = ns.nextLine();
		System.out.println("Enter student last name: ");
		String ln = ns.nextLine();
		System.out.println("Enter student username: ");
		String un = ns.nextLine();
		System.out.println("Enter student password: ");
		String pw = ns.nextLine();
		ArrayList<Courses> enrolledCourses = new ArrayList<>();
		Students student = new Students(un, pw, fn, ln, enrolledCourses);
		Main.studentList.add(student);

		
	}
	//Admin username and password check
	public void adminLogin() {
		Scanner unPW = new Scanner(System.in);
		System.out.println("Enter your username (case sensitive): ");
		String username = unPW.nextLine();
		System.out.println("Enter your password (case sensitive): ");
		String password = unPW.nextLine();

		if (username.equals("Admin") && password.equals("Admin001")) {
			menuSelect();
		}
		else {
			System.out.println("Please re-enter your username and password");
			adminLogin();
		}
	}
	//select admin menu
	public void menuSelect() {
		Scanner manageOrReports = new Scanner(System.in);
		System.out.println("Management or Reports: ");
		String menu = manageOrReports.nextLine();
		if(menu.equals("management")) {
			Scanner action = new Scanner(System.in);
			System.out.println("1. Create a new course \n"
					+ "2. Delete a course \n"
					+ "3. Edit a course \n"
					+ "4. Display information for a given course \n"
					+ "5. Add a new student to the system \n"
					+ "6. Exit");
			System.out.println("What action would you like to do? ");
			String actionSelection = action.nextLine();
			if (actionSelection.equals("1")) {
				createCourse();
				menuSelect();
			}
			if(actionSelection.equals("2")) {
				deleteCourse();
				menuSelect();
			}
			if(actionSelection.equals("3")) {
				courseEdit();
				menuSelect();
			}
			if(actionSelection.equals("4")) {
				courseDisplay();
				menuSelect();
			}
/*			if(actionSelection.equals("5")) {
				register();
				menuSelect();
			}
*/			if(actionSelection.equals("5")) {
				newStudent();
				menuSelect();
			}
			if(actionSelection.equals("6")) {
				exit();
			}
			else {
				menuSelect();
			}
		}
		else if(menu.equals("reports")) {
			Scanner action = new Scanner(System.in);
			System.out.println("1. View all courses \n"
					+ "2. View all courses that are FULL \n"
					+ "3. Write to a file the list of classes that are full \n"
					+ "4. View the names of students that are registered in a specific course \n"
					+ "5. View the list of courses that a given student is registered in \n"
					+ "6. Sort courses based on current number of students registered \n"
					+ "7. Exit \n");
			System.out.println("What action would you like to do? ");
			String actionSelection = action.nextLine();
			if (actionSelection.equals("1")) {
				viewCourse();
				menuSelect();
			}
			if(actionSelection.equals("2")) {
				viewFullCourse();
				menuSelect();
			}
			if(actionSelection.equals("3")) {
				fileWrite();
				menuSelect();
			}
			if(actionSelection.equals("4")) {
				registeredStudents();
				menuSelect();
			}
			if(actionSelection.equals("5")) {
				registeredCourses();
				menuSelect();
			}
			if(actionSelection.equals("6")) {
				sortCourses();
				menuSelect();
			}			
			if(actionSelection.equals("7")) {
				exit();
			}
			else {
				menuSelect();
			}

		}

	}
	//Create a course by entering course information
	public void createCourse() {
		Scanner nc = new Scanner(System.in);
		System.out.println("Enter course name: ");
		String courseName = nc.nextLine();
		
		System.out.println("Enter course instructor: ");
		String courseInstructor = nc.nextLine();
		
		System.out.println("Enter course location: ");
		String courseLocation = nc.nextLine();
		
		System.out.println("Enter course ID: ");
		String courseID = nc.nextLine();
		
		System.out.println("Enter maximum students: ");
		int maxStudents = nc.nextInt();
		
		System.out.println("Enter number of current students: ");
		int currentStudents = nc.nextInt();
		
		System.out.println("Enter course section number: ");
		int courseSectionNumber = nc.nextInt();
		
		ArrayList<String> enrolledStudents = new ArrayList<String>();
		
		Courses createCourse = new Courses(courseName, courseID, maxStudents, currentStudents, 
				courseInstructor, courseSectionNumber, courseLocation, enrolledStudents);
		
		Main.courseList.add(createCourse);
		System.out.println("Course created");

	}
	//Delete a course
	public void deleteCourse() {
		Main.courseList.remove(User.searchCourse());
		System.out.println("Course deleted");
	}
	//Edit a course information
	public void courseEdit() {
		Courses course = Main.courseList.get(User.searchCourse());
		Scanner ce = new Scanner(System.in);
		System.out.println("1. Max students \n"
					+ "2. Current students \n"
					+ "3. Course instrucor \n"
					+ "4. Section number \n"
					+ "5. Course location \n");
		System.out.println("What course info would you like to edit?");
		
		
		String editCourseInfo = ce.nextLine();
		
		if(editCourseInfo.equals("1")) {
			System.out.println("Enter new max students: ");
			int newMaxStudents = ce.nextInt();
			course.setMaxStudents(newMaxStudents);
		}
		else if(editCourseInfo.equals("2")) {
			System.out.println("Enter new current students: ");
			int newCurrentStudents = ce.nextInt();
			course.setCurrentStudents(newCurrentStudents);
		}
		else if(editCourseInfo.equals("3")) {
			System.out.println("Enter new course instructor: ");
			String newCourseInstructor = ce.nextLine();
			course.setCourseInstructor(newCourseInstructor);
		}
		else if(editCourseInfo.equals("4")) {
			System.out.println("Enter new course section number: ");
			int newSectionNumber = ce.nextInt();
			course.setCourseSectionNumber(newSectionNumber);
		}
		else if(editCourseInfo.equals("5")) {
			System.out.println("Enter new course location: ");
			String newLocation = ce.nextLine();
			course.setLocation(newLocation);
		}

		System.out.println("Course edited");	
	}
	//Display information for a given course by course ID
	public void courseDisplay() {
		Scanner inID = new Scanner(System.in);
		System.out.println("Enter ID number for course you want information on (case sensitive): ");
		String courseID = inID.nextLine();
		System.out.println("Enter the course name for the course you want information on (case sensitive): ");
		String courseName = inID.nextLine();			
		searchCourse(courseID, courseName);

	}
	//Register a student
/*	public void register() {
		Students student = Main.studentList.get(searchStudents());
		Courses course = Main.courseList.get(searchCourse());
		course.getStudentNames().add(student.getFName() + " " + student.getLName());
		int currentStudents = course.getCurrentStudents();
		currentStudents++;
		course.setCurrentStudents(currentStudents);
	}
*/
	//View Courses
	public void viewCourse() {
		for(int i = 0; i < Main.courseList.size(); i++) {
			String course = "Name: " + Main.courseList.get(i).getCourseName() + 
					"\n Number of Students Registered: " + Main.courseList.get(i).getCurrentStudents() + 
					"\n Max Number of Students: " + Main.courseList.get(i).getMaxStudents();
			System.out.println(course);
			try {
				for(int j = 0; j < Main.courseList.get(i).getStudentNames().size(); j++) {
				System.out.println("Enrolled Student Names: " + Main.courseList.get(i).getStudentNames().get(j)); 
				}
			}
			catch(NullPointerException e) {		
			}
		}
	
	}
	//View full courses
	public void viewFullCourse() {
		ArrayList<String> fullCourse = new ArrayList<String>();
		for(int i = 0; i < Main.courseList.size(); i++) {
			if(Main.courseList.get(i).getCurrentStudents() == Main.courseList.get(i).getMaxStudents()) {
				String fullCourseName = Main.courseList.get(i).getCourseName();
				fullCourse.add(fullCourseName);
			}
		}
		if(fullCourse.isEmpty()) {
			System.out.println("No classes are full");
		}
		else {
			System.out.println("These classes are full: ");
			for(int j = 0; j < fullCourse.size(); j++) {
				System.out.println(fullCourse.get(j));
			}
		}
	}
	//Write to a file the list of courses that are full
	public void fileWrite() {
		FileWriter fw;
		BufferedWriter bw;
		 try {
			 fw = new FileWriter("FullCourses.txt");
			 bw = new BufferedWriter(fw);
			 for(Courses fc: Main.courseList) {
				 if(fc.getCurrentStudents() >= fc.getMaxStudents()) {
					 String fullCourseName = fc.getCourseName();
					 bw.write(fullCourseName);
					 bw.newLine();
				 }
			 }
			 bw.close();

		 }
		 catch(IOException e) {
			 System.out.println("Error writing new file");
			 e.printStackTrace();
		 }
	}
	//View names of students registered in a specific course
	public void registeredStudents() {
		Scanner rs = new Scanner(System.in);
		System.out.println("Enter course ID: ");
		String courseID = rs.nextLine().toLowerCase();
		System.out.println("Enter course instructor: ");
		String courseInstructor = rs.nextLine().toLowerCase();
		String studentsRegistered = "";
		Courses registeredCourse = null;
		String classes;
		for(int i = 0; i < Main.courseList.size(); i++) {
			if(Main.courseList.get(i).getCourseID().equals(courseID) && Main.courseList.get(i).getCourseInstructor().equals(courseInstructor)) {
				registeredCourse = Main.courseList.get(i);
				}
			}
			for(int j = 0; j < registeredCourse.getStudentNames().size(); j++) {
				System.out.println(registeredCourse.getStudentNames().get(j));
		}
		
	}
/*		int i = User.searchCourse();
		ArrayList<String> registeredStudentNames = new ArrayList<>();
		for(int j = 0; j < Main.courseList.get(i).getStudentNames().size(); j++) {
			
			registeredStudentNames.add(Main.courseList.get(i).getStudentNames().get(j)); 
		}
		for(int k = 0; k < registeredStudentNames.size(); k++) {
			System.out.println(registeredStudentNames.get(k));
		}
		
		try {
			for(int j = 0; j < Main.courseList.get(i).getStudentNames().size(); j++) {
				registeredStudentNames.add(Main.courseList.get(i).getStudentNames().get(j));
			}
			for(int k = 0; k < registeredStudentNames.size(); k++) {
				System.out.println(registeredStudentNames.get(k));
			}
		}
		catch(NullPointerException e) {
		}*/
	
	//View list of courses a student is registered in
	public void registeredCourses() {
		Scanner rc = new Scanner(System.in);
		System.out.println("Enter first name: ");
		String firstName = rc.nextLine();
		System.out.println("Enter last name: ");
		String lastName = rc.nextLine();
		Students enrolledStudent = null;
		
		for(int i = 0; i < Main.studentList.size(); i++) {
			if(Main.studentList.get(i).getfName().equals(firstName) &&
					Main.studentList.get(i).getlName().equals(lastName)) {
				enrolledStudent = Main.studentList.get(i);
			}
		}
		for(int j = 0; j < enrolledStudent.enrolledCourses.size(); j++) {
			System.out.println(enrolledStudent.enrolledCourses.get(j));
		}
	}
		
	//Sort courses based on current number of students registered
	public void sortCourses() {
		ArrayList<Integer> numberCurrentStudents = new ArrayList<Integer>();
		for(Courses fc: Main.courseList) {
			int numberOfStudents = fc.getCurrentStudents();
			numberCurrentStudents.add(numberOfStudents);
		}
		Collections.sort(numberCurrentStudents);
		for(int i = 0; i < numberCurrentStudents.size(); i++) {
			System.out.println(Main.courseList.get(i).getCourseName());
		}
	}
}




























