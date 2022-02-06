package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML 파일)
//@Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpController {
	
	private static final String TAG="HttpControllerTest";
	
	
	public String lombokTest() {
		Member m = new Member.MemberBuilder().username("ssar").password("kakaroo").email("kiki").build();
		System.out.println(TAG+"getter :"+m.getId());
		m.setId(10);
		System.out.println(TAG+"getter :"+m.getId());
		return "lombok test 완료";
	}
	
	//인터넷 브라우저 요청은 무조건 get요청 밖에 할 수 없다.
	//http://localhost:8080/http/get
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get요청 id:"+m.getId()+","+m.getUsername()+","+m.getPassword(); 
	}
	
	//http://localhost:8080/http/post
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post요청 id:"+m.getId()+","+m.getUsername()+","+m.getPassword();
	}
	
	//http://localhost:8080/http/put
	@PutMapping("/http/put")
	public String putTest() {
		return "put요청";
	}
	
	//http://localhost:8080/http/delete
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete요청";
	}
}
