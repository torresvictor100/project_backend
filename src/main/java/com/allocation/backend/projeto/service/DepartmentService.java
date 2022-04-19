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

	public Department findByName(String name) {
		return departmentRepository.findByName(name).orElse(null);
		
	}

	public Department findBySigla(String sigla) {
		return departmentRepository.findBySigla(sigla).orElse(null);
		
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
	
	/*public Boolean comparandoNome(Department department) {
		Optional<Department> nome = departmentRepository.findByName(department.getName());
		Boolean valoe = true;
		if(nome != null) {
			valoe = true; 
		}else {
			valoe = false; 
		}
		return valoe;
	}
	
	public Boolean comparandoSigla(Department department) {
		Optional<Department> nome = departmentRepository.findBySigla(department.getSigla());
		Boolean valoe = true;
		if(nome != null) {
			valoe = true; 
		}else {
			valoe = false; 
		}
		return valoe;
	}

	public Department validationDepartment(Department department) {
			if(department.getName().length() < 2 && comparandoNome(department) && comparandoSigla(department)) {
				throw new IllegalArgumentException("os dados tem algum erro");
			}
			
			return saveInternal(department);
	}*/
	
	private Department saveInternal(Department department) {
        department = departmentRepository.save(department);

        List<Professor> professors = professorRepository.findByDepartmentId(department.getId());
        department.setProfessors(professors);

        return department;
    }

}
