package dev.jvsmaior.blog_api.dto;

import java.time.LocalDateTime;

public record BlogPostResponse(Long id, String authorName, String title, String content, LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
}
