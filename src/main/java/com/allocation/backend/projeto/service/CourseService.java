package com.allocation.backend.projeto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.allocation.backend.projeto.entity.Allocation;
import com.allocation.backend.projeto.entity.Course;
import com.allocation.backend.projeto.entity.Department;
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
	//public boolean findByName(String name) {
		//List<Course> courses = courseRepository.findAll();
		//boolean result = false;
		//for (Course specificName : courses) {

			//if (specificName.getName().equalsIgnoreCase(name)) {
				//return result = true;
			
			//}

		//}
		//return result;

	//}

	//public boolean findBySigla(String sigla) {
		//List<Course> courses = courseRepository.findAll();
		//boolean result = false;
		//for (Course specificSigla : courses) {

			//if (specificSigla.getSigla().equalsIgnoreCase(sigla)) {
				//return result = true;
		
			//}
		//}
		//return result;
	//}

	
	
	
	public Course save(Course course) {
		course.setId(null);
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

	public void test() {
		// ....//
	}

	private Course saveInternal(Course course) {
		course = courseRepository.save(course);

		List<Allocation> allocations = allocationRepository.findByCourseId(course.getId());
		course.setAllocation(allocations);

		return course;
	}

}
