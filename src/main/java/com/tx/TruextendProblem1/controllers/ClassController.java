package com.tx.TruextendProblem1.controllers;

import com.tx.TruextendProblem1.entities.Class;
import com.tx.TruextendProblem1.entities.Student;
import com.tx.TruextendProblem1.services.ClassService;
import com.tx.TruextendProblem1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/classes")
    public List<Class> getAllClasses(){
        return classService.getClasses();
    }

    @PostMapping("/classes")
    public Class createStudent(@RequestBody Class newClass) {
        return classService.createClass(newClass);
    }

    @DeleteMapping("/classes/{id}")
    public void deleteStudent(@PathVariable(value = "id") int classCode) {
        classService.deleteClass(classCode);
    }

    @PutMapping("/classes/{id}")
    public Class updateStudent(@PathVariable(value = "id") int classCode, @RequestBody Class newClass) {
        return classService.updateClass(classCode, newClass);
    }

}
