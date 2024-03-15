package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller//1. IOC, 2. 파일을 리턴하는 컨트롤러
public class AuthController {

    @GetMapping("/auth/signin")
    public String signInForm(){
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signUpForm(){
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signUp(SignupDto signupDto){
        User user=signupDto.toEntity();
        return "auth/signin";
    }
}
