package com.example.todolistproject.model.entity;

import com.example.todolistproject.model.enums.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="task_name", nullable = false)
    String taskName;

    @Column(name="description", nullable = false)
    String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    Status status;

    @CreatedDate
    LocalDateTime created;
    @LastModifiedDate
    LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
