package prime.holding.internship.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import prime.holding.internship.model.Employee;
import prime.holding.internship.service.EmployeeService;
import prime.holding.internship.web.dto.EmployeeDTO;

@Component
public class EmployeeDtoToEmployee implements Converter<EmployeeDTO, Employee>{
	
	@Autowired
	private EmployeeService employeeService;

	@Override
	public Employee convert(EmployeeDTO employeeDto) {
		Employee employee = new Employee();
		if(employeeDto.getId() == null) {
			employee = new Employee();
		}else {
			employee = employeeService.findOne(employeeDto.getId());
		}
		if(employee != null) {
			employee.setFirstName(employeeDto.getFirstName());
			employee.setLastName(employeeDto.getLastName());
			employee.setEmail(employeeDto.getEmail());
			employee.setPhoneNumber(employeeDto.getPhoneNumber());
			employee.setDateOfBirth(getLocalDate(employeeDto.getDateOfBirth()));
			employee.setMonthlySalary(employeeDto.getMonthlySalary());
		}
		return employee;
	}
	
    private LocalDate getLocalDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

}
