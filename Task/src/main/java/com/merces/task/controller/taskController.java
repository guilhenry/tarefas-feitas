package com.merces.task.controller;

import com.merces.task.domain.Task;
import com.merces.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Api/task")
public class taskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> creatTask(@RequestBody Task task){
        Task taskAdd = taskService.creatTask(task);
        return new ResponseEntity<>(taskAdd, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Task>> AllTask(){
        List<Task> tasks = taskService.getAllTask();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id, boolean feita){
       Task task = taskService.completedTask(id, feita);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletTask(@PathVariable Long id){
        if (!taskService.getTaskId(id).isPresent()){
            return ResponseEntity.notFound().build();
        } taskService.deletTask(id);
        return ResponseEntity.noContent().build();
    }
}
