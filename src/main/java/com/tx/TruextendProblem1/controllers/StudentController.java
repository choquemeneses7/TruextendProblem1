package com.tx.TruextendProblem1.controllers;

import com.tx.TruextendProblem1.entities.Course;
import com.tx.TruextendProblem1.entities.Student;
import com.tx.TruextendProblem1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public List<Student> getAllStudents(){
        return studentService.getStudents();
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable(value = "studentId") int studentId){
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/courses/{studentId}")
    public List<Course> getCoursesOfStudent(@PathVariable(value = "studentId") int studentId){
        return studentService.getCoursesOfStudent(studentId);
    }

    @PostMapping("")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable(value = "id") int studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable(value = "id") int studentId, @RequestBody Student student) {
        return studentService.updateStudent(studentId, student);
    }
}
