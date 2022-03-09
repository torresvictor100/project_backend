package com.allocation.backend.projeto.backend.projeto.service;

import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.allocation.backend.projeto.entity.Course;
import com.allocation.backend.projeto.service.CourseService;



@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTest {
	
	@Autowired
	CourseService courseService;
	
	@Test
	public void findAll() {

		List<Course> course = courseService.findAll(null);
		course.forEach(System.out::println);
	}
	@Test
	public void findByName() {
	String name = "test";
	Course course = courseService.findByName(name);
	System.out.println(course);
	}
	
	@Test
	public void findBySigla() {
	String sigla = "teste";
	Course course = courseService.findBySigla(sigla);
	System.out.println(course);
	}
	
	@Test
	public void findById() {

		Long id = 1L;
		Course course = courseService.findById(id);
		System.out.println(course);
	}
	
	@Test
	public void save() throws ParseException {

		Course course = new Course();
		course.setId(null);
		course.setName("test");
		course.setSigla("teste");
		

		course = courseService.save(course);
		System.out.println(course);
	}
	
	@Test
	public void update() throws ParseException {

		Course course = new Course();
		course.setId(1l);
		course.setName("test1");
		course.setSigla("teste2");
		

		course = courseService.save(course);
		System.out.println(course);
	}
	
	@Test
	public void deleteById() {
		Long id = 1L;

		courseService.deleteById(id);
	}
	
	@Test
	public void deleteAll() {
		courseService.deleteAll();
	}
	
	

}
