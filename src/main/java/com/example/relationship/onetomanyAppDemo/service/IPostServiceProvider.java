package com.example.relationship.onetomanyAppDemo.service;

import com.example.relationship.onetomanyAppDemo.model.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostServiceProvider {

    public Post createPost(Post post);
    public Post updatePost(Long postId, Post postRequest);
    public ResponseEntity<?> deletePost(Long postId);
    public Page<Post> getAllPosts(Pageable pageable);
}
