package com.cos.photogramstart.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

//JPA-->JAVA PERSISTENCE API(자바로 데이터를 영구적으로 저장(db)할 수 있는 api 제공)
@Builder//Builder 패턴으로 데이터를 담을 수 있게 해줌
@Data//자동으로 Getter, Setter, toString을 생성
@Entity//db에 테이블을 생성
@NoArgsConstructor//빈 생성자를 자동으로 생성
@AllArgsConstructor//모든 생성자를 자동으로 생성
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//번호 증가 전략이 데이터베이스를 따라간다
    private Integer id;
    @Column(length=20, unique = true)//중복된 값 들어가지 않도록
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website;
    private String bio;//자기소개
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;
    private String profileImageUrl;
    private String role;
    private LocalDateTime createDate;//자동으로 삽입됨
    @PrePersist//DB에 INSERT되기 직전에 실행
    public void createDate(){
        this.createDate=LocalDateTime.now();
    }
}
