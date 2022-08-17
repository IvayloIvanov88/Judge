package com.example.demo.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "homeworks")
public class Homework extends BaseEntity{

    @Column(name = "added_on")
    private LocalDateTime addedOn;

    @Column(name = "git_address")
    private String gitAddress;

    @ManyToOne
    private User author;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
}
