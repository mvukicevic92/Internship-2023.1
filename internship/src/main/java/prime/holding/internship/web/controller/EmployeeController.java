package prime.holding.internship.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import prime.holding.internship.model.Employee;
import prime.holding.internship.service.EmployeeService;
import prime.holding.internship.support.EmployeeDtoToEmployee;
import prime.holding.internship.support.EmployeeToEmployeeDto;
import prime.holding.internship.web.dto.EmployeeDTO;

@RestController
@RequestMapping(value = "/api/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeToEmployeeDto toEmployeeDto;
	
	@Autowired
	private EmployeeDtoToEmployee toEmployee;
	
	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getAll(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo){
		Page<Employee> page;
		page = employeeService.findAll(pageNo);
		
		
		return new ResponseEntity<>(toEmployeeDto.convert(page.getContent()), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EmployeeDTO> getOne(@PathVariable Long id){
		Employee employee = employeeService.findOne(id);
		if(employee != null) {
			return new ResponseEntity<>(toEmployeeDto.convert(employee), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody EmployeeDTO employeeDto){
		Employee employee = toEmployee.convert(employeeDto);
		Employee savedEmployee = employeeService.save(employee);
		
		return new ResponseEntity<>(toEmployeeDto.convert(savedEmployee), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeDTO> update(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDto){
		if(!id.equals(employeeDto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Employee employee = toEmployee.convert(employeeDto);
		Employee savedEmployee = employeeService.update(employee);
		
		return new ResponseEntity<>(toEmployeeDto.convert(savedEmployee), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Employee deletedEmployee = employeeService.delete(id);
		if(deletedEmployee != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/bySalary")
	public ResponseEntity<List<EmployeeDTO>> getAllByMonthSalary(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
													@RequestParam(required = false) Double monthlySalaryFrom,
													@RequestParam(required = false) Double monthlySalaryTo){
		Page<Employee> page;
		
		try {
			page = employeeService.searchMonthlySalary(monthlySalaryFrom, monthlySalaryTo, pageNo);
		} catch (Exception e) {
			page = employeeService.findAll(pageNo);
		}
		
		return new ResponseEntity<>(toEmployeeDto.convert(page.getContent()), HttpStatus.OK);
	}

}
