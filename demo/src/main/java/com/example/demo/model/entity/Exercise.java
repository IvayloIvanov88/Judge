package com.example.demo.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "exercises")
public class Exercise extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "started_on")
    private LocalDateTime startedOn;

    @Column(name = "due_date")
    private LocalDateTime dueDate;
}
