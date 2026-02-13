package dev.jvsmaior.blog_api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BlogPostRequest(
    @Size(min = 3, max = 50, message = "Author name must be between 3 and 50 characters")
    @NotBlank(message = "Author Name (authorName) is mandatory")
    String authorName,

    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    @NotBlank(message = "Title is mandatory")
    String title,

    @Column(nullable = false)
    @NotBlank(message = "Blog content is mandatory")
    String content
) {
}
