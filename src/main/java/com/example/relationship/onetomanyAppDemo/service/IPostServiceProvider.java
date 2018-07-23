package com.example.relationship.onetomanyAppDemo.service;

import com.example.relationship.onetomanyAppDemo.model.Comment;
import com.example.relationship.onetomanyAppDemo.model.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostServiceProvider {

     Post createPost(Post post);
     Post updatePost(Long postId, Post postRequest);
     ResponseEntity<?> deletePost(Long postId);
     Page<Post> getAllPosts(Pageable pageable);
     List<Comment> getAllComments(Long postId);
}
