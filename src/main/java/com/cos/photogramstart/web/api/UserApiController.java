package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(
            @PathVariable Integer id,
            @Valid UserUpdateDto userUpdateDto,
            BindingResult bindingResult,//꼭 @Valid가 적혀있는 다음 파라미터
            @AuthenticationPrincipal PrincipalDetails principalDetails){

        if(bindingResult.hasErrors()){
            Map<String,String> errorMap=new HashMap<>();
            for(FieldError error:bindingResult.getFieldErrors()){
                System.out.println("=========");
                System.out.println(error.getDefaultMessage());
                System.out.println("=========");
            }
            throw new CustomValidApiException("유효성 검사 실패함",errorMap);
        }
        else {
            User userEntity = userService.modify(id, userUpdateDto.toEntity());
            principalDetails.setUser(userEntity);//세션 정보 변경
            return new CMRespDto<>(1, "회원수정완료", userEntity);
        }
    }
}