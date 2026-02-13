package dev.jvsmaior.blog_api.controller;

import dev.jvsmaior.blog_api.dto.BlogPostRequest;
import dev.jvsmaior.blog_api.dto.BlogPostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.jvsmaior.blog_api.service.BlogPostService;

import java.util.List;

@RestController
@RequestMapping("/blogPosts")
@Tag(name = "Blog Posts", description = "REST endpoints for Blog API")
public class BlogPostController {

    private final BlogPostService service;

    public BlogPostController(BlogPostService service) {
        this.service = service;
    }

    @Operation(summary = "Get all Blog Posts", description = "returns a list of BlogPostResponse with all the posts available")
    @GetMapping
    public List<BlogPostResponse> getAllBlogPosts(){
        return service.getAllBlogPosts();
    }

    @Operation(summary = "Get Blog Post by id", description = "Get a single Blog Post, identified by a id")
    @ApiResponse(responseCode = "200", description = "Success! Blog Post found")
    @ApiResponse(responseCode = "404", description = "Blog Post not found")
    @GetMapping("/{id}")
    public ResponseEntity<BlogPostResponse> getBlogPostById(@PathVariable Long id){
        BlogPostResponse blogPost = service.getBlogPostById(id);
        return ResponseEntity.ok(blogPost);
    }

    @Operation(summary = "Create Blog Post", description = "returns a ResponseEntity with the created BlogPostResponse and a status code")
    @ApiResponse(responseCode = "201", description = "Blog Post was created with success")
    @ApiResponse(responseCode = "400",description = "Validation error, mandatory fields missing")
    @PostMapping
    public ResponseEntity<BlogPostResponse> saveBlogPost(@Valid @RequestBody BlogPostRequest blogPost){
        BlogPostResponse savedBlogPost = service.saveBlogPost(blogPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBlogPost);
    }

    @Operation(summary = "Update Blog Post", description = "update Blog Post with new values")
    @ApiResponse(responseCode = "200", description = "Success! Blog Post updated")
    @ApiResponse(responseCode = "400", description = "Validation error, mandatory fields are missing or are invalid")
    @ApiResponse(responseCode = "404", description = "Blog Post not found")
    @PutMapping("/{id}")
    public ResponseEntity<BlogPostResponse> updateBlogPost(@Valid @RequestBody BlogPostRequest updatedBlogPost, @PathVariable Long id){
        BlogPostResponse updated = service.updateBlogPost(updatedBlogPost, id);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete Blog Post by Id", description = "Delete blog post identified by id")
    @DeleteMapping("/{id}")
    public void deleteBlogPost(@PathVariable Long id){
        service.deleteBlogPost(id);
    }
}
