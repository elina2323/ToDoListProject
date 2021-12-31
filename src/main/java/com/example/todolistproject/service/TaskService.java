package com.example.todolistproject.service;

import com.example.todolistproject.model.dto.TaskDto;
import com.example.todolistproject.model.entity.Task;
import com.example.todolistproject.model.enums.Status;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {

    TaskDto saveTask(TaskDto taskDto);

    TaskDto updateTask(TaskDto taskDto);

    List<TaskDto> getTasksByUser(String user);

    TaskDto getTaskById(long id);

    void deleteTask(long id);

    ResponseEntity<?> changeTaskStatus(Long taskId, Status status);
}
