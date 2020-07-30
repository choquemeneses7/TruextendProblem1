package com.tx.TruextendProblem1.services;

import com.tx.TruextendProblem1.entities.Course;
import com.tx.TruextendProblem1.entities.Student;
import com.tx.TruextendProblem1.exceptions.DuplicatedKeyException;
import com.tx.TruextendProblem1.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private List<Student> studentList= new ArrayList<Student>(Arrays.asList(
            new Student(36051,"Veimar","Choque"),
            new Student(36052,"Leo","Daza"),
            new Student(36053,"Ruben","Dario"),
            new Student(36054,"Mario","Grunt"),
            new Student(36055,"Rodrigo","Rodriguez")));

    private List<Course> coursesList = new ArrayList<Course>(Arrays.asList(
            new Course(1, "Math", "Simple Math Class",new ArrayList<>(Arrays.asList(studentList.get(0),studentList.get(2)))),
            new Course(2, "Music", "Rock Alternative Class",new ArrayList<>(Arrays.asList(studentList.get(1),studentList.get(3)))),
            new Course(3, "Art", "Painting Mosaics Class",new ArrayList<>(Arrays.asList(studentList.get(0)))),
            new Course(4, "Design", "Photoshop and illustrator class",new ArrayList<>(Arrays.asList())),
            new Course(5, "Training", "A programation training program",new ArrayList<>(Arrays.asList(studentList.get(2),studentList.get(4))))));

    public List<Course> getCourses() {
        return coursesList;
    }

    public List<Student> getStudentsOfCourse(int courseCode) {
        Optional<Course> courseFound = coursesList.stream().filter(course -> course.getCode() == courseCode).findFirst();
        List<Student> response = new ArrayList<Student>();
        if (courseFound.isPresent()){
            courseFound.map(course -> response.addAll(course.getStudents()));
        }
        return response;
    }

    public Course getCourseByCode(int courseCode) {
        Course courseFound = coursesList.stream().filter(course -> course.getCode() == courseCode).findFirst().get();
        return courseFound;
    }

    public Course createCourse(Course newCourse) throws DuplicatedKeyException {
        if(coursesList.stream().anyMatch(course-> course.getCode()== newCourse.getCode())){
            throw new DuplicatedKeyException("Class: "+ newCourse.getCode());
        }
        coursesList.add(newCourse);
        return newCourse;
    }

    public void deleteCourse(int courseCode) throws ResourceNotFoundException {
        if(!coursesList.removeIf(course -> course.getCode() == courseCode)){
            throw new ResourceNotFoundException("Class: "+courseCode+ " not exists");
        }
    }

    public Course updateCourse(int courseCode, Course newCourse) throws ResourceNotFoundException {
        Optional<Course> courseFind = coursesList.stream().filter(course -> courseCode == course.getCode()).findFirst();
        if (courseFind.isPresent()){
            courseFind.map(course -> coursesList.set(coursesList.indexOf(course), newCourse));
            return newCourse;
        } else {
            throw new ResourceNotFoundException("Class: "+courseCode+ " not exists");
        }
    }
}
