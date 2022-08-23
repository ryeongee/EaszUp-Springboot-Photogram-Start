package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//Annotation 없이도 JPA 레파지토리를 상속하면 IoC 등록이 자동으로 됨
public interface UserRepository extends JpaRepository<User ,Integer> {

}
