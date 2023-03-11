package prime.holding.internship.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prime.holding.internship.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Employee findOneById(Long id);
	Page<Employee> findByMonthlySalaryBetween(Double monthlySalaryFrom, Double monthlySalaryTo, Pageable pageable);
}
