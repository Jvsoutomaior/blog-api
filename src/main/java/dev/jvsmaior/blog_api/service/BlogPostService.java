package dev.jvsmaior.blog_api.service;

import dev.jvsmaior.blog_api.dto.BlogPostRequest;
import dev.jvsmaior.blog_api.dto.BlogPostResponse;
import dev.jvsmaior.blog_api.entity.BlogPost;
import dev.jvsmaior.blog_api.exception.ResourceNotFoundException;
import dev.jvsmaior.blog_api.repository.BlogPostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.StreamSupport.stream;
import static org.hibernate.Hibernate.map;

@Service
public class BlogPostService {
    private final BlogPostRepository repository;

    public BlogPostService(BlogPostRepository repository) {
        this.repository = repository;
    }

    private BlogPostResponse entityToResponseConverter(BlogPost post){
        return new BlogPostResponse(
                post.getId(),
                post.getAuthorName(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getLastModifiedAt()
        );
    }

    public List<BlogPostResponse> getAllBlogPosts(){
        return repository.findAll().stream().map(this::entityToResponseConverter).toList();
    }

    public BlogPostResponse getBlogPostById(Long id){
        return  repository.findById(id).map(this::entityToResponseConverter).orElseThrow(() -> new ResourceNotFoundException("No Blog Post with ID of %d found".formatted(id)));
    }

    public BlogPostResponse saveBlogPost(BlogPostRequest request){
        // request -> entity
        BlogPost newBlogPost = new BlogPost(
                request.authorName(),
                request.title(),
                request.content()
        );

        // save in repository
        BlogPost entity = repository.save(newBlogPost);

        // return (entity -> response)
        return entityToResponseConverter(entity);
    }

    public BlogPostResponse updateBlogPost(BlogPostRequest updatedBlogPost, Long id){
        BlogPost existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cannot update Blog Post, no Blog Post with ID of %d found".formatted(id)));

        existing.setAuthorName(updatedBlogPost.authorName());
        existing.setTitle(updatedBlogPost.title());
        existing.setContent(updatedBlogPost.content());

        existing.setLastModifiedAt(LocalDateTime.now());

        BlogPost newBlogPost = repository.save(existing);

        return entityToResponseConverter(newBlogPost);
    }

    public void deleteBlogPost(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Cannot delete Blog Post, no Blog Post with ID of %d found".formatted(id));
        }
        repository.deleteById(id);
    }
    
}
