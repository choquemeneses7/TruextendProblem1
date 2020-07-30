package com.tx.TruextendProblem1.services;

import com.tx.TruextendProblem1.entities.CourseStudentDetail;
import com.tx.TruextendProblem1.exceptions.DuplicatedKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseStudentDetailService {

    private List<CourseStudentDetail> classStudentList= new ArrayList<CourseStudentDetail>(Arrays.asList(
            new CourseStudentDetail(36051, 1),
            new CourseStudentDetail(36052, 2),
            new CourseStudentDetail(36053, 3),
            new CourseStudentDetail(36051, 2),
            new CourseStudentDetail(36051, 3)
    ));

    public List<CourseStudentDetail> getClassStudentDetails() {
        return classStudentList;
    }

    public List<CourseStudentDetail> getByStudentId(int studentId) {
        return classStudentList.stream().filter(classStudent -> classStudent.getStudentId() == studentId).collect(Collectors.toList());
    }

    public List<CourseStudentDetail> getByClassCode(int classCode) {
        return classStudentList.stream().filter(classStudent -> classStudent.getCode() == classCode).collect(Collectors.toList());
    }

    public CourseStudentDetail createClassStudentDetail(CourseStudentDetail classStudent){
        if(classStudentList.stream().anyMatch(clas-> clas.getCode()==classStudent.getCode() && clas.getStudentId() == classStudent.getStudentId())){
            throw new DuplicatedKeyException("Class Code : "+classStudent.getCode()+", Student Id: "+classStudent.getStudentId());
        }
        classStudentList.add(classStudent);
        return classStudent;
    }
}
