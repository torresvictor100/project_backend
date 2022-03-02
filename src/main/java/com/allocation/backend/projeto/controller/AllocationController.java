package com.allocation.backend.projeto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.allocation.backend.projeto.entity.Allocation;
import com.allocation.backend.projeto.service.AllocationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {
	
	AllocationService allocationService;

	public AllocationController(AllocationService allocationService) {
		super();
		this.allocationService = allocationService;
	}
	
	@ApiOperation(value = "Find all allocation")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Allocation>> findAll() {
		List<Allocation> allocation = allocationService.findAll();
		return new ResponseEntity<>(allocation, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Find a allocation by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Allocation> findById(@PathVariable(name = "allocation_id") Long id) {
		Allocation allocation = allocationService.findById(id);
		if (allocation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(allocation, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Find allocations by course")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request") })
	@GetMapping(path = "allocation/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Allocation>> findByIdCourse(@PathVariable(name = "course_id") Long id) {
		List<Allocation> allocation = allocationService.findByCourse(id);
		if (allocation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(allocation, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Find allocations by professor")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request") })
	@GetMapping(path = "professor/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Allocation>> findByIdProfessor(@PathVariable(name = "course_id") Long id) {
		List<Allocation> allocation = allocationService.findByProfessor(id);
		if (allocation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(allocation, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Save a allocation")
	@ApiResponses({ @ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Allocation> save(@RequestBody Allocation allocation) {
		try {
			allocation = allocationService.save(allocation);
			return new ResponseEntity<>(allocation, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Update a allocation")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PutMapping(path = "/{allocation_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Allocation> update(@PathVariable(name = "allocation_id") Long id,
			@RequestBody Allocation allocation) {
		allocation.setId(id); 
		try {
			allocation = allocationService.update(allocation);
			if (allocation == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(allocation, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Delete a department")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content") })
	@DeleteMapping(path = "/{allocation_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "allocation_id") Long id) {
		allocationService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Delete all departments")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content") })
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		allocationService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
