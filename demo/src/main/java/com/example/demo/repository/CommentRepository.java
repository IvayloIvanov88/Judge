package com.example.demo.repository;

import com.example.demo.model.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<CommentEntity, String> {

    @Query("SELECT AVG(c.score) FROM CommentEntity c")
    Double findAverageScore();
}
