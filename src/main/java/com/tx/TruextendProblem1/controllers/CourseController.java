package com.tx.TruextendProblem1.controllers;

import com.tx.TruextendProblem1.entities.Course;
import com.tx.TruextendProblem1.entities.Student;
import com.tx.TruextendProblem1.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public List<Course> getAllCourses(){
        return courseService.getCourses();
    }

    @GetMapping("/{courseCode}")
    public Course getCourseByCode(@PathVariable(value = "courseCode") int courseCode){
        return courseService.getCourseByCode(courseCode);
    }

    @GetMapping("/students/{courseCode}")
    public List<Student> getCoursesOfStudent(@PathVariable(value = "courseCode") int courseCode){
        return courseService.getStudentsOfCourse(courseCode);
    }
    @PostMapping("")
    public Course createStudent(@RequestBody Course newCourse) {
        return courseService.createCourse(newCourse);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable(value = "id") int courseCode) {
        courseService.deleteCourse(courseCode);
    }

    @PutMapping("/{id}")
    public Course updateStudent(@PathVariable(value = "id") int courseCode, @RequestBody Course newCourse) {
        return courseService.updateCourse(courseCode, newCourse);
    }

}
