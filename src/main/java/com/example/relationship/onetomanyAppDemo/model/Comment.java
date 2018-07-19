package com.example.relationship.onetomanyAppDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Comments")
public class Comment extends AuditModel {

    //The default depends on the cardinality of the relationship. All to-one relationships use FetchType.EAGER and all to-many relationships FetchType.LAZY.
    //The FetchType.LAZY tells Hibernate to only fetch the related entities from the database when you use the relationship. This is a good idea in general because there’s no reason to select entities you don’t need for your uses case.
    //-------https://www.thoughts-on-java.org/entity-mappings-introduction-jpa-fetchtypes/-------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
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
