package com.example.relationship.onetomanyAppDemo.service;

import com.example.relationship.onetomanyAppDemo.exception.ResourceNotFoundException;
import com.example.relationship.onetomanyAppDemo.model.Comment;
import com.example.relationship.onetomanyAppDemo.model.Post;
import com.example.relationship.onetomanyAppDemo.repository.CommentRepository;
import com.example.relationship.onetomanyAppDemo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceProvider implements ICommentServiceProvider{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Long postId, Comment comment) {

        // Using PostRepo
        /*Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            Post existingPost = post.get();

           //It will add comment to Post object as well
            existingPost.addComment(comment);

            // Save the Post entity
            postRepository.save(existingPost);
            return comment;

        }else{
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }*/


        // Using Comment Repo

        comment.setPost(new Post(postId));
        commentRepository.save(comment);
        return comment;


    }

    public Comment updateComment(Long commentId, Comment commentRequest) {

        if(!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("CommentId " + commentId + " not found");
        }
        return commentRepository.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));

    }


    public ResponseEntity<?> deleteComment(Long commentId) {

        if(!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("CommentId " + commentId + " not found");
        }
        return commentRepository.findById(commentId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + " not found"));

    }

    @Override
    public Comment fetchComment(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if(comment.isPresent()){
            return comment.get();
        }else{
            throw new ResourceNotFoundException("CommentId " + commentId + " not found");
        }
    }

}
