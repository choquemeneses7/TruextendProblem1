package com.tx.TruextendProblem1.controllers;

import com.tx.TruextendProblem1.entities.Course;
import com.tx.TruextendProblem1.entities.Student;
import com.tx.TruextendProblem1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<Student> getStudentById(@PathVariable(value = "studentId") int studentId){
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/{studentId}/courses")
    public List<Course> getCoursesOfStudent(@PathVariable(value = "studentId") int studentId){
        return studentService.getCoursesOfStudent(studentId);
    }

    @PostMapping("")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PostMapping("/{studentId}")
    public List<Course> addStudentsToCourse(@PathVariable(value = "studentId") int studentId, @RequestBody List<Course> courses) {
        return studentService.addCoursesToStudent(studentId,courses);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable(value = "studentId") int studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable(value = "studentId") int studentId, @RequestBody Student student) {
        return studentService.updateStudent(studentId, student);
    }
}
