package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SubscribeRepository subscribeRepository;

    @Transactional(readOnly = true)
    public UserProfileDto userProfile(int pageUserId,int principalId){
        UserProfileDto dto=new UserProfileDto();

        User userEntity=userRepository.findById(pageUserId).orElseThrow(()->{
            throw new CustomException("해당 프로필 페이지는 없다");
        });
        dto.setUser(userEntity);
        dto.setPageOwnerState(pageUserId==principalId);
        dto.setImageCount(userEntity.getImages().size());
        int subscribeState=subscribeRepository.mSubscribeState(principalId,pageUserId);
        int subscribeCount=subscribeRepository.mSubscribeCount(pageUserId);
        dto.setSubscribeState(subscribeState==1);
        dto.setSubsScribeCount(subscribeCount);
        return dto;
    }
    @Transactional
    public User modify(Integer id,User user){
        User userEntity=userRepository.findById(id).orElseThrow();

        userEntity.setName(user.getName());
        String rawPassword=user.getPassword();
        String encPassword=bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
        return userEntity;
    }
}
