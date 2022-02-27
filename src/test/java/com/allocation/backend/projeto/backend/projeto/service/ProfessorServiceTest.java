package com.allocation.backend.projeto.backend.projeto.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.allocation.backend.projeto.entity.Professor;
import com.allocation.backend.projeto.service.ProfessorService;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorServiceTest {

	@Autowired
	ProfessorService professorService;

	@Test
	public void findAll() {

		List<Professor> professor = professorService.findAll(null);
		professor.forEach(System.out::println);
	}

	@Test
	public void findById() {

		Long id = 1L;
		Professor professor = professorService.findById(id);
		System.out.println(professor);
	}

	@Test
	public void findByDepartmentId() {

		Long id = 1L;
		List<Professor> professor = professorService.findByDepartmentId(id);
		professor.forEach(System.out::println);
	}

	@Test
	public void save() {

		Professor professor = new Professor();
		professor.setId(null);
		professor.setName("Professor 10");
		professor.setCpf("11111113111");
		professor.setDepartmentId(1L);

		professor = professorService.save(professor);
		System.out.println(professor);
	}

	@Test
	public void update() {

		Professor professor = new Professor();
		professor.setId(1L);
		professor.setName("Professor 10");
		professor.setCpf("111.111.141-11");
		professor.setDepartmentId(1L);

		professor = professorService.save(professor);
		System.out.println(professor);
	}

	@Test
	public void deleteById() {
		Long id = 1L;
		professorService.deleteById(id);
	}

	@Test
	public void deleteAll() {
		professorService.deleteAll();
	}

}
