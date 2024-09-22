package com.example.yone3.springwebsample.controller;

import com.example.yone3.springwebsample.entity.ReviewEntity;
import com.example.yone3.springwebsample.service.ReviewService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;


@Controller
public class ReviewListController {

    @Autowired
    private ReviewService service;

    @GetMapping("/")
    public String reviewList(
            @RequestParam(name = "searchBookName", required = false) String searchBookName,
            @RequestParam(name = "page", required = false) String page,
            @RequestParam(name = "pageSize", required = false) String pageSize,
            HttpServletRequest request,
            Model model) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        int searchPageNo = StringUtils.isEmpty(page) ? 1 : Integer.parseInt(page);
        int searchPageSize = StringUtils.isEmpty(pageSize) ? 30 : Integer.parseInt(pageSize);
        PageInfo<ReviewEntity> results = service.getReviews(searchPageNo, searchPageSize, searchBookName, ipAddress);
        model.addAttribute("searchBookName", searchBookName);
        model.addAttribute("reviews", results);
        return "home";
    }
}
