package com.tx.TruextendProblem1.services;

import com.tx.TruextendProblem1.entities.Class;
import com.tx.TruextendProblem1.entities.ClassStudentDetail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassStudentDetailService {

    private List<ClassStudentDetail> classStudentList= new ArrayList<ClassStudentDetail>(Arrays.asList(
            new ClassStudentDetail(36051, 1),
            new ClassStudentDetail(36052, 2),
            new ClassStudentDetail(36053, 3),
            new ClassStudentDetail(36051, 2),
            new ClassStudentDetail(36051, 3)
    ));

    public List<ClassStudentDetail> getClassStudentDetails() {
        return classStudentList;
    }

    public List<ClassStudentDetail> getByStudentId(int studentId) {
        return classStudentList.stream().filter(classStudent -> classStudent.getStudentId() == studentId).collect(Collectors.toList());
    }

    public List<ClassStudentDetail> getByClassCode(int classCode) {
        return classStudentList.stream().filter(classStudent -> classStudent.getCode() == classCode).collect(Collectors.toList());
    }

    public ClassStudentDetail createClassStudentDetail(ClassStudentDetail classStudent){
        classStudentList.add(classStudent);
        return classStudent;
    }
}
