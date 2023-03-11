package prime.holding.internship.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


public class EmployeeDTO {
	
	@Positive
	private Long id;
	
	@NotBlank(message = "First name must not be empty")
	private String firstName;
	
	@NotBlank(message = "Last name must not be empty")
	private String lastName;
	
	@NotBlank(message = "Email must not be empty")
	private String email;
	
	@Positive
	private Long phoneNumber;
	
	@NotNull
	private String dateOfBirth;
	
	@Positive
	private Double monthlySalary;


	public EmployeeDTO() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public Double getMonthlySalary() {
		return monthlySalary;
	}


	public void setMonthlySalary(Double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	
	

}
