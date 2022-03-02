package com.allocation.backend.projeto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.allocation.backend.projeto.entity.Department;
import com.allocation.backend.projeto.entity.Professor;
import com.allocation.backend.projeto.repository.DepartamentRepository;
import com.allocation.backend.projeto.repository.ProfessorRepository;

@Service
public class DepartmentService {

	private final DepartamentRepository departmentRepository;
	private final ProfessorRepository professorRepository;
	
	

	public DepartmentService(DepartamentRepository departmentRepository, ProfessorRepository professorRepository) {
		super();
		this.departmentRepository = departmentRepository;
		this.professorRepository = professorRepository;
	}

	public List<Department> findAll(String name) {
		if (name == null) {
			return departmentRepository.findAll();
		} else {
			return departmentRepository.findByNameContainingIgnoreCase(name);
		}
	}

	public Department findById(Long id) {
		return departmentRepository.findById(id).orElse(null);
	}

	public Department findByNome(Long id) {
		// ....//
		return null;
	}

	public Department findBySigla(Long id) {
		// ...//
		return null;
	}

	public Department save(Department departament) {
		departament.setId(null);
		return saveInternal(departament);
	}

	public Department update(Department departament) {
		Long id = departament.getId();
		if (id != null && departmentRepository.existsById(id)) {
			return saveInternal(departament);
		} else {
			return null;
		}
	}

	public void deleteById(Long id) {
		if (departmentRepository.existsById(id)) {
			departmentRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}

	public void test() {
		// ....//
	}
	
	private Department saveInternal(Department department) {
        department = departmentRepository.save(department);

        List<Professor> professors = professorRepository.findByDepartmentId(department.getId());
        department.setProfessors(professors);

        return department;
    }

}