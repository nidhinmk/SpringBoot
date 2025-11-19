package com.nidhin.cruddemo.dao;

import com.nidhin.cruddemo.entity.Instructor;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);
}
