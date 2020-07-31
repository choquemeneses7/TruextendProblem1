package com.tx.TruextendProblem1.mock;

import com.tx.TruextendProblem1.entities.Course;
import com.tx.TruextendProblem1.entities.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockData {

    private static List<Course> coursesInitialList = new ArrayList<Course>(Arrays.asList(
            new Course(1, "Math", "Simple Math Class"),
            new Course(2, "Music", "Rock Alternative Class"),
            new Course(3, "Art", "Painting Mosaics Class"),
            new Course(4, "Design", "Photoshop and illustrator class"),
            new Course(5, "Training", "A programation training program")));

    private static List<Student> studentList= new ArrayList<Student>(Arrays.asList(
            new Student(36051,"Veimar","Choque", new ArrayList<>(Arrays.asList(coursesInitialList.get(0),coursesInitialList.get(2)))),
            new Student(36052,"Leo","Daza", new ArrayList<>(Arrays.asList(coursesInitialList.get(1)))),
            new Student(36053,"Ruben","Dario", new ArrayList<>(Arrays.asList(coursesInitialList.get(0),coursesInitialList.get(4)))),
            new Student(36054,"Mario","Grunt", new ArrayList<>(Arrays.asList(coursesInitialList.get(1)))),
            new Student(36055,"Rodrigo","Rodriguez", new ArrayList<>(Arrays.asList(coursesInitialList.get(4))))));

    private static List<Course> coursesList = new ArrayList<Course>(Arrays.asList(
            new Course(1, "Math", "Simple Math Class",new ArrayList<>(Arrays.asList(studentList.get(0),studentList.get(2)))),
            new Course(2, "Music", "Rock Alternative Class",new ArrayList<>(Arrays.asList(studentList.get(1),studentList.get(3)))),
            new Course(3, "Art", "Painting Mosaics Class",new ArrayList<>(Arrays.asList(studentList.get(0)))),
            new Course(4, "Design", "Photoshop and illustrator class",new ArrayList<>(Arrays.asList())),
            new Course(5, "Training", "A programation training program",new ArrayList<>(Arrays.asList(studentList.get(2),studentList.get(4))))));

    public static List<Course> getCoursesList() {
        return coursesList;
    }

    public static List<Student> getStudentList() {
        return studentList;
    }

}
