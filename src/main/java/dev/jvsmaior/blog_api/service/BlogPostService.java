package dev.jvsmaior.blog_api.service;

import dev.jvsmaior.blog_api.entity.BlogPost;
import dev.jvsmaior.blog_api.exception.ResourceNotFoundException;
import dev.jvsmaior.blog_api.repository.BlogPostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogPostService {
    private final BlogPostRepository repository;

    public BlogPostService(BlogPostRepository repository) {
        this.repository = repository;
    }

    public List<BlogPost> getAllBlogPosts(){
        return repository.findAll();
    }

    public BlogPost getBlogPostById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Blog Post with ID of %d found".formatted(id)));
    }

    public BlogPost saveBlogPost(BlogPost blogPost){
        return repository.save(blogPost);
    }

    public BlogPost updateBlogPost(BlogPost updatedBlogPost, Long id){
        BlogPost existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cannot update Blog Post, no Blog Post with ID of %d found".formatted(id)));

        existing.setAuthorName(updatedBlogPost.getAuthorName());
        existing.setTitle(updatedBlogPost.getTitle());
        existing.setContent(updatedBlogPost.getContent());

        existing.setLastModifiedAt(LocalDateTime.now());

        return repository.save(existing);
    }

    public void deleteBlogPost(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Cannot delete Blog Post, no Blog Post with ID of %d found".formatted(id));
        }
        repository.deleteById(id);
    }
    
}
