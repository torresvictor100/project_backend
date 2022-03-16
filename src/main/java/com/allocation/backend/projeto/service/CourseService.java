package com.allocation.backend.projeto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.allocation.backend.projeto.entity.Allocation;
import com.allocation.backend.projeto.entity.Course;
import com.allocation.backend.projeto.repository.AllocationRepository;
import com.allocation.backend.projeto.repository.CourseRepository;

@Service
public class CourseService {

	private final CourseRepository courseRepository;
	private final AllocationRepository allocationRepository;

	public CourseService(CourseRepository courseRepository, AllocationRepository allocationRepository) {
		super();
		this.courseRepository = courseRepository;
		this.allocationRepository = allocationRepository;
	}

	public List<Course> findAll(String name) {
		if (name == null) {
			return courseRepository.findAll();
		} else {
			return courseRepository.findByNameContainingIgnoreCase(name);
		}
	}

	public Course findById(Long id) {
		return courseRepository.findById(id).orElse(null);
	}

	public Course findBySigla(String sigla) {
		return courseRepository.findBySigla(sigla).orElse(null);
	}

	public Course findByName(String name) {
		return courseRepository.findByName(name).orElse(null);
	}
	// public boolean findByName(String name) {
	// List<Course> courses = courseRepository.findAll();
	// boolean result = false;
	// for (Course specificName : courses) {

	// if (specificName.getName().equalsIgnoreCase(name)) {
	// return result = true;

	// }

	// }
	// return result;

	// }

	// public boolean findBySigla(String sigla) {
	// List<Course> courses = courseRepository.findAll();
	// boolean result = false;
	// for (Course specificSigla : courses) {

	// if (specificSigla.getSigla().equalsIgnoreCase(sigla)) {
	// return result = true;

	// }
	// }
	// return result;
	// }

	public Course save(Course course) {
		course.setId(null);
		course.setName(course.getName().replaceAll("[^a-z1-9 ]", ""));
		course.setName(course.getName().replaceAll("[^a-z1-9 ]", ""));
		return saveInternal(course);
	}

	public Course update(Course course) {
		Long id = course.getId();
		if (id != null && courseRepository.existsById(id)) {
			return saveInternal(course);
		} else {
			return null;
		}
	}

	public void deleteById(Long id) {
		if (courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		courseRepository.deleteAllInBatch();
	}
	
	/*public Boolean comparandoNome(Course course) {
		Optional<Course> nome = courseRepository.findByName(course.getName());
		Boolean valoe = true;
		if(nome != null) {
			valoe = true; 
		}else {
			valoe = false; 
		}
		return valoe;
	}
	
	public Boolean comparandoSigla(Course course) {
		Optional<Course> nome = courseRepository.findBySigla(course.getSigla());
		Boolean valoe = true;
		if(nome != null) {
			valoe = true; 
		}else {
			valoe = false; 
		}
		return valoe;
	}

	public Course validationDepartment(Course course) {
		if(course.getName().length() < 2 && comparandoNome(course) && comparandoSigla(course)) {
			throw new IllegalArgumentException("os dados tem algum erro");
		}
		
		return saveInternal(course);
	}*/

	private Course saveInternal(Course course) {
		course = courseRepository.save(course);

		List<Allocation> allocations = allocationRepository.findByCourseId(course.getId());
		course.setAllocation(allocations);

		return course;
	}

}
