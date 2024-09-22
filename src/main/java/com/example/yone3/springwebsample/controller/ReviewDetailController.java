package com.example.yone3.springwebsample.controller;

import com.example.yone3.springwebsample.entity.ReviewEntity;
import com.example.yone3.springwebsample.form.ReviewRegisterForm;
import com.example.yone3.springwebsample.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ReviewDetailController {

    @Autowired
    private ReviewService service;

    @GetMapping("/edit/{id}")
    public String editReview(@PathVariable(name = "id") Long id, Model model) {
        ReviewEntity result = service.findReview(id);

        ReviewRegisterForm form = new ReviewRegisterForm();
        form.setReviewId(String.valueOf(result.getReviewId()));
        form.setReviewerName(result.getReviewerName());
        form.setBookName(result.getBookName());
        form.setImageUrl(result.getImageUrl());
        form.setEvaluation(result.getEvaluation());
        form.setContent(result.getContent());

        model.addAttribute("registerForm", form);

        return "register";
    }
}
