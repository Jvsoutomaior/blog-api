package dev.jvsmaior.blog_api.controller;

import dev.jvsmaior.blog_api.entity.BlogPost;
import dev.jvsmaior.blog_api.repository.BlogPostRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class BlogPostController {

    private final BlogPostRepository repository;

    public BlogPostController(BlogPostRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/BlogPosts")
    public List<BlogPost> getAllBlogPosts(){
        return repository.findAll();
    }

    @PostMapping("/BlogPosts")
    public BlogPost saveBlogPost(@RequestBody BlogPost blogPost){
        return repository.save(blogPost);
    }

    @PutMapping("/BlogPosts/{id}")
    public BlogPost updateBlogPost(@RequestBody BlogPost updatedBlogPost, @PathVariable Long id){

        BlogPost existing = repository.findById(id).orElseThrow();

        existing.setAuthorName(updatedBlogPost.getAuthorName());
        existing.setTitle(updatedBlogPost.getTitle());
        existing.setContent(updatedBlogPost.getContent());

        existing.setLastModifiedAt(LocalDateTime.now());

        return repository.save(existing);
    }

    @DeleteMapping("/BlogPosts/{id}")
    public void deleteBlogPost(@PathVariable Long id){
        repository.deleteById(id);
    }
}
