package com.allocation.backend.projeto.backend.projeto.service;

import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.allocation.backend.projeto.entity.Area;
import com.allocation.backend.projeto.entity.Department;
import com.allocation.backend.projeto.service.DepartmentService;


@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceTest {
	
	@Autowired
	DepartmentService departmentService;
	
	@Test
	public void findAll() {

		List<Department> department = departmentService.findAll(null);
		department.forEach(System.out::println);
	}
	
	@Test
	public void findById() {

		Long id = 1L;
		Department departament = departmentService.findById(id);
		System.out.println(departament);
	}
	
	@Test
	public void findByName() {

		String name = "test";
		Department departament = departmentService.findByName(name);
		System.out.println(departament);
	}
	
	@Test
	public void findBySigla() {

		String sigla = "test";
		Department departament = departmentService.findBySigla(sigla);
		System.out.println(departament);
	}
	
	@Test
	public void save() throws ParseException {

		Department departament = new Department();
		departament.setId(null);
		departament.setName("test");
		departament.setSigla("test");
		departament.setArea(Area.EXATAS);
		
		departament = departmentService.save(departament);
		System.out.println(departament);
	}
	
	@Test
	public void update() throws ParseException {

		Department departament = new Department();
		departament.setId(1l);
		departament.setName("test");
		departament.setSigla("test");
		departament.setArea(Area.EXATAS);
		
		
		departament = departmentService.save(departament);
		System.out.println(departament);
	}
	
	@Test
	public void deleteById() {
		Long id = 1L;
		departmentService.deleteById(id);
	}
	
	@Test
	public void deleteAll() {
		departmentService.deleteAll();
	}
	
	

}
