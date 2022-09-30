package co.com.poli.pdstodo.controller;

import co.com.poli.pdstodo.exceptions.TaskException;
import co.com.poli.pdstodo.persistence.entity.Task;
import co.com.poli.pdstodo.persistence.entity.TaskStatus;
import co.com.poli.pdstodo.services.TaskService;
import co.com.poli.pdstodo.services.dto.TaskInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody TaskInDTO taskInDTO){
        return taskService.createTask(taskInDTO);
    }

    @GetMapping("/status/{status}")
    public List<Task> findAllByTaskStatus(@PathVariable("status") TaskStatus taskStatus){
        return taskService.findAllByTaskStatus(taskStatus);
    }

    @GetMapping
    public List<Task> findAll(){
        return taskService.findAll();
    }

    @PatchMapping("/mark as_finished/{id}")
    public ResponseEntity<?>  markTaskAsFinished(@PathVariable("id") Long id){
        Task task = this.taskService.markTaskAsFinished(id);
        if(Objects.isNull(task)){
            throw new TaskException("Task not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable("id") Long id){
        Task task = this.taskService.deleteTaskById(id);
        if(Objects.isNull(task)){
            throw new TaskException("Task not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }

}
