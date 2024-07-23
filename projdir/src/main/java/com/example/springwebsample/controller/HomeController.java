package com.example.springwebsample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String hello(Model model) {
        model.addAttribute("message", "Hello Thymeleaf!!");
        return "index";
    }

    @GetMapping("/hoge")
    public String hoge(Model model) {
        model.addAttribute("message", "なんだって！？？");
        return "aaaa/index";
    }
}
