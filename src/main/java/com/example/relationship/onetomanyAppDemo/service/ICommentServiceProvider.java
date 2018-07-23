package com.example.relationship.onetomanyAppDemo.service;

import com.example.relationship.onetomanyAppDemo.model.Comment;
import org.springframework.http.ResponseEntity;

public interface ICommentServiceProvider {

     Comment createComment(Long postId,Comment comment);
     Comment updateComment(Long commentId, Comment commentRequest);
     ResponseEntity<?> deleteComment(Long commentId);
     Comment fetchComment(Long commentId);

}
