package com.tx.TruextendProblem1.services;

import com.tx.TruextendProblem1.entities.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class StudentService {

    private List<Student> studentList= new ArrayList<Student>();

    public List<Student> getStudents() {
        return studentList;
    }

    public Student createStudent(Student student) {
        studentList.add(student);
        return student;
    }

    public void deleteStudent(int studentId) {
        studentList.removeIf(student -> student.getStudentId()== studentId);
    }

    public Student updateStudent(int studentId, Student student) {
        Optional<Student> studentIndex = studentList.stream().filter(item -> item.getStudentId() == studentId).findFirst();
        int x = studentList.indexOf(studentIndex);
        studentList.set(x, student);
        return student;
    }
}
