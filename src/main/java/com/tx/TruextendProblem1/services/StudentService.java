package com.tx.TruextendProblem1.services;

import com.tx.TruextendProblem1.entities.ClassStudentDetail;
import com.tx.TruextendProblem1.entities.Student;
import com.tx.TruextendProblem1.exceptions.DuplicatedKeyException;
import com.tx.TruextendProblem1.exceptions.ResourceNotFoundException;
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

    public List<Student> getByStudentId(List<ClassStudentDetail> details) {
        List<Student> response = new ArrayList<Student>();
        details.stream().forEach(classStudentDetail -> response.add(studentList.stream()
                .filter(student -> student.getStudentId() == classStudentDetail.getStudentId())
                .findFirst().get()));
        return response;
    }

    public Student createStudent(Student student) throws DuplicatedKeyException  {
        if(studentList.stream().anyMatch(std-> std.getStudentId()==student.getStudentId()))
            throw new DuplicatedKeyException("student Id: "+student.getStudentId());
        studentList.add(student);
        return student;
    }

    public void deleteStudent(int studentId) throws ResourceNotFoundException {
        if(!studentList.removeIf(student -> student.getStudentId()== studentId)){
            throw new ResourceNotFoundException("Student Id: "+studentId+ " not exists");
        }
    }

    public Student updateStudent(int studentId, Student student) throws ResourceNotFoundException {
        Optional<Student> studentFind = studentList.stream().filter(itemStudent -> studentId == itemStudent.getStudentId()).findFirst();
        if (studentFind.isPresent()){
            studentFind.map(itemStudent -> studentList.set(studentList.indexOf(itemStudent), student));
            return student;
        } else
            throw new ResourceNotFoundException("Student Id: "+studentId+ " not exists");
    }
}
