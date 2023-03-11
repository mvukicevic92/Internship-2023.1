package prime.holding.internship.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TaskDTO {
	
	@Positive
	private Long id;
	
	@NotBlank(message = "Title must not be empty")
	private String title;
	
	@NotBlank
	private String description;
	
	@NotNull
	private EmployeeDTO assignee;
	
	@NotNull
	private String dueDate;
	
	@NotBlank
	private Boolean isCompleted;

	public TaskDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EmployeeDTO getAssignee() {
		return assignee;
	}

	public void setAssignee(EmployeeDTO assignee) {
		this.assignee = assignee;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	

}
