package dev.jvsmaior.blog_api.repository;

import dev.jvsmaior.blog_api.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
}
