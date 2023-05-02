package com.cos.blog.controller;



import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//인즈이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
//그냥 주소가 /이면 index.jsp만 허용
//static 이하에 있는 /js/**, /css/**, /image/** 허용

@Controller
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Value(value = "${cos.key}")
	private String cosKey;

	@Autowired
	private UserService userService;


	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}

	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}


	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) { //Data를 return해주는 컨트롤러 함수

		RestTemplate rt= new RestTemplate();

		//http오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

		//httpBody 오브젝트 생성
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "263627d78c01c77316c66238b918bf02");
		params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
		params.add("code", code);

		//httpheader와 httpbody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
				new HttpEntity<>(params,headers);

		//http요청하기 -Post방식으로 -그리고 response변수의 응답을 받음
		ResponseEntity <String>response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
		);

		//POST방식으로 key=value데이터를 요청(카카오 쪽으로)
		//Retrofit2
		//OkHttp
		//RestTemplate


		//Gson,Json Simple, ObjectMapper
		ObjectMapper obMapper= new ObjectMapper();
		OAuthToken oauthToken =null;
		try {
			 oauthToken = obMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		System.out.println(":"+oauthToken.getAccess_token());


/**********************************************************************/
		RestTemplate rt2= new RestTemplate();

		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest =
				new HttpEntity<>(headers2);

		ResponseEntity <String>response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest,
				String.class
		);


		ObjectMapper obMapper2= new ObjectMapper();
		KakaoProfile kakaoProfile =null;
		try {
			kakaoProfile = obMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		//user오브젝트 :username, password, email
		System.out.println("****카카오아이디*****:"+kakaoProfile.getId());
		System.out.println("****카카오이메일*****:"+kakaoProfile.getKakao_account().getEmail());

		System.out.println("블로그 서버 유저네임: "+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
		System.out.println("블로그 서버 이메일: "+kakaoProfile.getKakao_account().getEmail());
	//	UUID garbagePassword = UUID.randomUUID();
		System.out.println("블로그 서버 패스워드: "+cosKey);

		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(cosKey)
				.oauth("kakao")
				.email(kakaoProfile.getKakao_account().getEmail()).build();


		System.out.println("회원가입 전"+kakaoUser.getUsername());
		System.out.println("회원가입 후"+kakaoUser.getPassword());
		//가입자 체크
		User originUser = userService.회원찾기(kakaoUser.getUsername());
		if(originUser.getUsername() ==null) {
			System.out.println("처음 회원입니다. 회원가입을 진행합니다");
			userService.회원가입(kakaoUser);
		}

		System.out.println(kakaoUser.getUsername());
		System.out.println(kakaoUser.getPassword());
		//로그인 처리
		System.out.println("자동로그인을 진행합니다.");
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);



		return "r/edirect:";
	}
}
