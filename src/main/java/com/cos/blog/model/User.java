package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity //User 클래스가 자동으로 MySQL에 테이블이 생성이 된다.
public class User {

	@Id //Primary key
	@GeneratedValue(strategy= GenerationType.IDENTITY)//프로텍트에서 연결된 DB의 넘버링 전략을 따른다. 
	private int id;//auto_increment  => mysql의 전략
	
	@Column(nullable=false, length=30)
	private String username; //Id
	
	@Column(nullable=false, length=100)
	private String password;
	
	@Column(nullable=false, length=50)
	private String email;
	
	@ColumnDefault("'user'")
	private String role; //원래 Enum을 쓰는게 좋다 => 도메인을 만들 수 있음 //admin, user, manager
	
	@CreationTimestamp //시간이 자동 입력
	private Timestamp createDate;
	
}
