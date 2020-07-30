package com.tx.TruextendProblem1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tx.TruextendProblem1.controllers.StudentController;
import com.tx.TruextendProblem1.entities.Student;
import com.tx.TruextendProblem1.services.StudentService;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
@Import({StudentService.class, Student.class})
public class StudentControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void getAllStudentsSuccessfully() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/students")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].studentId", Is.is(36051)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].studentId", Is.is(36052)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].studentId", Is.is(36053)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].studentId", Is.is(36054)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[4].studentId", Is.is(36055)));

    }

    @Test
    public void addStudentSuccessfully()throws Exception {
        Student newStudent = new Student(36056,"John","Wow blast");
        mvc.perform(MockMvcRequestBuilders.post("/api/students")
                .content(mapper.writeValueAsString(newStudent))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentId", Is.is(36056)));
    }

    @Test
    public void addStudentWithDuplicatedCode()throws Exception {
        Student newStudent = new Student(36055,"John","Wow blast");

        mvc.perform(MockMvcRequestBuilders.post("/api/students")
                .content(mapper.writeValueAsString(newStudent))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void putStudentSuccessfully() throws Exception {
        Student newStudent = new Student(36055,"John","Wow blast");
        mvc.perform(MockMvcRequestBuilders.put("/api/students/36055")
                .content(mapper.writeValueAsString(newStudent))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentId", Is.is(36055)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Is.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Is.is("Wow blast")));

    }

    @Test
    public void putStudentWithInvalidCode() throws Exception {
        Student newStudent = new Student(666,"John","Wow blast");
        mvc.perform(MockMvcRequestBuilders.put("/api/students/666")
                .content(mapper.writeValueAsString(newStudent))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404));

    }

    @Test
    public void deleteStudentSuccessfully() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/students/36051")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deleteStudentWithInvalidCode() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/students/666")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404));

    }

    @Test
    public void getClassesOfStudentSuccessfully() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/students/36052/courses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getClassesOfInvalidStudent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/students/666/courses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    public void getStudentByCodeSuccessfully() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/students/36051")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentId", Is.is(36051)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Is.is("Veimar")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Is.is("Choque")));
    }

    @Test
    public void getStudentByCodeInvalid() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/students/666")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}
