package com.tx.TruextendProblem1.controllers;

import com.tx.TruextendProblem1.entities.Course;
import com.tx.TruextendProblem1.entities.Student;
import com.tx.TruextendProblem1.services.CourseService;
import com.tx.TruextendProblem1.services.CourseStudentDetailService;
import com.tx.TruextendProblem1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseStudentDetailService courseStudentDetailService;
    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public List<Course> getAllClasses(){
        return courseService.getCourses();
    }

    @GetMapping("/students/{courseCode}")
    public List<Student> getClassesOfStudent(@PathVariable(value = "courseCode") int courseCode){
        return courseService.getStudentsOfCourse(courseCode);
    }
    @PostMapping("")
    public Course createStudent(@RequestBody Course newCourse) {
        return courseService.createCourse(newCourse);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable(value = "id") int classCode) {
        courseService.deleteCourse(classCode);
    }

    @PutMapping("/{id}")
    public Course updateStudent(@PathVariable(value = "id") int classCode, @RequestBody Course newCourse) {
        return courseService.updateCourse(classCode, newCourse);
    }

}
