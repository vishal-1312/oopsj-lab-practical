package com.example.tasktracker.service;

import com.example.tasktracker.model.Task;
import com.example.tasktracker.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Task> getIncompleteTasks() {
        return repository.findByCompletedFalse();
    }

    public Task createTask(Task task) {
        return repository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        Task existing = repository.findById(id).orElse(null);
        if (existing != null) {
            existing.setTitle(task.getTitle());
            existing.setDueDate(task.getDueDate());
            existing.setCompleted(task.isCompleted());
            return repository.save(existing);
        }
        return null;
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
