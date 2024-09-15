package com.example.yone3.springwebsample.service;

import com.example.yone3.springwebsample.entity.ReviewEntity;
import com.example.yone3.springwebsample.exception.TooManyResultsException;
import com.example.yone3.springwebsample.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper mapper;

    @Transactional
    public void RegisterReview(ReviewEntity entity) throws Exception {
        System.out.println("登録/更新処理開始");
        try {
            List<ReviewEntity> results = mapper.getReviews(entity.getReviewerName(), entity.getBookName(), entity.getIpAddress());
            if (results.isEmpty()) {
                // 新規登録
                System.out.println("新規登録");
                LocalDateTime nowDateTime = LocalDateTime.now();
                entity.setCreatedAt(nowDateTime);
                entity.setUpdatedAt(nowDateTime);
                mapper.insertReview(entity);
            } else if (results.size() > 1) {
                // 複数件ヒットした場合はエラー
                throw new TooManyResultsException();
            } else {
                // 更新
                System.out.println("更新データ！");
                entity.setReviewId(results.get(0).getReviewId());
                entity.setUpdatedAt(LocalDateTime.now());
                mapper.updateReview(entity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
