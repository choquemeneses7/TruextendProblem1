package com.tx.TruextendProblem1.controllers;

import com.tx.TruextendProblem1.entities.Course;
import com.tx.TruextendProblem1.entities.Student;
import com.tx.TruextendProblem1.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<Course> getCourseByCode(@PathVariable(value = "courseCode") int courseCode){
        return courseService.getCourseByCode(courseCode);
    }

    @GetMapping("/{courseCode}/students")
    public List<Student> getCoursesOfStudent(@PathVariable(value = "courseCode") int courseCode){
        return courseService.getStudentsOfCourse(courseCode);
    }
    @PostMapping("")
    public Course createStudent(@RequestBody Course newCourse) {
        return courseService.createCourse(newCourse);
    }

    @PostMapping("/{courseCode}")
    public List<Student> addStudentsToCourse(@PathVariable(value = "courseCode") int courseCode, @RequestBody List<Student> students) {
        return courseService.addStudentsToCourse(courseCode,students);
    }

    @DeleteMapping("/{courseCode}")
    public void deleteStudent(@PathVariable(value = "id") int courseCode) {
        courseService.deleteCourse(courseCode);
    }

    @PutMapping("/{courseCode}")
    public Course updateStudent(@PathVariable(value = "courseCode") int courseCode, @RequestBody Course newCourse) {
        return courseService.updateCourse(courseCode, newCourse);
    }

}
