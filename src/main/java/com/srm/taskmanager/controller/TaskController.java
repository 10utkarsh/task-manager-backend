package com.srm.taskmanager.controller;

import com.srm.taskmanager.model.Task;
import com.srm.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getAll() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        return service.getTaskById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return service.createTask(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        return service.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/complete")
    public Task markComplete(@PathVariable Long id) {
        return service.markComplete(id);
    }

    @GetMapping("/statistics")
    public TaskService.TaskStats getStats() {
        return service.getStatistics();
    }
}
```

---

## ✅ VERIFY YOUR FILES

Your folder should now look like this:
```
task-manager-backend/
├── pom.xml
├── src/
│   └── main/
│       ├── java/com/srm/taskmanager/
│       │   ├── TaskManagerApplication.java
│       │   ├── model/Task.java
│       │   ├── repository/TaskRepository.java
│       │   ├── service/TaskService.java
│       │   └── controller/TaskController.java
│       └── resources/
│           └── application.properties