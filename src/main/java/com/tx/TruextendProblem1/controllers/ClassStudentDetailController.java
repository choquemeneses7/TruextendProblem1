package com.tx.TruextendProblem1.controllers;

import com.tx.TruextendProblem1.entities.Class;
import com.tx.TruextendProblem1.entities.ClassStudentDetail;
import com.tx.TruextendProblem1.entities.Student;
import com.tx.TruextendProblem1.services.ClassService;
import com.tx.TruextendProblem1.services.ClassStudentDetailService;
import com.tx.TruextendProblem1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClassStudentDetailController {

    @Autowired
    private ClassStudentDetailService classStudentDetailService;
    @Autowired
    private ClassService classService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/classStudentDetails")
    public List<ClassStudentDetail> getAllClassStudentDetail(){
        return classStudentDetailService.getClassStudentDetails();
    }

    @GetMapping("/studentClasses/{studentId}")
    public List<Class> getStudentClasses(@PathVariable(value = "studentId") int studentId){
        return classService.getByClassCode(classStudentDetailService.getByStudentId(studentId));
    }

    @GetMapping("/studentsOfClass/{classCode}")
    public List<Student> getStudentsOfClass(@PathVariable(value = "classCode") int classCode){
        return studentService.getByStudentId(classStudentDetailService.getByClassCode(classCode));
    }

    @PostMapping("/classStudentDetails")
    public ClassStudentDetail createClassStudentDetail(@RequestBody ClassStudentDetail classStudentDetail) {
        return classStudentDetailService.createClassStudentDetail(classStudentDetail);
    }
}
