package com.allocation.backend.projeto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.allocation.backend.projeto.entity.Allocation;
import com.allocation.backend.projeto.entity.Department;
import com.allocation.backend.projeto.entity.Professor;
import com.allocation.backend.projeto.repository.AllocationRepository;
import com.allocation.backend.projeto.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	private final DepartmentService departmentService;
	private final AllocationRepository allocationRepository;

	public ProfessorService(ProfessorRepository professorRepository, DepartmentService departmentService,
			AllocationRepository allocationRepository) {
		super();
		this.professorRepository = professorRepository;
		this.departmentService = departmentService;
		this.allocationRepository = allocationRepository;

	}

	public List<Professor> findAll(String name) {
		if (name == null) {
			return professorRepository.findAll();
		} else {
			return professorRepository.findByNameContainingIgnoreCase(name);
		}
	}

	public List<Professor> findByDepartmentId(Long departmentId) {
		return professorRepository.findByDepartmentId(departmentId);
	}

	public Professor findById(Long id) {
		return professorRepository.findById(id).orElse(null);
	}

	public Professor findByName(String name) {
		return professorRepository.findByName(name).orElse(null);

	}

	public Professor findByCpf(String cpf) {
		return professorRepository.findByCpf(cpf).orElse(null);

	}

	public Professor save(Professor professor) {
		professor.setId(null);
		professor.setCpf(professor.getCpf());
		professor.setName(professor.getName());
		return saveInternal(professor);
	}

	public Professor update(Professor professor) {
		Long id = professor.getId();
		if (id != null && professorRepository.existsById(id)) {
			return saveInternal(professor);
		} else {
			return null;
		}
	}

	private Professor saveInternal(Professor professor) {
		professor = professorRepository.save(professor);

		Department department = departmentService.findById(professor.getDepartmentId());
		professor.setDepartment(department);

		List<Allocation> allocations = allocationRepository.findByProfessorId(professor.getId()); // isso não entendi
		professor.setAllocations(allocations);

		return professor;
	}

	public void deleteById(Long id) {
		if (professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}

	public void teste() {

	}

}
