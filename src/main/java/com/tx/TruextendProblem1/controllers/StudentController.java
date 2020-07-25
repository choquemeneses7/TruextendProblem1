package com.tx.TruextendProblem1.controllers;

import com.tx.TruextendProblem1.entities.Student;
import com.tx.TruextendProblem1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentService.getStudents();
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable(value = "id") int studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable(value = "id") int studentId, @RequestBody Student student) {
        return studentService.updateStudent(studentId, student);
    }
}
