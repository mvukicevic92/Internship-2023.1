package prime.holding.internship.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prime.holding.internship.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	
	Task findOneById(Long id);

}
