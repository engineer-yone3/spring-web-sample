package com.example.yone3.springwebsample.service;

import com.example.yone3.springwebsample.entity.ReviewEntity;
import com.example.yone3.springwebsample.exception.TooManyResultsException;
import com.example.yone3.springwebsample.mapper.ReviewMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper mapper;

    /**
     * 書籍レビュー情報を登録・更新する
     * @param entity ReviewEntity 登録・更新情報
     * @throws TooManyResultsException 更新時に対象データが複数件ある場合の例外
     * @throws Exception 処理例外
     */
    @Transactional
    public void RegisterReview(ReviewEntity entity) throws Exception {
        try {
            if (entity.getReviewId() > 0) {
                ReviewEntity prevData = mapper.findById(entity.getReviewId());
                if (prevData != null) {
                    entity.setUpdatedAt(LocalDateTime.now());
                    mapper.updateReview(entity);
                }
            } else {
                List<ReviewEntity> results = mapper.getReviews(entity.getReviewerName(), entity.getBookName(), entity.getIpAddress());
                if (results.isEmpty()) {
                    // 新規登録
                    LocalDateTime nowDateTime = LocalDateTime.now();
                    entity.setCreatedAt(nowDateTime);
                    entity.setUpdatedAt(nowDateTime);
                    mapper.insertReview(entity);
                } else if (results.size() > 1) {
                    // 複数件ヒットした場合はエラー
                    throw new TooManyResultsException();
                } else {
                    // 更新
                    entity.setReviewId(results.get(0).getReviewId());
                    entity.setUpdatedAt(LocalDateTime.now());
                    mapper.updateReview(entity);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    /**
     * 書籍レビューの一覧を取得する
     * @param page ページ番号
     * @param size 1ページあたりの表示サイズ(件数)
     * @param bookName 書籍名(部分一致検索)
     * @return List<ReviewEntity> 検索結果
     */
    public PageInfo<ReviewEntity> getReviews(int page, int size, String bookName, String ipAddress) {

        PageHelper.startPage(page, size);

        List<ReviewEntity> reviews = null;
        if (StringUtils.isEmpty(bookName)) {
            reviews = mapper.searchReviews(null);
        } else {
            reviews = mapper.searchReviews("%" + bookName + "%");
        }

        if (!reviews.isEmpty()) {
            for (ReviewEntity review : reviews) {
                review.setCanDelete(ipAddress.equals(review.getIpAddress()));
            }
        }

        return new PageInfo<>(reviews);
    }

    /**
     * 書籍レビュー情報を削除する
     * @param id レビューID
     */
    public void deleteReview(Long id) {
        try {
            mapper.deleteReview(id);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 書籍レビュー情報を1件取得する
     * @param id 書籍レビューID
     * @return 書籍レビュー情報Entity
     */
    public ReviewEntity findReview(Long id) {
        return mapper.findById(id);
    }
}
