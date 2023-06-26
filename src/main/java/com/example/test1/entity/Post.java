package com.example.test1.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToMany
    @JoinTable(
        name = "post_tag",
        joinColumns = { @JoinColumn(name = "post_id")},
        inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private Set<Tag> tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
