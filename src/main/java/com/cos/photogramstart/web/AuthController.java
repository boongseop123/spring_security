package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor//final에 대한 생성자를 만들어준다. DI할때 사용
@Controller//1. IOC, 2. 파일을 리턴하는 컨트롤러
public class AuthController {

    private final AuthService authService;
//    public AuthController(AuthService authService){
//        this.authService=authService;//의존성 주입
//    }
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
        User userEntity = authService.Signup(user);
        return "auth/signin";
    }
}
