package prime.holding.internship.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import prime.holding.internship.model.Employee;
import prime.holding.internship.web.dto.EmployeeDTO;

@Component
public class EmployeeToEmployeeDto implements Converter<Employee, EmployeeDTO>{

	@Override
	public EmployeeDTO convert(Employee employee) {
		EmployeeDTO employeeDto = new EmployeeDTO();
		employeeDto.setId(employee.getId());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setPhoneNumber(employee.getPhoneNumber());
		employeeDto.setDateOfBirth(employee.getDateOfBirth().toString());
		employeeDto.setMonthlySalary(employee.getMonthlySalary());
		return employeeDto;
	}
	
	public List<EmployeeDTO> convert(List<Employee> employees){
		List<EmployeeDTO> employeesDto = new ArrayList<>();
		for(Employee employee : employees) {
			employeesDto.add(convert(employee));
		}
		return employeesDto;
	}

}
