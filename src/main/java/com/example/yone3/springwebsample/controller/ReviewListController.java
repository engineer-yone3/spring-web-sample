package com.example.yone3.springwebsample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ReviewListController {

    @GetMapping("/")
    public String reviewList(Model model) {
        return "home";
    }
}
