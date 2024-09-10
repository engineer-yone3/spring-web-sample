package com.example.yone3.springwebsample.form;

import java.io.Serializable;

import com.example.yone3.springwebsample.form.validation.ImageRequired;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@ImageRequired
public class ReviewRegisterForm implements Serializable {

    @Size(max = 50)
    @NotBlank(message = "{NotBlank.registerForm.reviewer_name}")
    private String reviewerName;

    @NotBlank(message = "{NotBlank.registerForm.book_name}")
    private String bookName;

    private String imageUrl;
    private MultipartFile image;

    @NotBlank(message = "{NotBlank.registerForm.evaluation}")
    private String evaluation;

    @NotBlank(message = "{NotBlank.registerForm.content}")
    private String content;
}
