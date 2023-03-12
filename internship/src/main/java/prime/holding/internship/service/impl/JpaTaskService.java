package prime.holding.internship.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import prime.holding.internship.comparator.ValueComparator;
import prime.holding.internship.model.Task;
import prime.holding.internship.repository.TaskRepository;
import prime.holding.internship.service.TaskService;
import prime.holding.internship.web.dto.CompletedTasksDto;

@Service
public class JpaTaskService implements TaskService{
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task findOne(Long id) {
		return taskRepository.findOneById(id);
	}

	@Override
	public List<Task> findAll() {
		return taskRepository.findAll();
	}
	
	@Override
	public Page<Task> findAll(Integer pageNo) {
		return taskRepository.findAll(PageRequest.of(pageNo, 5));
	}

	@Override
	public Task save(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Task update(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Task delete(Long id) {
		Task task = taskRepository.findOneById(id);
		if(task != null) {
			task.getAssignee().getTasks().remove(task);
			task = taskRepository.save(task);
			taskRepository.delete(task);
			return task;
		}
		return null;
	}

	@Override
	public List<CompletedTasksDto> getCompletedTasks() {
		List<Task> tasks = getAllCompletedTasks();
		
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		for (Task task : tasks) {
		    Long key = task.getAssignee().getId();
		    if(map.containsKey(key)){
		    	Integer numberOfTasks = map.get(key);
		    	numberOfTasks++;
		    	 map.put(key, numberOfTasks);
		    }else{
		        map.put(key, 1);
		    }
		}
		
		ValueComparator bvc = new ValueComparator(map);
        TreeMap<Long, Integer> sorted_map = new TreeMap<Long, Integer>(bvc);
		sorted_map.putAll(map);
		
		List<CompletedTasksDto> cplTasks = new ArrayList<>();
		for (Map.Entry<Long, Integer> entry : sorted_map.entrySet()) {
			CompletedTasksDto task = new CompletedTasksDto();
			task.setEmployeeId(entry.getKey());
			task.setNumOfCompletedTasks(entry.getValue());
			cplTasks.add(task);
		}
		if(cplTasks.size() < 6) {
			return cplTasks;
		}
		return cplTasks.subList(0, 5);
	}

	private List<Task> getAllCompletedTasks() {
		List<Task> tasks = taskRepository.findAll();
		List<Task> filtredList = new ArrayList<>();
		for(Task task : tasks) {
			if(task.getIsCompleted() == true && task.getDueDate().isAfter(LocalDate.now().minusMonths(1))) {
				filtredList.add(task);
			}
		}
		return filtredList;
	}

	@Override
	public Task changeCompletedTask(Task task) {
		if(task.getIsCompleted() == false) {
			task.setIsCompleted(true);
		}
		taskRepository.save(task);
		return task;
	}

}


