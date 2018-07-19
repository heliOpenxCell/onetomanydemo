package com.example.relationship.onetomanyAppDemo.controller;

import com.example.relationship.onetomanyAppDemo.model.Post;
import com.example.relationship.onetomanyAppDemo.service.PostServiceProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/#1-auditmodel
@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostServiceProvider postService;

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postService.getAllPosts(pageable);
    }

    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
        return postService.updatePost(postId,postRequest);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
       return postService.deletePost(postId);
    }

}
