package co.com.poli.pdstodo.controller;

import co.com.poli.pdstodo.persistence.entity.Task;
import co.com.poli.pdstodo.persistence.entity.TaskStatus;
import co.com.poli.pdstodo.services.TaskService;
import co.com.poli.pdstodo.services.dto.TaskInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping("/{id}")
    public void markTaskAsFinished(@PathVariable("id") Long id){
        this.taskService.markTaskAsFinished(id);
    }



}
