package com.srm.taskmanager.repository;

import com.srm.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Task.Status status);
    List<Task> findByPriority(Task.Priority priority);
    List<Task> findByTitleContainingIgnoreCase(String title);
    long countByStatus(Task.Status status);
}