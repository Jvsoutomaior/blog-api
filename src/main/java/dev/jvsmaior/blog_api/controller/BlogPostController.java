package dev.jvsmaior.blog_api.controller;

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
    public List<BlogPost> getAllBlogPosts(){
        return service.getAllBlogPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Long id){
        BlogPost blogPost = service.getBlogPostById(id);
        return ResponseEntity.ok(blogPost);
    }

    @PostMapping
    public ResponseEntity<BlogPost> saveBlogPost(@Valid @RequestBody BlogPost blogPost){
        BlogPost savedBlogPost = service.saveBlogPost(blogPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBlogPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@Valid @RequestBody BlogPost updatedBlogPost, @PathVariable Long id){
        BlogPost updated = service.updateBlogPost(updatedBlogPost, id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteBlogPost(@PathVariable Long id){
        service.deleteBlogPost(id);
    }
}
