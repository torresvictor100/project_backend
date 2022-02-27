package com.allocation.backend.projeto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.allocation.backend.projeto.entity.Allocation;
import com.allocation.backend.projeto.entity.Course;
import com.allocation.backend.projeto.entity.Professor;
import com.allocation.backend.projeto.repository.AllocationRepository;

@Service
public class AllocationService {
	
	private final AllocationRepository allocationRepository;
	private final ProfessorService professorService;
	private final CourseService courseService;

	public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService,
			CourseService courseService) {
		this.allocationRepository = allocationRepository;
		this.professorService = professorService;
		this.courseService = courseService;
	}
	
	public List<Allocation> findAll() {
		return allocationRepository.findAll();
	}
	
	public Allocation findById(Long id) {
		return allocationRepository.findById(id).orElse(null);
	}
	
	public List<Allocation> findByProfessor(Long professorId) {
		return allocationRepository.findByProfessorId(professorId);
	}
	
	public List<Allocation> findByCourse(Long courseId) {
		return allocationRepository.findByCourseId(courseId);
	}
	
	public Allocation save(Allocation allocation) {
		allocation.setId(null);
		return saveInternal(allocation);
	}

	
	public Allocation update(Allocation allocation) {
		Long id = allocation.getId();
		if (id != null && allocationRepository.existsById(id)) {
			return saveInternal(allocation);
		} else {
			return null;
		}
	}

	public void deleteById(Long id) {
		if (allocationRepository.existsById(id)) {
			allocationRepository.deleteById(id);
		}
	}
	
	public void deleteAll() {
		allocationRepository.deleteAllInBatch();
	}
	
	private Allocation saveInternal(Allocation allocation) {
	    if (!isEndHourGreaterThanStartHour(allocation) || hasCollision(allocation)) {
	        throw new RuntimeException();
	    } else {
	        allocation = allocationRepository.save(allocation);

	        Professor professor = professorService.findById(allocation.getProfessorId());
	        allocation.setProfessor(professor);

	        Course course = courseService.findById(allocation.getCourseId());
	        allocation.setCourse(course);

	        return allocation;
	    }
	}
	//aqui no caso é a regra de negocio que fala justamente que o inicio n pode ser depois do final
	boolean isEndHourGreaterThanStartHour(Allocation allocation) {
	    return allocation != null && allocation.getStart() != null && allocation.getEnd() != null
	            && allocation.getEnd().compareTo(allocation.getStart()) > 0;
	}

	boolean hasCollision(Allocation newAllocation) {
		boolean hasCollision = false;

		List<Allocation> currentAllocations = allocationRepository.findByProfessorId(newAllocation.getProfessorId());

		for (Allocation currentAllocation : currentAllocations) {
			hasCollision = hasCollision(currentAllocation, newAllocation);
			if (hasCollision) {
				break;
			}
		}

		return hasCollision;
	}
	//olhando a tabela verdade da para entende caso tudo for verdadeiro vira falso ou seja tem colisão
	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
		return !currentAllocation.getId().equals(newAllocation.getId())//para ver se não são iguais
				&& currentAllocation.getDay() == newAllocation.getDay()//para ver se é no mesmo dia
				&& currentAllocation.getStart().compareTo(newAllocation.getEnd()) < 0
				&& newAllocation.getStart().compareTo(currentAllocation.getEnd()) < 0;
	}

}
