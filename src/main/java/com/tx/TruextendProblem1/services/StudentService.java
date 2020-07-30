package com.tx.TruextendProblem1.services;

import com.tx.TruextendProblem1.entities.Course;
import com.tx.TruextendProblem1.entities.Student;
import com.tx.TruextendProblem1.exceptions.DuplicatedKeyException;
import com.tx.TruextendProblem1.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    public static List<Course> coursesList = new ArrayList<Course>(Arrays.asList(
            new Course(1, "Math", "Simple Math Class"),
            new Course(2, "Music", "Rock Alternative Class"),
            new Course(3, "Art", "Painting Mosaics Class"),
            new Course(4, "Design", "Photoshop and illustrator class"),
            new Course(5, "Training", "A programation training program")));

    public static List<Student> studentList= new ArrayList<Student>(Arrays.asList(
            new Student(36051,"Veimar","Choque", new ArrayList<>(Arrays.asList(coursesList.get(0),coursesList.get(2)))),
            new Student(36052,"Leo","Daza", new ArrayList<>(Arrays.asList(coursesList.get(1)))),
            new Student(36053,"Ruben","Dario", new ArrayList<>(Arrays.asList(coursesList.get(0),coursesList.get(4)))),
            new Student(36054,"Mario","Grunt", new ArrayList<>(Arrays.asList(coursesList.get(1)))),
            new Student(36055,"Rodrigo","Rodriguez", new ArrayList<>(Arrays.asList(coursesList.get(4))))));

    public List<Student> getStudents() {
        return studentList;
    }

    public Optional<Student> getStudentById(int studentId) {
        Optional <Student> studentFound = studentList.stream().filter(student -> student.getStudentId() == studentId).findFirst();
        return studentFound;
    }

    public List<Course> getCoursesOfStudent(int studentId) {
        Optional<Student> studentFound = getStudentById(studentId);
        List<Course> response = new ArrayList<Course>();
        if (studentFound.isPresent()){
            studentFound.map(student -> response.addAll(student.getCourses()));
        }
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
        Optional<Student> studentFound = getStudentById(studentId);
        if (studentFound.isPresent()){
            studentFound.map(itemStudent -> studentList.set(studentList.indexOf(itemStudent), student));
            return student;
        } else
            throw new ResourceNotFoundException("Student Id: "+studentId+ " not exists");
    }
}
