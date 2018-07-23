package com.example.relationship.onetomanyAppDemo.model;

import org.springframework.lang.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Comments")

public class Comment extends AuditModel implements Serializable {
    //-------https://www.thoughts-on-java.org/entity-mappings-introduction-jpa-fetchtypes/-------



    public Comment(){

    }

    public Comment(Post post) {
        this.post = post;
    }

    public Comment(Long id, @NotNull String text, Post post) {
        this.id = id;
        this.text = text;
        this.post = post;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    private String text;

    @ManyToOne
    @Nullable
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
