package prime.holding.internship.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import prime.holding.internship.model.Employee;
import prime.holding.internship.repository.EmployeeRepository;
import prime.holding.internship.service.EmployeeService;

@Service
public class JpaEmployeeService implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee findOne(Long id) {
		return employeeRepository.findOneById(id);
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	@Override
	public Page<Employee> findAll(Integer pageNo) {
		return employeeRepository.findAll(PageRequest.of(pageNo, 5));
	}

	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee update(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee delete(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()) {
			employeeRepository.deleteById(id);
			return employee.get();
		}
		return null;
	}

	@Override
	public Page<Employee> searchMonthlySalary(Double monthlySalaryFrom, Double monthlySalaryTo, Integer pageNo) {
		if(monthlySalaryFrom == null) {
			monthlySalaryFrom = 0.0;
		}
		if(monthlySalaryTo == null){
			monthlySalaryTo = Double.MAX_VALUE;
		}
		return employeeRepository.findByMonthlySalaryBetween(monthlySalaryFrom, monthlySalaryTo, PageRequest.of(pageNo, 5));
	}
}
