package com.example.todolistproject.mapper;

import com.example.todolistproject.model.dto.TaskDto;
import com.example.todolistproject.model.entity.Task;
import com.example.todolistproject.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    default Task mapToTask(TaskDto taskDto){
        Task task = new Task();
        task.setStatus(taskDto.getStatus());
        task.setDescription(taskDto.getDescription());
        task.setTaskName(taskDto.getTaskName());
        task.setId(taskDto.getId());
        return task;
    }

    default  TaskDto mapToTaskDto(Task task){
        TaskDto taskDto = new TaskDto();
        taskDto.setDescription(task.getDescription());
        taskDto.setTaskName(task.getTaskName());
        taskDto.setUserName(task.getUser().getUsername());
        taskDto.setStatus(task.getStatus());
        taskDto.setId(task.getId());
        return taskDto;
    }

    default List<TaskDto> mapToTaskDtoList(List<Task> taskList){
        List<TaskDto> taskDtoList = taskList.stream()
                .map(x->mapToTaskDto(x)).collect(Collectors.toList());
        return taskDtoList;
    }
}
