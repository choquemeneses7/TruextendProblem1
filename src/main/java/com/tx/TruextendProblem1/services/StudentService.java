package com.tx.TruextendProblem1.services;

import com.tx.TruextendProblem1.entities.ClassStudentDetail;
import com.tx.TruextendProblem1.entities.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class StudentService {

    private List<Student> studentList= new ArrayList<Student>();

    public List<Student> getAll() {
        return studentList;
    }

    public Student create(Student student) {
        studentList.add(student);
        return student;
    }

    public void delete(int studentId) {
        studentList.removeIf(student -> student.getStudentId()== studentId);
    }

    public Student update(int studentId, Student student) {
        OptionalInt classIndex = IntStream.range(0, studentList.size())
                .filter(i -> studentList.get(i).getStudentId()==studentId)
                .findFirst();
        studentList.set(classIndex.getAsInt(), student);
        return student;
    }
}
