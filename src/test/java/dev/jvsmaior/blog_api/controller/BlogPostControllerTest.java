package dev.jvsmaior.blog_api.controller;

import dev.jvsmaior.blog_api.entity.BlogPost;
import dev.jvsmaior.blog_api.repository.BlogPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class BlogPostControllerTest {

    private RestTestClient client;

    @MockitoBean
    BlogPostRepository repository;

    @BeforeEach
    void setUp(){
        client = RestTestClient.bindToController(new BlogPostController(repository)).build();
    }

    @Test
    void shouldFindAllBlogPosts(){
        BlogPost post = new BlogPost("Author", "Title", "Content");
        
        when(repository.findAll()).thenReturn(List.of(post));

        client.get()
            .uri("/BlogPosts")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(new org.springframework.core.ParameterizedTypeReference<List<BlogPost>>() {})
            .value(posts -> assertThat(posts).hasSize(1));
    }

    @Test
    void shouldSaveBlogPost(){
        BlogPost post = new BlogPost("Author", "Title", "Content");
        BlogPost saved = new BlogPost("Author", "Title", "Content");
        when(repository.save(post)).thenReturn(saved);

        client.post()
            .uri("/BlogPosts")
            .body(post)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(BlogPost.class);
    }

    @Test
    void shouldUpdateBlogPost(){
        BlogPost updated = new BlogPost("NewAuthor", "NewTitle", "NewContent");
        BlogPost existing = new BlogPost("Author", "Title", "Content");
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any())).thenReturn(existing);

        client.put()
            .uri("/BlogPosts/1")
            .body(updated)
            .exchange()
            .expectStatus()
            .isOk();
    }

    @Test
    void shouldDeleteBlogPost(){
        doNothing().when(repository).deleteById(1L);

        client.delete().uri("/BlogPosts/1").exchange().expectStatus().isOk();
        verify(repository, times(1)).deleteById(1L);
    }
}
