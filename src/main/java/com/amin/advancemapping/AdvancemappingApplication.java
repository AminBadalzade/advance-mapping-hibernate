package com.amin.advancemapping;

import com.amin.advancemapping.entity.Course;
import com.amin.advancemapping.entity.Instructor;
import com.amin.advancemapping.entity.InstructorDetail;
import com.amin.advancemapping.entity.Review;
import com.amin.advancemapping.repository.AppDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
//   createInstructorWithCourses(appDAO);

//			findInstructorWithCourses(appDAO);
//			findCourseByInstructorId(appDAO);
//          findInstructorWithCoursesJoinFetch(appDAO);
     		//  updateInstructor(appDAO);
//			updateCourse(appDAO);
//			deleteInstructor(appDAO);
			//deleteCourse(appDAO);
		//	createCourseAndReviews(appDAO);
//			getCourseAndReviews(appDAO);

			deleteCourse(appDAO);
        };
	}

	private void getCourseAndReviews(AppDAO appDAO) {
		int id = 12;
	Course course =	appDAO.getCourse(12);
		System.out.println(course);
		System.out.println(course.getReviews());

	}

	private void createCourseAndReviews(AppDAO appDAO) {

		Course course = new Course("Problem-solving");
		course.add(new Review("So goood"));
		course.add(new Review("So amazing"));
		course.add(new Review("So badd"));

		appDAO.saveCourse(course);
		System.out.println("Done");

	}

	private void deleteCourse(AppDAO appDAO) {
		int id =12;
		appDAO.deleteCourseById(id);
		System.out.println("Done");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;
		Course course = appDAO.findCourseById(theId);

		course.setTitle("Enjoy simple things!");
		appDAO.update(course);

		System.out.println("DOne");
	}

	private void updateInstructor(AppDAO appDAO) {
		int id = 1;
		Instructor instructor = appDAO.getInstructorById(id);
		instructor.setLastName("TESTER");

		appDAO.update(instructor);

		System.out.println("Done");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);
		System.out.println(instructor);
		System.out.println(instructor.getCourses());

		System.out.println("Done!");
	}

	private void findCourseByInstructorId(AppDAO appDAO) {
		int instructorId = 1;

		Instructor instructor = appDAO.getInstructorById(instructorId);

		System.out.println(instructor);

		List<Course> courses= appDAO.findCoursesByInstructorId(instructorId);

		instructor.setCourses(courses);

		System.out.println(instructor.getCourses());
	}

//	private void findCourseById(AppDAO appDAO) {
//		int id = 10;
//		Course course = appDAO.findCourseById(id);
//		System.out.println(course);
//
//		System.out.println("Instructor: " + course.getInstructor());
//
//	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 1;
		Instructor instructor = appDAO.getInstructorById(id);

		System.out.println(instructor);
		System.out.println("the associated courses: " + instructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor instructor = new Instructor("Aslan", "Mamedov", "aslan@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("aslan.mamedov/youtube", "judo");
		instructor.setInstructorDetail(instructorDetail);

		Course course = new Course("Programming");
		Course course2 = new Course("Guitar");
		instructor.addCourse(course);
		instructor.addCourse(course2);

		appDAO.save(instructor);

		System.out.println("Done");
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
