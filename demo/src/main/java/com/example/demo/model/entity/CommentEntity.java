package com.example.demo.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@ToString
@Data
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "text_content", nullable = false, columnDefinition = "TEXT")
    private String textContent;

    @ManyToOne
    private UserEntity author;

    @ManyToOne
    private HomeworkEntity homework;

}
