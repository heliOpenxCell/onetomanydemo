package com.example.relationship.onetomanyAppDemo.controller;
import com.example.relationship.onetomanyAppDemo.model.Comment;
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


    @PostMapping("/createcomment/{postId}")
    public Comment createComment(@PathVariable (value = "postId") Long postId,@Valid @RequestBody Comment comment) {
       return commentService.createComment(postId, comment);
    }

    @PutMapping("/updatecomment/{commentId}")
    public Comment updateComment(@PathVariable (value = "commentId") Long commentId,
                                 @Valid @RequestBody Comment commentRequest) {
        return commentService.updateComment(commentId,commentRequest);
    }

    @DeleteMapping("/deletecomment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "commentId") Long commentId) {
        return commentService.deleteComment(commentId);
    }

    @GetMapping("getcomment/{commentId}")
    public Comment getComment(@PathVariable (value = "commentId") Long commentId){
        return commentService.fetchComment(commentId);
    }


}
