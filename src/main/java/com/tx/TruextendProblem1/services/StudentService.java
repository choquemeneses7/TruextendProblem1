package com.tx.TruextendProblem1.services;

import com.tx.TruextendProblem1.entities.Course;
import com.tx.TruextendProblem1.entities.Student;
import com.tx.TruextendProblem1.exceptions.DuplicatedKeyException;
import com.tx.TruextendProblem1.exceptions.ResourceNotFoundException;
import com.tx.TruextendProblem1.mock.MockData;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    CourseService courseService = new CourseService();

    public List<Student> getStudents() {
        return MockData.getStudentList();
    }

    public Optional<Student> getStudentById(int studentId) {
        Optional <Student> studentFound = MockData.getStudentList().stream().filter(student -> student.getStudentId() == studentId).findFirst();
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
        if(MockData.getStudentList().stream().anyMatch(std-> std.getStudentId()==student.getStudentId()))
            throw new DuplicatedKeyException("student Id: "+student.getStudentId());
        MockData.getStudentList().add(student);
        return student;
    }

    public Boolean addStudentToCourse(int studentId, int courseCode) throws DuplicatedKeyException  {
        Optional<Student> studentFound = getStudentById(studentId);
        Optional<Course> courseFound = courseService.getCourseByCode(courseCode);
        if (studentFound.isPresent() && courseFound.isPresent()){
            Course courseWithoutRecursion = new Course(courseFound.get().getCode(), courseFound.get().getTitle(), courseFound.get().getDescription());
            courseService.getCourseByCode(courseCode).ifPresent(course -> course.getStudents().add(studentFound.get()));
            getStudentById(studentId).ifPresent(student -> student.getCourses().add(courseWithoutRecursion));
        } else {
            throw new ResourceNotFoundException("Student Id: "+studentId+ "or Course Code:"+courseCode+ " not exist");
        }
        return true;
    }

    public void deleteStudent(int studentId) throws ResourceNotFoundException {
        if(!MockData.getStudentList().removeIf(student -> student.getStudentId()== studentId)){
            throw new ResourceNotFoundException("Student Id: "+studentId+ " not exists");
        }
    }

    public Student updateStudent(int studentId, Student student) throws ResourceNotFoundException {
        Optional<Student> studentFound = getStudentById(studentId);
        if (studentFound.isPresent()){
            studentFound.map(itemStudent -> MockData.getStudentList().set(MockData.getStudentList().indexOf(itemStudent), student));
            return student;
        } else
            throw new ResourceNotFoundException("Student Id: "+studentId+ " not exists");
    }
}
