package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해 Bean에 등록해줌, .IOC를 해준다
@Service
public class UserService {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private BCryptPasswordEncoder encoder;
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//
//	@Transactional
//	public void 회원가입(User user) {
//		String rawPassword = user.getPassword();
//		String encPassword = encoder.encode(rawPassword);
//		user.setPassword(encPassword);
//		user.setRole(RoleType.USER);
//		userRepository.save(user);
//	}
//
//	@Transactional
//	public void 회원수정(User user) {
//		// 수정시에는 영속성 컨텍스트 user 오브젝트를 영속화 시키고, 영속화된 User 오브젝트를 수정
//		// select를 해서 User오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서!!
//		//영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날리기 때문
//		System.out.println("id: "+user.getId()+"userName: "+user.getUsername()+"userEmail"+user.getEmail());
//		User persistance = userRepository.findById(user.getId()).orElseThrow(()->
//		{
//			return new IllegalArgumentException("회원찾기 실패");
//		});
//
//		if(persistance.getOauth()==null || persistance.getOauth().equals("")) {
//			String rawPasssword = user.getPassword();
//			String encPassword = encoder.encode(rawPasssword);
//			persistance.setPassword(encPassword);
//			persistance.setEmail(user.getEmail());
//		}
//
//
//
//		//회원수정 함수 종료시 => 서비스종료시 => 트랜잭션 종료 => commit이 자동으로 된다.
//		//영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌
//	}
//
//	@Transactional
//	public User 회원찾기(String username) {
//		System.out.println("**************회원찾기 log**********************");
//		User user = userRepository.findByUsername(username).orElseGet(()->{
//			System.out.println("**************회원찾기 실패**********************");
//			return new User();
//		});
//		return user;
//	}
//
//
//	@Transactional(readOnly = true) //select 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
//	public User 로그인(User user) {//여러번 selete를 시도해도 같은 값을 유지할 수 있음
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}


}
