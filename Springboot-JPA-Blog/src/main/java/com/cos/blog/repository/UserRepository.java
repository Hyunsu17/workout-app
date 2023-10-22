package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO => in JPA
// 자동으로 bean 등록
// @Repository  생략가능
public interface UserRepository extends JpaRepository<User, Integer>{ //user 테이블이 관리하는 레파지토리, key는 인티저
	// SELECT * FROM user WHERE username=1?;
	Optional<User>findByUsername(String username);

	//JPA Naming 전략
	//Select * From user WHERE username=? AND password=?;
	User findByUsernameAndPassword(String username, String password);

    Optional<User> findByEmail(String email);
    //	@Query(value="Select * FROM user WHERE username = ?1 AND password= ?2 ",nativeQuery = true)
	//	User login(String username,String password);
}
