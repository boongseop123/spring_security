package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service//1. IOC 2. 트랜잭션 관리
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Transactional//Write(insert, update, delete)
    public User Signup(User user){//이 user는 외부에서 통신을 통해 받은 데이터를 User 객체에 담음
        String rawPassword=user.getPassword();
        String encPassword=bCryptPasswordEncoder.encode(rawPassword);//해시로 암호화
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");//관리자 ROLE_ADMIN
        User userEntity = userRepository.save(user);
        //userEntity는 데이터베이스에 있는 데이터를 User 객체에 담음
        return userEntity;
    }
}
