package com.example.yone3.springwebsample.controller;

import com.example.yone3.springwebsample.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ReviewDeleteController {

    @Autowired
    private ReviewService service;

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable(name = "id") Long id) {
        try {
            service.deleteReview(id);
            return ResponseEntity.ok().body(Map.of("success", true, "message", "削除に成功しました"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "削除に失敗しました"));
        }
    }
}
