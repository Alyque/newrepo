package net.codejava;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	
	
	  @GetMapping("/employee") public List<Employee> list() { return
	  service.listAll(); }
	 
	 
	/*
	 * @GET
	 * 
	 * @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	 * 
	 * @GetMapping("/employee") public List<Employee> list() { return
	 * service.listAll(); }
	 */
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> get(@PathVariable Integer id)
	{
		try
		{
			Employee employee=service.get(id);
			return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		}
		catch(NoSuchElementException e)
		{
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/employee")
	public void add(@RequestBody Employee employee)
	{
		service.save(employee);
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<?> update(@RequestBody Employee employee,@PathVariable Integer id)
	{
		try
		{
		Employee existEmployee=service.get(id);
		service.save(employee);
		return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(NoSuchElementException e) 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
	}
	
	@DeleteMapping("/employee/{id}")
	public void delete(@PathVariable Integer id)
	{
		service.delete(id);
	}
}
