package com.example.relationship.onetomanyAppDemo.service;

import com.example.relationship.onetomanyAppDemo.exception.ResourceNotFoundException;
import com.example.relationship.onetomanyAppDemo.model.Comment;
import com.example.relationship.onetomanyAppDemo.repository.CommentRepository;
import com.example.relationship.onetomanyAppDemo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceProvider {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;


        /*public Page<Comment> getAllCommentsByPostId(Long postId, Pageable pageable) {
            return commentRepository.findByPostId(postId, (java.awt.print.Pageable) pageable);
        }*/


    public Comment createComment(Long postId, Comment comment) {

        //So basically in this first we tried to find out post object and we set it to comment object.
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }


    public Comment updateComment(Long postId, Long commentId, Comment commentRequest) {
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        return commentRepository.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }


    public ResponseEntity<?> deleteComment(Long postId,
                                           Long commentId) {
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        return commentRepository.findById(commentId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + " not found"));
    }
}
