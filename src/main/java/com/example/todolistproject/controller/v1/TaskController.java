package com.example.todolistproject.controller.v1;

import com.example.todolistproject.model.dto.TaskDto;
import com.example.todolistproject.model.enums.Status;
import com.example.todolistproject.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks/")
@CrossOrigin
public class TaskController {

    private final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("add")
    public ResponseEntity<?> addTask(TaskDto taskDto) {

        log.info("IN TaskController addTask - task {} successfully added", taskDto);

        return new ResponseEntity<>(taskService.saveTask(taskDto), HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<?> updateTask(TaskDto taskDto) {

        log.info("IN TaskController updateTodo{}", taskDto);

        return new ResponseEntity<>(taskService.updateTask(taskDto), HttpStatus.OK);
    }

    @GetMapping("get-tasks")
    public ResponseEntity<List<?>> getAllTasks(String user) {

        log.info("IN TaskController getAllTasks - tasks successfully got");

        return new ResponseEntity<>(taskService.getTasksByUser(user), HttpStatus.FOUND);
    }

    @GetMapping("delete{id}")
    public ResponseEntity<?> deleteTask(@PathVariable long id) {

        taskService.deleteTask(id);

        log.info("IN TaskController deleteTask - task {} successfully deleted", id);

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("change/status")
    public ResponseEntity<?> changeTaskStatus(@RequestParam Long taskId, @RequestParam Status status){

        log.info("IN TaskController changeTaskStatus - task status {} {} successfully changed", taskId, status);

        return taskService.changeTaskStatus(taskId,status);
    }

}



