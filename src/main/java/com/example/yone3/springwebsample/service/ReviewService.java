package com.example.yone3.springwebsample.service;

import com.example.yone3.springwebsample.entity.ReviewEntity;
import com.example.yone3.springwebsample.repository.ReviewRepository;
import com.example.yone3.springwebsample.repository.UsrSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    public void RegisterReview(ReviewEntity entity) throws Exception {
        try {
            UsrSpecification<ReviewEntity> spec = new UsrSpecification<>();
            // 投稿者名、書籍名、ipaddressが合致していれば重複とみなす
            List<ReviewRepository> = repository.findAll(
                    Specification.where(spec.reviewerNameEqual(entity.getReviewerName()))
                            .and(spec.bookNameEqual(entity.getBookName()))
                            .and(spec.ipAddressEqual(entity.getIpAddress()))
            );
        } catch (Exception e) {
            throw e;
        }
    }
}
