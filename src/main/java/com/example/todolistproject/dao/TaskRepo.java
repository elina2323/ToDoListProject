package com.example.todolistproject.dao;

import com.example.todolistproject.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    List<Task> findAllByUser_Name(String user_name);

}
