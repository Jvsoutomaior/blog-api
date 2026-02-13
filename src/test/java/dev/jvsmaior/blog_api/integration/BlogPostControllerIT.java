package dev.jvsmaior.blog_api.integration;

import dev.jvsmaior.blog_api.dto.BlogPostRequest;
import dev.jvsmaior.blog_api.dto.BlogPostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlogPostControllerIT {

    RestTestClient client;

    @BeforeEach
    void setClient(){
        client = RestTestClient.bindToServer().baseUrl("http://localhost:8080").build();
    }

    @Test
    void shouldCreateBlogPosts(){
        client.post()
                .uri("/blogPosts")
                .body(new BlogPostRequest("Author", "Title", "Content"))
                .exchange().expectStatus().isCreated();
    }

    @Test
    void shouldFindAllPosts(){
        client.get().uri("/blogPosts")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<BlogPostResponse>>() {}).value(blogPosts -> assertThat(blogPosts).hasSize(1));
    }

    @Test
    void shouldUpdateBlogPost(){
        BlogPostRequest updated = new BlogPostRequest("Author", "Updated Title", "Updated Content");
        client.put()
                .uri("/blogPosts/1")
                .body(updated)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BlogPostResponse.class)
                .value(blogPost -> assertThat(blogPost.title()).isEqualTo("Updated Title"));
    }

    @Test
    void shouldDeleteBlogPost(){
        client.delete()
                .uri("/blogPosts/1")
                .exchange()
                .expectStatus().isOk();
        client.get()
                .uri("/blogPosts")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<BlogPostResponse>>() {})
                .value(blogPosts -> assertThat(blogPosts).isEmpty());
    }
}
