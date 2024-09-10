package com.example.yone3.springwebsample.controller;

import com.example.yone3.springwebsample.entity.ReviewEntity;
import com.example.yone3.springwebsample.form.ReviewRegisterForm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.thymeleaf.util.StringUtils;


@Controller
public class ReviewRegisterController {

    private static final String UPLOAD_DIR = "img/uploads";

    @GetMapping("/register")
    public String registerView(Model model) {
        model.addAttribute("registerForm", new ReviewRegisterForm());
        return "register";
    }

    @PostMapping("/create")
    public String createReviewData(
            @ModelAttribute("registerForm") @Validated ReviewRegisterForm form,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        String uploadfilePath = "";
        if (!form.getImage().isEmpty()) {
            try {
                uploadfilePath = createFile(form.getImage());
            } catch (Exception e) {
                FieldError error = new FieldError(bindingResult.getObjectName(), "image", "画像の読み込みに失敗しました");
                bindingResult.addError(error);
                return "register";
            }
        }

        ReviewEntity formEntity = ReviewEntity.newInstance(
                0,
                form.getReviewerName(),
                form.getBookName(),
                StringUtils.isEmpty(uploadfilePath) ? form.getImageUrl() : uploadfilePath,
                form.getEvaluation(),
                form.getContent(),
                request.getRemoteAddr(),
                request.getHeader("User-Agent"),
                null,
                null,
                null
        );

        return "";
    }

    private String createFile(MultipartFile file) throws Exception {

        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 画像ファイルの保存先パス
            String filePath = UPLOAD_DIR + File.separator + file.getOriginalFilename();

            // 画像ファイルをディスクに保存
            Path destination = new File(filePath).toPath();
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

            return filePath;
        } catch (Exception e) {
            throw e;
        }
    }

}
