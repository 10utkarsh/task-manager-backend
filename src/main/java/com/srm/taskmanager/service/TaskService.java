package com.srm.taskmanager.service;

import com.srm.taskmanager.model.Task;
import com.srm.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return repository.findById(id);
    }

    public Task createTask(Task task) {
        return repository.save(task);
    }

    public Task updateTask(Long id, Task details) {
        Task task = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(details.getTitle());
        task.setDescription(details.getDescription());
        task.setPriority(details.getPriority());
        task.setDeadline(details.getDeadline());
        task.setStatus(details.getStatus());
        return repository.save(task);
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    public Task markComplete(Long id) {
        Task task = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(Task.Status.COMPLETED);
        return repository.save(task);
    }

    public TaskStats getStatistics() {
        long total = repository.count();
        long completed = repository.countByStatus(Task.Status.COMPLETED);
        long pending = repository.countByStatus(Task.Status.PENDING);
        return new TaskStats(total, completed, pending);
    }

    public record TaskStats(long total, long completed, long pending) {}
}