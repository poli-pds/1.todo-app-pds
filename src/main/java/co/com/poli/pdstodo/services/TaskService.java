package co.com.poli.pdstodo.services;

import co.com.poli.pdstodo.mapper.TaskInDTOToTask;
import co.com.poli.pdstodo.persistence.entity.Task;
import co.com.poli.pdstodo.persistence.entity.TaskStatus;
import co.com.poli.pdstodo.persistence.repository.TaskRepository;
import co.com.poli.pdstodo.services.dto.TaskInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    /* Opcion 1 - inyeccion
    @Autowired
    private TaskRepository taskRepository;
     */
    private final TaskRepository taskRepository;
    private final TaskInDTOToTask taskInDTOToTask;

    public Task createTask(TaskInDTO taskInDTO){
        Task task = taskInDTOToTask.map(taskInDTO);
        return this.taskRepository.save(task);
    }

    public List<Task> findAllByTaskStatus(TaskStatus taskStatus){
        return this.taskRepository.findAllByTaskStatus(taskStatus);
    }

    public List<Task> findAll(){
        return this.taskRepository.findAll();
    }

    @Transactional
    public Task markTaskAsFinished(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        if(task.isEmpty()){
            return null;
        }
        this.taskRepository.markTaskAsFinished(id);
        return task.orElse(null);
    }

    @Transactional
    public Task deleteTaskById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        if(task.isEmpty()){
            return null;
        }
        this.taskRepository.deleteById(id);
        return task.orElse(null);
    }

}
