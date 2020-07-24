package com.tx.TruextendProblem1.services;

import com.tx.TruextendProblem1.entities.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private List<Student> studentList= new ArrayList<Student>(Arrays.asList(
            new Student(36051,"Veimar","Choque"),
            new Student(36052,"Leo","Daza"),
            new Student(36053,"Ruben","Dario"),
            new Student(36054,"Mario","Grunt"),
            new Student(36055,"Rodrigo","Rodriguez")));

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
        Optional<Student> studentFind = studentList.stream().filter(itemStudent -> studentId == itemStudent.getStudentId()).findFirst();
        if (studentFind.isPresent()){
            studentFind.map(itemStudent -> studentList.set(studentList.indexOf(itemStudent), student));
            return student;
        }
        return null;
    }
}
