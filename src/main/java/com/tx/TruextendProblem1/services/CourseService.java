package com.tx.TruextendProblem1.services;

import com.tx.TruextendProblem1.entities.Course;
import com.tx.TruextendProblem1.entities.Student;
import com.tx.TruextendProblem1.exceptions.DuplicatedKeyException;
import com.tx.TruextendProblem1.exceptions.ResourceNotFoundException;
<<<<<<< HEAD
=======
import com.tx.TruextendProblem1.mock.MockData;
>>>>>>> [develop] Add mockdata to order in a better way data
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    public List<Course> getCourses() {
        return MockData.getCoursesList();
    }

    public List<Student> getStudentsOfCourse(int courseCode) {
        Optional<Course> courseFound = getCourseByCode(courseCode);
        List<Student> response = new ArrayList<Student>();
        if (courseFound.isPresent()){
            courseFound.map(course -> response.addAll(course.getStudents()));
        }
        return response;
    }

    public Optional<Course> getCourseByCode(int courseCode) {
        Optional<Course> courseFound = MockData.getCoursesList().stream().filter(course -> course.getCode() == courseCode).findFirst();
        return courseFound;
    }

    public Course createCourse(Course newCourse) throws DuplicatedKeyException {
        if(MockData.getCoursesList().stream().anyMatch(course-> course.getCode()== newCourse.getCode())){
            throw new DuplicatedKeyException("Class: "+ newCourse.getCode());
        }
        MockData.getCoursesList().add(newCourse);
        return newCourse;
    }

    public void deleteCourse(int courseCode) throws ResourceNotFoundException {
        if(!MockData.getCoursesList().removeIf(course -> course.getCode() == courseCode)){
            throw new ResourceNotFoundException("Class: "+courseCode+ " not exists");
        }
    }

    public Course updateCourse(int courseCode, Course newCourse) throws ResourceNotFoundException {
        Optional<Course> courseFind = getCourseByCode(courseCode);
        if (courseFind.isPresent()){
            courseFind.map(course -> MockData.getCoursesList().set(MockData.getCoursesList().indexOf(course), newCourse));
            return newCourse;
        } else {
            throw new ResourceNotFoundException("Class: "+courseCode+ " not exists");
        }
    }
}
