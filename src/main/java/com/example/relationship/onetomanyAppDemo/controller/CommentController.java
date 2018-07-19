package com.example.relationship.onetomanyAppDemo.controller;


import com.example.relationship.onetomanyAppDemo.model.Comment;
import com.example.relationship.onetomanyAppDemo.repository.PostRepository;
import com.example.relationship.onetomanyAppDemo.service.CommentServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentServiceProvider commentService;

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(@PathVariable(value = "postId") Long postId,
                                 @Valid @RequestBody Comment comment) {
       return commentService.createComment(postId,comment);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable (value = "postId") Long postId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @Valid @RequestBody Comment commentRequest) {
        return commentService.updateComment(postId,commentId,commentRequest);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
                                           @PathVariable (value = "commentId") Long commentId) {
        return commentService.deleteComment(postId,commentId);
    }


}
