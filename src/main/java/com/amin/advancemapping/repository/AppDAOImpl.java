package com.amin.advancemapping.repository;

import com.amin.advancemapping.entity.Course;
import com.amin.advancemapping.entity.Instructor;
import com.amin.advancemapping.entity.InstructorDetail;
import com.amin.advancemapping.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {
    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor getInstructorById(int id) {
       return entityManager.find(Instructor.class, id);
    }

    @Transactional
    @Override
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);

        List<Course> courses = instructor.getCourses();
        for(Course course : courses){
            course.setInstructor(null);
        }

        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Transactional
    @Override
    public void deleteInstructorDetailbyId(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

        instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);

        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i "
        + "Join fetch i.courses " + "where i.id = :data", Instructor.class);

        query.setParameter("data", id);

        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Transactional
    @Override
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Transactional
    @Override
    public void update(Course course) {
         entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
       return entityManager.find(Course.class, id);

    }

    @Transactional
    @Override
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Transactional
    @Override
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    @Transactional
    public Course getCourse(int id) {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c from Course c JOIN FETCH c.reviews where c.id = :data", Course.class);
        query.setParameter("data", id);
      Course course =  query.getSingleResult();
      return  course;
    }

    @Transactional
    @Override
    public Course findCourseAndStudents(int id) {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c from Course c JOIN FETCH c.students WHERE c.id = :id", Course.class);
        query.setParameter("id", id);
        Course course = query.getSingleResult();
        return course;
    }

    @Transactional
    @Override
    public Student findStudentAndCourses(int id) {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s from Student s JOIN FETCH s.courses WHERE s.id = :id", Student.class);
        query.setParameter("id", id);
        Student student = query.getSingleResult();
        return student;
    }

    @Transactional
    @Override
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Transactional
    @Override
    public void deleteStudentById(int id) {
        Student student = entityManager.find(Student.class, id);
        if(student != null){
            List<Course> courses = student.getCourses();

            for(Course course :courses){
                course.getStudents().remove(student);
            }

            entityManager.remove(student);
        }
    }


}
