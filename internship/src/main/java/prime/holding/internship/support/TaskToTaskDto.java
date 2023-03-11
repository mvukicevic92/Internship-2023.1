package prime.holding.internship.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import prime.holding.internship.model.Task;
import prime.holding.internship.web.dto.TaskDTO;

@Component
public class TaskToTaskDto implements Converter<Task, TaskDTO>{
	
	@Autowired
	private EmployeeToEmployeeDto toEmployeeDto;

	@Override
	public TaskDTO convert(Task task) {
		TaskDTO taskDto = new TaskDTO();
		taskDto.setId(task.getId());
		taskDto.setTitle(task.getTitle());
		taskDto.setDescription(task.getDescription());
		taskDto.setAssignee(toEmployeeDto.convert(task.getAssignee()));
		taskDto.setDueDate(task.getDueDate().toString());
		taskDto.setIsCompleted(task.getIsCompleted());
		return taskDto;
	}
	
	public List<TaskDTO> convert(List<Task> tasks){
		List<TaskDTO> tasksDto = new ArrayList<>();
		for(Task task : tasks) {
			tasksDto.add(convert(task));
		}
		return tasksDto;
	}

}
