package com.nidhin.demo.rest;

import com.nidhin.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    //define @PostContruct to load the student data; only once.
    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Ava", "Nidhin"));
        theStudents.add(new Student("May", "Nidhin"));
        theStudents.add(new Student("Taylor", "Swift"));
    }

    //define endpoints for students
    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    //return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        if(studentId >=theStudents.size() || studentId <0){
            throw new StudentNotFoundException("Student Id not found "+studentId);
        }
        return theStudents.get(studentId);
    }
}
