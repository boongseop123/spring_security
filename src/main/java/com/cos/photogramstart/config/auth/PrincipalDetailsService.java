package com.cos.photogramstart.config.auth;


import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    //1. 패스워드는 알아서 체킹하니까 신경쓸필요 x
    //2. 리턴이 잘되면 자동으로 세션을 만듬
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User userEntity=userRepository.findByUsername(username);
        if(userEntity==null){
            return null;
        }
        else{
            return new PrincipalDetails(userEntity);
        }
    }
}
