package com.amin.advancemapping.repository;

import com.amin.advancemapping.entity.Instructor;
import com.amin.advancemapping.entity.InstructorDetail;

import java.beans.Introspector;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor getInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailbyId(int id);
}
