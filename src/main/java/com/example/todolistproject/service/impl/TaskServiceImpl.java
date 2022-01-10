package com.example.todolistproject.service.impl;

import com.example.todolistproject.dao.TaskRepo;
import com.example.todolistproject.exception.ResourceNotFoundException;
import com.example.todolistproject.mapper.TaskMapper;
import com.example.todolistproject.model.dto.StatusHistoryDto;
import com.example.todolistproject.model.dto.TaskDto;
import com.example.todolistproject.model.dto.UserDto;
import com.example.todolistproject.model.entity.Task;
import com.example.todolistproject.model.entity.User;
import com.example.todolistproject.model.enums.Status;
import com.example.todolistproject.service.StatusHistoryService;
import com.example.todolistproject.service.TaskService;
import com.example.todolistproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private StatusHistoryService statusHistoryService;

    @Override
    public TaskDto saveTask(TaskDto taskDto) {

        User user = userService.findByUsername(taskDto.getUserName());
        Task task = TaskMapper.INSTANCE.mapToTask(taskDto);
        task.setUser(user);
        task = taskRepo.saveAndFlush(task);
        StatusHistoryDto statusHistoryDto = new StatusHistoryDto();
        statusHistoryDto.setTask(new TaskDto(task.getId()));
        statusHistoryDto.setStatus(Status.NEW);
        statusHistoryService.saveStatusHistory(statusHistoryDto);

        log.info("IN TaskServiceImpl saveTask - task {} successfully saved", task);

        return TaskMapper.INSTANCE.mapToTaskDto(task);
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto) throws ResourceNotFoundException{

        Task task= TaskMapper.INSTANCE.mapToTask(taskDto);

        taskRepo.findById(taskDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No task record exists for given id : "));
        task = taskRepo.save(task);

        log.info("IN TaskServiceImpl updateTask - task {} successfully updated", taskDto);

        return TaskMapper.INSTANCE.mapToTaskDto(task);

    }

    @Override
    public List<TaskDto> getTasksByUser(String user) {

        log.info("IN TaskServiceImpl getTasksByUser - tasks {} successfully got", user);

        return TaskMapper.INSTANCE.mapToTaskDtoList(taskRepo.findAllByUser_Name(user));
    }

    @Override
    public TaskDto getTaskById(long id) {

        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Task with such id \"%s\" not found" + id));

        log.info("IN TaskServiceImpl getTaskById - task {} successfully got", id);

        return TaskMapper.INSTANCE.mapToTaskDto(task);
    }

    @Override
    public void deleteTask(long id) {

        Optional<Task> task = taskRepo.findById(id);
        task.ifPresent(value -> taskRepo.delete(value));

        log.info("IN TaskServiceImpl deleteTask - task {} successfully deleted", id);

    }

    @Override
    public ResponseEntity<?> changeTaskStatus(Long taskId, Status status) {
        Task task = taskRepo.findById(taskId).orElseThrow(()->new ResourceNotFoundException
                ("Task with such id \"%s\" not found" + taskId));
        task.setStatus(status);
        StatusHistoryDto statusHistoryDto = statusHistoryService.getLastStatus(taskId);
        statusHistoryDto.setEndDate(LocalDateTime.now());
        statusHistoryService.saveStatusHistory(statusHistoryDto);
        StatusHistoryDto actualStatusHistory = new StatusHistoryDto();
        actualStatusHistory.setTask(new TaskDto(taskId));
        actualStatusHistory.setStatus(status);
        statusHistoryService.saveStatusHistory(actualStatusHistory);

        log.info("IN TaskServiceImpl changeTaskStatus - task status {} {} successfully changed", taskId, status);

        return ResponseEntity.ok(TaskMapper.INSTANCE.mapToTaskDto(task));
    }
}


