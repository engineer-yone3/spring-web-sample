package com.example.yone3.springwebsample.controller;

import com.example.yone3.springwebsample.entity.ReviewEntity;
import com.example.yone3.springwebsample.exception.TooManyResultsException;
import com.example.yone3.springwebsample.form.ReviewRegisterForm;
import com.example.yone3.springwebsample.service.ReviewService;
import com.example.yone3.springwebsample.service.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;


@Controller
public class ReviewRegisterController {

    @Autowired
    private ReviewService service;

    @Autowired
    private StorageService storageService;

    @GetMapping("/register")
    public String registerView(Model model) {
        model.addAttribute("registerForm", new ReviewRegisterForm());
        return "register";
    }

    @PostMapping("/create")
    public String createReviewData(
            @ModelAttribute("registerForm") @Validated ReviewRegisterForm form,
            BindingResult bindingResult,
            HttpServletRequest request,
            Model model
    ) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        String uploadfilePath = "";
        if (!form.getImage().isEmpty()) {
            uploadfilePath = storageService.uploadImageFile(form.getImage());
            if (StringUtils.isEmpty(uploadfilePath)) {
                FieldError error = new FieldError(bindingResult.getObjectName(), "image", "画像の読み込みに失敗しました");
                bindingResult.addError(error);
                return "register";
            }
        }

        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        ReviewEntity formEntity = ReviewEntity.newInstance(
                StringUtils.isEmpty(form.getReviewId()) ? 0 : Long.parseLong(form.getReviewId()),
                form.getReviewerName(),
                form.getBookName(),
                StringUtils.isEmpty(uploadfilePath) ? form.getImageUrl() : uploadfilePath,
                form.getEvaluation(),
                form.getContent(),
                ipAddress,
                request.getHeader("User-Agent"),
                null,
                null,
                null,
                false
        );

        try {
            service.RegisterReview(formEntity);
            if (!StringUtils.isEmpty(uploadfilePath)) {
                form.setImageUrl(uploadfilePath);
            }
        } catch (TooManyResultsException ex) {
            ObjectError error = new ObjectError("globalError", "登録データが複数件あり、処理できない状態です");
            bindingResult.addError(error);
            return "register";
        } catch (Exception e) {
            System.out.println("エラーが発生しました");
            ObjectError error = new ObjectError("globalError", "データ更新時にエラーが発生しました。お手数ですが、操作をやり直してください");
            bindingResult.addError(error);
            return "register";
        }

        model.addAttribute("successMessage", "正常に登録/更新できました");
        return "register";
    }

}
