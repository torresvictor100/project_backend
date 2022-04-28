package com.allocation.backend.projeto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allocation.backend.projeto.entity.Professor;



@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    List<Professor> findByNameContainingIgnoreCase(String name);

    List<Professor> findByDepartmentId(Long departmentId);
    
    Optional<Professor> findByCpf(String cpf);
    
    Optional<Professor> findByName(String name);
}