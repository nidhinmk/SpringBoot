package com.nidhin.cruddemo.dao;

import com.nidhin.cruddemo.entity.Course;
import com.nidhin.cruddemo.entity.Instructor;
import com.nidhin.cruddemo.entity.InstructorDetail;
import com.nidhin.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements  AppDAO{

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        //find the instructor
        Instructor instructor = entityManager.find(Instructor.class, id);

        //get the courses
        List<Course> courses = instructor.getCourses();

        //break association of all the courses for the instructor
        for(Course course: courses){
            course.setInstructor(null);
        }

        //delete the instructor
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

        //Remove the associated object reference
        //Break the bi-directional link
        instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {

        //create teh query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);

        //execute query
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        //create query

        TypedQuery<Instructor> query = entityManager.createQuery(
                                                        "select i from Instructor i "
                                                        + "JOIN FETCH i.courses "
                                                                + "JOIN FETCH i.instructorDetail "
                                                        + "where i.id=:data", Instructor.class);

        query.setParameter("data", id);

        //execute query
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        //retrieve teh course
        Course course = entityManager.find(Course.class, id);

        //delete teh course
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c "
                                                                + "JOIN FETCH c.reviews "
                                                                + "where c.id = :data", Course.class);

        query.setParameter("data", id);

        Course course = query.getSingleResult();
        return course;

    }

    @Override
    public Course findCoursesAndStudentsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c "
                                                            + "JOIN FETCH c.students "
                                                            + "where c.id = :data", Course.class);

        query.setParameter("data", id);

        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s "
                                                    + "JOIN FETCH s.courses "
                                                    + "where s.id = :data", Student.class);

        query.setParameter("data", id);

        Student student = query.getSingleResult();
        return student;
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        //retirevie teh studetns

        Student tempStudent = entityManager.find(Student.class, id);


        if(tempStudent != null) {
            //get teh courses
            List<Course> courses = tempStudent.getCourses();

            //break association of all courses for teh student
            for(Course course: courses){
                course.getStudents().remove(tempStudent);
            }

            //delete teh student
            entityManager.remove(tempStudent);
        }
    }
}
