CREATE TABLE reviews (
    review_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    reviewer_name VARCHAR(50) NOT NULL,
    book_name VARCHAR(100) NOT NULL,
    image_url VARCHAR(256),
    evaluation CHAR(1) NOT NULL,
    content VARCHAR(512) NOT NULL,
    ip_address VARCHAR(50),
    user_agent VARCHAR(512),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    PRIMARY KEY (review_id)
);