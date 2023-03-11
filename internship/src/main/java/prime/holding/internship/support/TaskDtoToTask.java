package prime.holding.internship.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import prime.holding.internship.model.Task;
import prime.holding.internship.service.EmployeeService;
import prime.holding.internship.service.TaskService;
import prime.holding.internship.web.dto.TaskDTO;

@Component
public class TaskDtoToTask implements Converter<TaskDTO, Task>{
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private EmployeeService employeeService;

	@Override
	public Task convert(TaskDTO taskDto) {
		Task task = new Task();
		if(taskDto.getId() == null) {
			task = new Task();
		}else {
			task = taskService.findOne(taskDto.getId());
		}
		if(task != null) {
			task.setTitle(taskDto.getTitle());
			task.setDescription(taskDto.getDescription());
			task.setAssignee(employeeService.findOne(taskDto.getAssignee().getId()));
			task.setDueDate(getLocalDate(taskDto.getDueDate()));
			task.setIsCompleted(taskDto.getIsCompleted());
		}
		return task;
	}
	
    private LocalDate getLocalDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

}
