package com.example.relationship.onetomanyAppDemo.service;

import com.example.relationship.onetomanyAppDemo.exception.ResourceNotFoundException;
import com.example.relationship.onetomanyAppDemo.model.Comment;
import com.example.relationship.onetomanyAppDemo.model.Post;
import com.example.relationship.onetomanyAppDemo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceProvider implements IPostServiceProvider {

    //Used for automatic dependency injection
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long postId, Post postRequest) {
        //Lambda expression is being used
        return postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDescription());
            post.setContent(postRequest.getContent());
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @Override
    public ResponseEntity<?> deletePost(Long postId) {
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @Override
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public List<Comment> getAllComments(Long postId) {

        Optional<Post> post = postRepository.findById(postId);

        if(post.isPresent()){
            Post existingPost = post.get();
            return existingPost.getComment();
        }

        else {
            return null;
        }

    }


}
