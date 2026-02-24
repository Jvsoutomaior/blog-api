CREATE TABLE blog_post (
    id BIGINT NOT NULL,
    author_name VARCHAR(50) NOT NULL,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    last_modified_at TIMESTAMP NOT NULL,
    CONSTRAINT blog_post_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE blog_post_seq START WITH 1 INCREMENT BY 50;