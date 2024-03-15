package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//어노테이션이 없어도 jpaRepository를 상속하면 ioc가 자동 등록
public interface UserRepository extends JpaRepository<User,Integer> {
}
