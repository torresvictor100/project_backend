package com.allocation.backend.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allocation.backend.projeto.entity.Department;



@Repository
public interface DepartamentRepository extends JpaRepository<Department, Long>{

	List<Department> findByNameContainingIgnoreCase(String name);
	
	List<Department> findByName(String name);
}
