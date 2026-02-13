package dev.jvsmaior.blog_api.controller;

import dev.jvsmaior.blog_api.dto.BlogPostRequest;
import dev.jvsmaior.blog_api.dto.BlogPostResponse;
import dev.jvsmaior.blog_api.entity.BlogPost;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.jvsmaior.blog_api.service.BlogPostService;

import java.util.List;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostController {

    private final BlogPostService service;

    public BlogPostController(BlogPostService service) {
        this.service = service;
    }

    @GetMapping
    public List<BlogPostResponse> getAllBlogPosts(){
        return service.getAllBlogPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPostResponse> getBlogPostById(@PathVariable Long id){
        BlogPostResponse blogPost = service.getBlogPostById(id);
        return ResponseEntity.ok(blogPost);
    }

    @PostMapping
    public ResponseEntity<BlogPostResponse> saveBlogPost(@Valid @RequestBody BlogPostRequest blogPost){
        BlogPostResponse savedBlogPost = service.saveBlogPost(blogPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBlogPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPostResponse> updateBlogPost(@Valid @RequestBody BlogPostRequest updatedBlogPost, @PathVariable Long id){
        BlogPostResponse updated = service.updateBlogPost(updatedBlogPost, id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteBlogPost(@PathVariable Long id){
        service.deleteBlogPost(id);
    }
}
