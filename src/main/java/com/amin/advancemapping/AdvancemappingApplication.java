package com.amin.advancemapping;

import com.amin.advancemapping.entity.Instructor;
import com.amin.advancemapping.entity.InstructorDetail;
import com.amin.advancemapping.repository.AppDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdvancemappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancemappingApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
        return runner -> {
//			saveInstructor(appDAO);

//			findInstructor(appDAO);

//			deleteInstructor(appDAO);

//			findInstructorDetail(appDAO);

 	//	deleteInstructorDetailById(appDAO);
        };
	}

	private void deleteInstructorDetailById(AppDAO appDAO) {
		int id =3;
		appDAO.deleteInstructorDetailbyId(2);

	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id = 2;
		System.out.println("Finding instructor detail id: " + id);
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);

		System.out.println("InstructorDetail: " + instructorDetail);
		System.out.println("the associated instructor" + instructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Deleting instructor id: " + id);

		appDAO.deleteInstructorById(id);

		System.out.println("done");
	}

	private void findInstructor(AppDAO appDAO){
		Instructor instructor = appDAO.getInstructorById(1);
		System.out.println("Instructor: " + instructor);
		System.out.println("the assosiated InstructorDetail: " + instructor.getInstructorDetail());
	}

	private void saveInstructor(AppDAO appDAO) {


		Instructor instructor = new Instructor("Amin", "Badalzade", "aminbadalov32@gmail.com");

		InstructorDetail instructorDetail = new InstructorDetail(
				"http://www.amin.com", "coding");

		instructor.setInstructorDetail(instructorDetail);
		appDAO.save(instructor);

		Instructor instructor1 = new Instructor("Emin", "Badalzade", "eminbadalov@gmail.com");

		InstructorDetail instructorDetail1 = new InstructorDetail(
				"http://www.emin.com", "coding");

		instructor.setInstructorDetail(instructorDetail1);

		//this will also save the details object becasue of cascadetype.all
		appDAO.save(instructor1);
	}


}
