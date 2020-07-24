package com.tx.TruextendProblem1.controllers;

import com.tx.TruextendProblem1.entities.Class;
import com.tx.TruextendProblem1.entities.ClassStudentDetail;
import com.tx.TruextendProblem1.services.ClassService;
import com.tx.TruextendProblem1.services.ClassStudentDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClassStudentDetailController {

    @Autowired
    private ClassStudentDetailService classStudentDetailService;

    @GetMapping("/classStudentDetails")
    public List<ClassStudentDetail> getAllClassStudentDetail(){
        return classStudentDetailService.getClassStudentDetails();
    }

    @PostMapping("/classStudentDetails")
    public ClassStudentDetail createClassStudentDetail(@RequestBody ClassStudentDetail classStudentDetail) {
        return classStudentDetailService.createClassStudentDetail(classStudentDetail);
    }
}
