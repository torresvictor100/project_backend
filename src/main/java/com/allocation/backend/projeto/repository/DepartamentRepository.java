package com.allocation.backend.projeto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allocation.backend.projeto.entity.Department;



@Repository
public interface DepartamentRepository extends JpaRepository<Department, Long>{

	List<Department> findByNameContainingIgnoreCase(String name);
	
	Optional<Department> findByName(String name);
	
	Optional<Department> findBySigla(String sigla);
}
