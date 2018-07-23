package com.example.relationship.onetomanyAppDemo.repository;
import com.example.relationship.onetomanyAppDemo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {


}
