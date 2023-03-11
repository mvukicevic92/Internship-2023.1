package prime.holding.internship.service;

import java.util.List;

import org.springframework.data.domain.Page;

import prime.holding.internship.model.Employee;

public interface EmployeeService {
	
	Employee findOne(Long id);
	List<Employee> findAll();
	Page<Employee> findAll(Integer pageNo);
	Employee save(Employee employee);
	Employee update(Employee employee);
	Employee delete(Long id);
	Page<Employee> searchMonthlySalary(Double monthlySalaryFrom, Double monthlySalaryTo, Integer pageNo);
}
