package com.nidhin.cruddemo;

import com.nidhin.cruddemo.dao.AppDAO;
import com.nidhin.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sound.sampled.ReverbType;
import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){

		return runner -> {

			//createCourseAndStudents(appDAO);

			//findCourseAndStudents(appDAO);

			//findStudentAndCourses(appDAO);

			//addMoreCoursesForStudent(appDAO);

			//deleteCourse(appDAO);

			deleteStudent(appDAO);

		};
	}

	private void deleteStudent(AppDAO appDAO) {

		int theID = 1;

		System.out.println("Deleting the student: "+theID);

		appDAO.deleteStudentById(theID);

		System.out.println("Done!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {

		int theId = 2;

		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		//create more courses
		Course tempCourse1 = new Course("Around the world!");
		Course tempCourse2 = new Course("Think and Grow Rich");

		//add courses to student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		System.out.println("Updating student: "+tempStudent);
		System.out.println("Associated courses: "+tempStudent.getCourses());

		appDAO.update(tempStudent);

		System.out.println("Done!");

	}

	private void findStudentAndCourses(AppDAO appDAO) {

		int theId = 1;

		Student theStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		System.out.println("Loaded Student: "+theStudent);
		System.out.println("Associaated Course: "+theStudent.getCourses());

		System.out.println("Done!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {

		int theId = 10;

		Course tempCourse = appDAO.findCoursesAndStudentsByCourseId(theId);

		System.out.println("Loaded course: "+tempCourse);
		System.out.println("Associated Students: "+tempCourse.getStudents());

		System.out.println("Done!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {

		//crete a course
		Course tempCourse = new Course("AI and Machine Learning");

		//create the students
		Student tempStudent1 = new Student("Johny", "Chan", "jchan@email.com");
		Student tempStudent2 = new Student("Timothy", "Albany", "talbany@email.com");

		//add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		//save the course and associated students
		appDAO.save(tempCourse);

		System.out.println("Done!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {

		int id = 11;

		System.out.println("Deleting course id: "+id);

		appDAO.deleteCourseById(id);

		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int id = 11;

		System.out.println("Retrieving course for: "+id);
		Course course = appDAO.findCourseAndReviewsByCourseId(id);

		System.out.println("Teh course: "+course);
		System.out.println("Associated Reviews: "+course.getReviews());

		System.out.println("Done!!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		//create a course
		Course tempCourse = new Course("Nintendo Lab VR kit guide");

		//add some reviews
		tempCourse.addReview(new Review("Nice course to learn the kit!!"));
		tempCourse.addReview(new Review("Cool course!!"));
		tempCourse.addReview(new Review("Not a nice course!!"));

		//save the course
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("Done!!");
	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 10;

		System.out.println("Deleting course id: "+id);

		appDAO.deleteCourseById(id);

		System.out.println("Done!!");
	}

	private void upateCourse(AppDAO appDAO) {
		int id = 10;

		//find the course
		Course tempCourse = appDAO.findCourseById(id);

		//upadte the course
		System.out.println("Updating the course for "+id);
		tempCourse.setTitle("Sapiens");
		appDAO.update(tempCourse);

		System.out.println("Done!!");
	}

	private void upateInstructor(AppDAO appDAO) {

		int id = 1;
		//find instructor by id
		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Update the instructor");
		instructor.setLastName("LNU");
		appDAO.update(instructor);
		System.out.println("Done!!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 1;

		System.out.println("Finding instructor id for "+id);

		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor: "+instructor);
		System.out.println("The associated courses: "+instructor.getCourses());
		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 1;

		System.out.println("Finding instructor id for "+id);

		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("Instructor: "+instructor);

		//find courses for Instructor
		System.out.println("Finding courses for instructor id: "+id);
		List<Course> courses = appDAO.findCoursesByInstructorId(id);

		//System.out.println("Courses are "+courses);

		//associate the objects
		instructor.setCourses(courses);

		System.out.println("The associated courses: "+instructor.getCourses());

		System.out.println("Done!");

	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 1;

		System.out.println("Finding instructor id for "+id);

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Instructor: "+instructor);
		System.out.println("Associated courses: "+instructor.getCourses());
		System.out.println("Done");


	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		//create the instructor
		Instructor instructor = new Instructor("Susan", "Public", "susan@google.com");

		//create the instructor detail
		InstructorDetail instructorDetail = new InstructorDetail("Games", "www.google.com");

		//associate the objects
		instructor.setInstructorDetail(instructorDetail);

		//create courses
		Course course1 = new Course("Air Guitar - A Complete Guide");
		Course course2 = new Course("The Pinball mastercalss");

		//add courses to instructor
		instructor.add(course1);
		instructor.add(course2);
		//NOTE: This will also save teh courses, because of CascadeType.PERSIST
		//save teh intrucor
		System.out.println("Saving insstructor: "+instructor);
		System.out.println("Courses are: "+instructor.getCourses());
		appDAO.save(instructor);

		System.out.println("Done");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 3;
		appDAO.deleteInstructorDetailById(id);
		System.out.println("Done");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id =2;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
		System.out.println("Instructor detail: "+instructorDetail);
		System.out.println("Instructor: "+instructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;
		appDAO.deleteInstructorById(id);
		System.out.println("Done");
	}

	private void findInstructor(AppDAO appDAO) {

		int id = 1;
		Instructor tempInstructor = appDAO.findInstructorById(id);

		System.out.println("Instructor: "+tempInstructor);
		System.out.println("Instructor details: "+tempInstructor.getInstructorDetail());

	}

	private void createInstructor(AppDAO appDAO) {

		/*//create the instructor
		Instructor instructor = new Instructor("Chad", "Derby", "darby@google.com");

		//create the instructor detail
		InstructorDetail instructorDetail = new InstructorDetail("www.youtube.com", "Coding");*/

		//create the instructor
		Instructor instructor = new Instructor("John", "Wick", "wick@google.com");

		//create the instructor detail
		InstructorDetail instructorDetail = new InstructorDetail("www.twitter.com", "Feed");

		//associate the objects
		instructor.setInstructorDetail(instructorDetail);

		//save teh instrucror
		System.out.println("Saving the instructor "+instructor);

		appDAO.save(instructor);

		System.out.println("Done");

	}
}
