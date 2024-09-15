package com.example.yone3.springwebsample.mapper;

import com.example.yone3.springwebsample.entity.ReviewEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {

    /**
     * 書籍レビュー情報を検索する
     * @param reviewerName レビュー者名
     * @param bookName 書籍名
     * @param ipAddress 送信元IPアドレス
     * @return 検索結果
     */
    List<ReviewEntity> getReviews(@Param("reviewerName") String reviewerName, @Param("bookName") String bookName, @Param("ipAddress") String ipAddress);

    /**
     * 書籍レビュー情報を登録する
     * @param entity 書籍レビュー情報Entity
     */
    void insertReview(@Param("entity") ReviewEntity entity);

    /**
     * 書籍レビュー情報を更新する
     * @param entity 書籍レビュー情報Entity
     */
    void updateReview(@Param("entity") ReviewEntity entity);
}
