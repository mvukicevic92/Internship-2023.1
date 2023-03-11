package prime.holding.internship.service;

import java.util.List;

import org.springframework.data.domain.Page;

import prime.holding.internship.model.Task;
import prime.holding.internship.web.dto.CompletedTasksDto;

public interface TaskService {
	
	Task findOne(Long id);
	List<Task> findAll();
	Page<Task> findAll(Integer pageNo);
	Task save(Task task);
	Task update(Task task);
	Task delete(Long id);
	List<CompletedTasksDto> getCompletedTasks();
	Task changeCompletedTask(Task taks);
}
