package com.tx.TruextendProblem1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tx.TruextendProblem1.controllers.ClassStudentDetailController;
import com.tx.TruextendProblem1.entities.CourseStudentDetail;
import com.tx.TruextendProblem1.services.CourseService;
import com.tx.TruextendProblem1.services.CourseStudentDetailService;
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
@WebMvcTest(ClassStudentDetailController.class)
@Import({CourseStudentDetailService.class, CourseStudentDetail.class, CourseService.class, StudentService.class})
public class CourseStudentDetailControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void getAllClassStudentDetailSuccessfully() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/classStudentDetails")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].studentId", Is.is(36051)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].studentId", Is.is(36052)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].studentId", Is.is(36053)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].studentId", Is.is(36051)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[4].studentId", Is.is(36051)));

    }

    @Test
    public void getStudentsOfClassSuccessfully() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/studentsOfClass/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].studentId", Is.is(36052)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].studentId", Is.is(36051)));

    }

    @Test
    public void getStudentsOfInvalidClassCode() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/studentsOfClass/666")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

    }

    @Test
    public void getClassesOfStudentSuccessfully() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/studentClasses/36051")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Is.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].code", Is.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].code", Is.is(3)));

    }

    @Test
    public void getClassesOfInvalidStudentCode() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/studentClasses/666")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

    }

    @Test
    public void addClassStudentDetailSuccessfully()throws Exception {
        CourseStudentDetail newCourseStudentDetail = new CourseStudentDetail(36052,1);
        mvc.perform(MockMvcRequestBuilders.post("/api/classStudentDetails")
                .content(mapper.writeValueAsString(newCourseStudentDetail))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentId", Is.is(36052)));
    }
}
