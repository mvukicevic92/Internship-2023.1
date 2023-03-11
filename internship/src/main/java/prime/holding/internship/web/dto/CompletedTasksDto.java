package prime.holding.internship.web.dto;

public class CompletedTasksDto {
	
	private Long employeeId;
	
	private Integer numOfCompletedTasks;

	public CompletedTasksDto() {
		super();
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getNumOfCompletedTasks() {
		return numOfCompletedTasks;
	}

	public void setNumOfCompletedTasks(Integer numOfCompletedTasks) {
		this.numOfCompletedTasks = numOfCompletedTasks;
	}
	
	

}
