package com.tx.TruextendProblem1.services;

import com.tx.TruextendProblem1.entities.Class;
import com.tx.TruextendProblem1.entities.ClassStudentDetail;
import com.tx.TruextendProblem1.entities.Student;
import com.tx.TruextendProblem1.exceptions.DuplicatedKeyException;
import com.tx.TruextendProblem1.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ClassService {
    private List<Class> classesList= new ArrayList<Class>(Arrays.asList(
            new Class(1, "Math", "Simple Math Class"),
            new Class(2, "Music", "Rock Alternative Class"),
            new Class(3, "Art", "Painting Mosaics Class"),
            new Class(4, "Design", "Photoshop and illustrator class"),
            new Class(5, "Training", "A programation training program")
    ));

    public List<Class> getClasses() {
        return classesList;
    }

    public List<Class> getByClassCode(List<ClassStudentDetail> details) {
        List<Class> response = new ArrayList<Class>();
        details.stream().forEach(classStudentDetail -> response.add(classesList.stream()
                .filter(itemClass -> itemClass.getCode() == classStudentDetail.getCode())
                .findFirst().get()));
        return response;
    }

    public Class createClass(Class newClass) throws DuplicatedKeyException {
        if(classesList.stream().anyMatch(cls-> cls.getCode()==newClass.getCode())){
            throw new DuplicatedKeyException("Class: "+newClass.getCode());
        }
        classesList.add(newClass);
        return newClass;
    }

    public void deleteClass(int classCode) throws ResourceNotFoundException {
        if(!classesList.removeIf(itemClass -> itemClass.getCode() == classCode)){
            throw new ResourceNotFoundException("Class: "+classCode+ " not exists");
        }
    }

    public Class updateClass(int classCode, Class newClass) throws ResourceNotFoundException {
        Optional<Class> studentFind = classesList.stream().filter(itemClass -> classCode == itemClass.getCode()).findFirst();
        if (studentFind.isPresent()){
            studentFind.map(itemClass -> classesList.set(classesList.indexOf(itemClass), newClass));
            return newClass;
        } else {
            throw new ResourceNotFoundException("Class: "+classCode+ " not exists");
        }
    }
}
