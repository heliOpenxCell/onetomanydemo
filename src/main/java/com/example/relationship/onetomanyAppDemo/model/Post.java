package com.example.relationship.onetomanyAppDemo.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post extends AuditModel implements Serializable {

    public Post(){
    }

    public Post(Long id, @NotNull @Size(max = 100) String title, @NotNull @Size(max = 250) String description, @NotNull String content, List<Comment> comment) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.comment = comment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String title;

    @NotNull
    @Size(max = 250)
    private String description;

    @NotNull
    @Lob
    private String content;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="post")
    @Nullable
    private List<Comment> comment;

    public Post(Long postId) {
        this.id=postId;
    }

    /*==================Getter setter methods===================*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComment() { return comment; }

    public void setComment(List<Comment> comment) { this.comment = comment; }

    /*==================Add and remove comment in existing post===================*/

    public Comment addComment(Comment comment) {
        this.getComment().add(comment);
        return comment;
    }

    public Comment removeOrder(Comment comment) {
        this.getComment().remove(comment);
        return comment;
    }
}