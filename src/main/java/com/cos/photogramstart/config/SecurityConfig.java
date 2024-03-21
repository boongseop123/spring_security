package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity//해당 파일로 시큐리티를 활성화
@Configuration//IOC
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //super 삭제->기존 시큐리티가 가지고 있는 기능이 다 비활성화
        http.csrf().disable();//postman이던, 홈페이지던 구분하지 않겠다.(csrf)비활성화
        http.authorizeRequests()
                .antMatchers("/","/users/**","/image/**","/subscribe/**","/comment/**").authenticated()//이 주소는 인증이 필요
                .anyRequest().permitAll()//이 주소는 다 허용
                .and()
                .formLogin()//인증이 필요한 페이지에 접속을 시도하면
                .loginPage("/auth/signin")//Get
                .loginProcessingUrl("auth/siginin")//Post-->스프링시큐리티가 로그인프로세스 진행
                .defaultSuccessUrl("/");//로그인을 정상적으로 하면 이 url로 오도록
    }
}
