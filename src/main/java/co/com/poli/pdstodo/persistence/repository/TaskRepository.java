package co.com.poli.pdstodo.persistence.repository;

import co.com.poli.pdstodo.persistence.entity.Task;
import co.com.poli.pdstodo.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findAllByTaskStatus(TaskStatus taskStatus);

}
