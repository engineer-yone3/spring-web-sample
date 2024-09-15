package com.example.yone3.springwebsample.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("reviews")
public class ReviewEntity {

    @Id
    @Column("review_id")
    private long reviewId;

    @Column("reviewer_name")
    private String reviewerName;

    @Column("book_name")
    private String bookName;

    @Column("image_url")
    private String imageUrl;

    @Column("evaluation")
    private String evaluation;

    @Column("content")
    private String content;

    @Column("ip_address")
    private String ipAddress;

    @Column("user_agent")
    private String userAgent;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;

    @Column("deleted_at")
    private LocalDateTime deletedAt;

    public static ReviewEntity newInstance(
            long reviewId,
            String reviewerName,
            String bookName,
            String imageUrl,
            String evaluation,
            String content,
            String ipAddress,
            String userAgent,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        ReviewEntity entity = new ReviewEntity();
        entity.setReviewId(reviewId);
        entity.setReviewerName(reviewerName);
        entity.setBookName(bookName);
        entity.setImageUrl(imageUrl);
        entity.setEvaluation(evaluation);
        entity.setContent(content);
        entity.setIpAddress(ipAddress);
        entity.setUserAgent(userAgent);
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(updatedAt);
        entity.setDeletedAt(deletedAt);

        return entity;
    }
}
