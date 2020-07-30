package com.tx.TruextendProblem1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tx.TruextendProblem1.controllers.CourseController;
import com.tx.TruextendProblem1.entities.Course;
import com.tx.TruextendProblem1.services.CourseService;
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
@WebMvcTest(CourseController.class)
@Import({CourseService.class, Course.class})
public class CourseControllerTests {

	@Autowired
	private MockMvc mvc;
	@Autowired
	ObjectMapper mapper;

	@Test
	public void getAllClassesSuccessfully() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/courses")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].code", Is.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].code", Is.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[2].code", Is.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[3].code", Is.is(4)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[4].code", Is.is(5)));

	}

	@Test
	public void addClassSuccessfully()throws Exception {
		Course newCourse = new Course(6,"Language","Spanish and english subject");

		mvc.perform(MockMvcRequestBuilders.post("/api/courses")
				.content(mapper.writeValueAsString(newCourse))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(6)));
	}

	@Test
	public void addClassWithDuplicatedCode()throws Exception {
		Course newCourse = new Course(4,"English","English expression class");

		mvc.perform(MockMvcRequestBuilders.post("/api/courses")
				.content(mapper.writeValueAsString(newCourse))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putClassSuccessfully() throws Exception {
		Course newCourse = new Course(1,"Language","Spanish and english subject");
		mvc.perform(MockMvcRequestBuilders.put("/api/courses/1")
				.content(mapper.writeValueAsString(newCourse))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.title", Is.is("Language")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description", Is.is("Spanish and english subject")));

	}

	@Test
	public void putClassWithInvalidCode() throws Exception {
		Course newCourse = new Course(666,"Language","Spanish and english subject");
		mvc.perform(MockMvcRequestBuilders.put("/api/courses/666")
				.content(mapper.writeValueAsString(newCourse))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(404));

	}

	@Test
	public void deleteClassSuccessfully() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/api/courses/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void deleteClassWithInvalidCode() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/api/courses/666")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(404));

	}

	@Test
	public void getStudentsOfClassSuccessfully() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/courses/students/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void getStudentsOfInvalidClass() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/courses/students/666")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
	}

	@Test
	public void getStudentByCodeSuccessfully() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/courses/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.title", Is.is("Math")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description", Is.is("Simple Math Class")));
	}

	@Test
	public void getStudentByCodeInvalid() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/courses/666")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(500));
	}

}
