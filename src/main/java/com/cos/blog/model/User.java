package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert // insert시 null인 필드를 제거해준다
@Entity //User 클래스가 자동으로 MySQL에 테이블이 생성이 된다.
public class User {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//프로텍트에서 연결된 DB의 넘버링 전략을 따른다.
    @Column(name = "USER_SQ")
    private int id;//auto_increment  => mysql의 전략

    @Column(nullable = false, length = 30, unique = true)
    private String username; //Id

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

	private String oauth;

	@OneToMany(mappedBy = "user")
	private List<WorkOutHistory> workOutHistoryList;
    //DB는 RoleType이란게 없다
    //@ColumnDefault("user")
    @Enumerated(EnumType.STRING)
    private RoleType role; //원래 Enum을 쓰는게 좋다 => 도메인을 만들 수 있음 //admin, user, manager

    @Enumerated(EnumType.STRING)
    private Role role2; //원래 Enum을 쓰는게 좋다 => 도메인을 만들 수 있음 //admin, user, manager


    @CreationTimestamp //시간이 자동 입력
    private Timestamp createDate;

    public User update(String username) {
        this.username = username;

        return this;
    }
}
