package com.cos.blog.controller.api;

import com.cos.blog.config.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;



@RestController
@RequiredArgsConstructor
public class UserApiController {

	final int SUCCESS = 1;

	private final JwtManager jwtManager;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private BCryptPasswordEncoder encode;



	@PostMapping("/api/login")
	public ResponseDto<> LoginByToken(RequestBody){

	}
	@PostMapping("/api/refreshToken")
	public ResponseDto<> LoginByRefreshToken(RequestBody){


	}@PostMapping("/api/loginBy")
	public ResponseDto<> loginByIdPwd(RequestBody){

	}




	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("save호출");
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),SUCCESS);
	}

	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		userService.회원수정(user);
		//여기서는 트랜잭션이 종료되기 때문에 DB값은 변경되었지만
		// 세션값은 변경되지 않았기 때문에 직접 세션값을 변경해주어야함

		//세션 등록
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),SUCCESS);
	}

	/**전통방식의 login**/
	@PostMapping("/api/user/login")
	public  ResponseDto<Integer> login(@RequestBody User user,HttpSession session){
		System.out.println("login호출");
		User principal=userService.로그인(user);

//		if(principal!=null) {
//			System.out.println("principal 세션 설정");
//			session.setAttribute("principal", principal);
//		}
//		else {
//			System.out.println("principal 세션 설정 실패");
//		}

		return new ResponseDto<Integer>(HttpStatus.OK.value(),SUCCESS);

	}
}
