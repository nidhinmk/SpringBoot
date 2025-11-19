package com.nidhin.cruddemo;

import com.nidhin.cruddemo.dao.AppDAO;
import com.nidhin.cruddemo.entity.Instructor;
import com.nidhin.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){

		return runner -> {
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			deleteInstructorDetail(appDAO);
		};
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
