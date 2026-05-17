package com.amin.advancemapping.repository;

import com.amin.advancemapping.entity.Course;
import com.amin.advancemapping.entity.Instructor;
import com.amin.advancemapping.entity.InstructorDetail;

import java.beans.Introspector;
import java.util.List;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor getInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailbyId(int id);

     List<Course> findCoursesByInstructorId(int id);

     Instructor findInstructorByIdJoinFetch(int id);

     void update(Instructor instructor);

     void update(Course course);

     Course findCourseById(int id);

     void deleteCourseById(int id);

     void saveCourse(Course course);

     Course getCourse(int id);

     Course findCourseAndStudents(int id);

}
