package dev.jvsmaior.blog_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


@Entity
public class BlogPost {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, max = 50)
    @NotBlank(message = "Author Name (authorName) is mandatory")
    private String authorName;

    @Column(nullable = false)
    @Size(min = 3, max = 50)
    @NotBlank(message = "title is mandatory")
    private String title;

    @Lob
    @Column(nullable = false)
    @NotBlank(message = "blog content is mandatory")
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime lastModifiedAt;

    protected BlogPost() {
        // required by JPA
    }

    public BlogPost(String authorName, String title, String content) {
        this.authorName = authorName;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = this.createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
}
