package prime.holding.internship.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import prime.holding.internship.model.Task;
import prime.holding.internship.service.TaskService;
import prime.holding.internship.support.TaskDtoToTask;
import prime.holding.internship.support.TaskToTaskDto;
import prime.holding.internship.web.dto.CompletedTasksDto;
import prime.holding.internship.web.dto.TaskDTO;

@RestController
@RequestMapping(value = "/api/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskToTaskDto toTaskDto;
	
	@Autowired
	private TaskDtoToTask toTask;
	
	@GetMapping
	public ResponseEntity<List<TaskDTO>> getAll(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo){
		Page<Task> page;
		page = taskService.findAll(pageNo);
		
		return new ResponseEntity<>(toTaskDto.convert(page.getContent()), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TaskDTO> getOne(@PathVariable Long id){
		Task task = taskService.findOne(id);
		if(task != null) {
			return new ResponseEntity<>(toTaskDto.convert(task), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskDTO> create(@Valid @RequestBody TaskDTO taskDto){
		Task task = toTask.convert(taskDto);
		task.setIsCompleted(false);
		Task savedTask = taskService.save(task);
		
		return new ResponseEntity<>(toTaskDto.convert(savedTask), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskDTO> update(@PathVariable Long id, @Valid @RequestBody TaskDTO taskDto){
		if(!id.equals(taskDto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Task task = toTask.convert(taskDto);
		Task savedTask = taskService.update(task);
		
		return new ResponseEntity<>(toTaskDto.convert(savedTask), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Task deletedTask = taskService.delete(id);
		if(deletedTask != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/completedTasks")
	public List<CompletedTasksDto> getCompletedTasks(){
		return taskService.getCompletedTasks();	
	}
	
	@PostMapping(value = "/{id}")
	public ResponseEntity<TaskDTO> changeCompletedTask(@PathVariable Long id){
		Task task = taskService.findOne(id);
		taskService.changeCompletedTask(task);
		return new ResponseEntity<>(toTaskDto.convert(task), HttpStatus.OK);
	}

}
