import java.util.*;
import java.io.*;

public class Main {
	public static ArrayList<Courses> courseList = new ArrayList<Courses>();
	public static ArrayList<Students> studentList = new ArrayList<Students>();

	
	public static void main(String[] args) throws FileNotFoundException {
		//Scan in MyUniversityCourses file
		fileImport();
		
		//Deserialize course list
		try {
			FileInputStream fileIn = new 
					FileInputStream("MyUniversityCourses.ser");
			ObjectInputStream objIn = new ObjectInputStream(fileIn);	
			courseList = (ArrayList<Courses>)objIn.readObject();
			fileIn.close();
			objIn.close();
			
		}
		catch(IOException e){
			e.printStackTrace();
			return;
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
			return;
		}

		//Deserialize students list
		try {
			FileInputStream fileIn = new 
					FileInputStream("Students.ser");
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			studentList = (ArrayList<Students>)objIn.readObject();
			fileIn.close();
			objIn.close();
		}
		catch(IOException e){
			e.printStackTrace();
			return;
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
			return;
		}

		userType();
		
	}
	
	//Check if the user is a student or an admin
	public static void userType() {
		AdminInterface admin = new Admin();
		StudentInterface student = new Students();
		
		Scanner sOrA = new Scanner(System.in);
		System.out.println("Are you a student or admin? ");
		String studentOrAdmin = sOrA.next().toLowerCase();
		if (studentOrAdmin.equals("student")){
			student.studentLogin();
		}
		else if (studentOrAdmin.equals("admin")){
			admin.adminLogin();
		}
		else {
			userType();
		}
		sOrA.close();
	}
	
	//Scan in MyUniversityCourses.csv file, create course objects from each line,
	//add course objects to courseList ArrayList
	public static void fileImport() throws FileNotFoundException {

		Scanner fi = new Scanner(new File("MyUniversityCourses.csv"));
		@SuppressWarnings("unused")
		String firstLine = fi.nextLine();
		while(fi.hasNextLine()) {
			String fileLine = fi.nextLine();
			String[] infoSplit = fileLine.split(",");
			
			String coursename = infoSplit[0];
			String courseID = infoSplit[1];
			int maxStudents = Integer.parseInt(infoSplit[2]);
			int currentStudents = Integer.parseInt(infoSplit[3]);
			String courseInstructor = infoSplit[5];
			int courseSectionNumber = Integer.parseInt(infoSplit[6]);
			String courseLocation = infoSplit[7];
			ArrayList<String> studentNames = null;
			
			Courses newcourse = new Courses(coursename, courseID, maxStudents, currentStudents,
					courseInstructor, courseSectionNumber, courseLocation, studentNames);
			courseList.add(newcourse);
		}
		fi.close();
	}
	
	//Serialize courses
	public static void courseSerialize() {
		try {
			FileOutputStream f = 
					new FileOutputStream("MyUniversityCourses.ser");
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(courseList);
			o.close();
			f.close();
			System.out.println("Course list is serialized");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//Serialize students
	public static void studentSerialize() {
		try {
			FileOutputStream f = new FileOutputStream("Students.ser");
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(studentList);
			o.close();
			f.close();
			System.out.println("Student list is serialized");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}

























