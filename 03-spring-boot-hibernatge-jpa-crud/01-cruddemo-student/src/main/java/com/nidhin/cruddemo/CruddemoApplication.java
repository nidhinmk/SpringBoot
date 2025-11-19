package com.nidhin.cruddemo;

import com.nidhin.cruddemo.dao.StudentDAO;
import com.nidhin.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			//createStudent(studentDAO);
			
			createMultipleStudents(studentDAO);

			//readStudent(studentDAO);

			//queryforStudents(studentDAO);

			//queryforStudentsByLastName(studentDAO);

			//updateStudent(studentDAO);

			//deleteStudent(studentDAO);

			//deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Delete all students");
		int rowsDeleted = studentDAO.deleteAll();
		System.out.println(rowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Deleting student id: "+studentId);

		studentDAO.delete(studentId);

	}

	private void updateStudent(StudentDAO studentDAO) {
		//retrieve student based on the id: primary key
		int studentId = 1;

		System.out.println("Getting student with id: "+studentId);
		Student myStudent = studentDAO.findById(studentId);

		//change first name to "Scooby"
		System.out.println("Updating the student");
		myStudent.setFirstName("John");

		//update the student
		studentDAO.update(myStudent);

		//display the updated student
		System.out.println("Updated student: "+myStudent);
		//queryforStudents(studentDAO);
	}

	private void queryforStudentsByLastName(StudentDAO studentDAO) {
		//get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Wick");

		//display list of students
		for (Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}

	}

	private void queryforStudents(StudentDAO studentDAO) {
		//get a list of students
		List<Student> theStudents = studentDAO.findAll();

		//display the list of students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		//create a student object
		System.out.println("Create the Student object");
		Student tempStudent = new Student("Abraham", "Lincon", "lincon@email.com");

		//save the student object
		System.out.println("Save the student object");
		studentDAO.save(tempStudent);

		//display id of the student object
		System.out.println("Saved student: "+tempStudent.getId());

		//retrieve student based on the id: primary key
		System.out.println("Retrieving Student with  id: "+ tempStudent.getId());
		Student myStudent = studentDAO.findById(tempStudent.getId());

		//display student
		System.out.println("Found the student: "+myStudent);

	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		//create multiple students
		System.out.println("Creating 3 Students");
		Student tempStudent1 = new Student("John", "Wick", "wick@email.com");
		Student tempStudent2 = new Student("Jane", "Wick", "jane@email.com");
		Student tempStudent3 = new Student("Tom", "George", "tom@email.com");

		//save the student objects
		System.out.println("Saving the student");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {

		//create the student object
		System.out.println("Creating the Student");
		Student tempStudent = new Student("John", "Doe", "john.doe@email.com");

		//save the student object
		System.out.println("Saving the student");
		studentDAO.save(tempStudent);

		//display the id of the saved student
		System.out.println("Saved Student "+ tempStudent.getId());
	}
}
