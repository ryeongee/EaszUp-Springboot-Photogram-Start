package com.cos.photogramstart.domain.user;
//JPA- java persistance api(자바로 데이터를 영구적으로(DB) 저장할 수 있는 api를 제공해줌)

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor // 전체 생성자
@NoArgsConstructor // 빈 생성자
@Data //getter&setter
@Entity // db에 테이블을 생성
public class User {
    @Id // primary key 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 db를 따라간다. mysql: autoIncreament oracle:sequence 알아서 따라가겠다.
    private Integer id; // long이 맞는데 그냥 편하게 여기선 int로 함
    @Column(length = 20, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website;
    private String bio; //자기소개
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; // user img
    private String role; // 권한
    private LocalDateTime createDate;

    @PrePersist // db에 insert 되기 전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

}
