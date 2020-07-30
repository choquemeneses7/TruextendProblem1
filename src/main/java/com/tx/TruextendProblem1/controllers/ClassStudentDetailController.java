package com.tx.TruextendProblem1.controllers;

import com.tx.TruextendProblem1.entities.Course;
import com.tx.TruextendProblem1.entities.CourseStudentDetail;
import com.tx.TruextendProblem1.entities.Student;
import com.tx.TruextendProblem1.services.CourseService;
import com.tx.TruextendProblem1.services.CourseStudentDetailService;
import com.tx.TruextendProblem1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courseStudentDetails")
public class ClassStudentDetailController {

    @Autowired
    private CourseStudentDetailService courseStudentDetailService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public List<CourseStudentDetail> getAllClassStudentDetail(){
        return courseStudentDetailService.getClassStudentDetails();
    }

    @GetMapping("/studentClasses/{studentId}")
    public List<Course> getStudentClasses(@PathVariable(value = "studentId") int studentId){
        return courseService.getByCourseCode(courseStudentDetailService.getByStudentId(studentId));
    }

    @GetMapping("/studentsOfClass/{classCode}")
    public List<Student> getStudentsOfClass(@PathVariable(value = "classCode") int classCode){
        return studentService.getByStudentId(courseStudentDetailService.getByClassCode(classCode));
    }

    @PostMapping("")
    public CourseStudentDetail createClassStudentDetail(@RequestBody CourseStudentDetail courseStudentDetail) {
        return courseStudentDetailService.createClassStudentDetail(courseStudentDetail);
    }
}
