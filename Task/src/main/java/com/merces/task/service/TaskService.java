package com.merces.task.service;

import com.merces.task.domain.Task;
import com.merces.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task creatTask(Task task){
        return taskRepository.save(task);
    }

    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskId(Long id){
        return taskRepository.findById(id);
    }

    public Task completedTask(Long id, boolean feita){
        Optional<Task> taskOption = taskRepository.findById(id);
        if (taskOption.isPresent()){
            Task task = taskOption.get();
            task.setCompleted(feita);
            return taskRepository.save(task);
        }
        return null;
    }
    public void deletTask(Long id){
        taskRepository.deleteById(id);
    }
}
