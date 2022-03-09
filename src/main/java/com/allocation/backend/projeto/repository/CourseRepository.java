package com.allocation.backend.projeto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allocation.backend.projeto.entity.Course;



@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

	List<Course> findByNameContainingIgnoreCase(String name);
	
	Optional<Course> findByName(String name);
	
	Optional<Course> findBySigla(String sigla);

}
	 
