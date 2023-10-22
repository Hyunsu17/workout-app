package com.cos.blog.test;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyController {

	@Autowired// 의존성 주입(DI)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			return "there is no record corresponding this id: "+id;
		}
		
		return "삭제되었습니다.";
	}
	
	
	//save함수는 id를 전달하지 않으며 insert를 해주고
	//save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	//해당 id에대한 데이터가 없으면 insert를 함
	@Transactional // 함수 종료시 자동 커밋됨 
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) {
		System.out.println("id: "+id);
		System.out.println("Email: "+requestUser.getEmail());
		System.out.println("password: "+requestUser.getPassword());
		
		requestUser.setId(id);
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("There is no user record corresponding to indentifier: "+id) ;
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(requestUser);
		
		//더티 체킹
		return user;
		
	}
	
	//{id}주소로 파라미터를 전달받을 수 있음
	//http://localhost:8080/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//optional로 User객체를 감싸서 가져온후 판단해서 return
	    User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("There is no user record corresponding to indentifier: "+id);
		});
	    // 요청: 웹브라우저
	    //user 객체 = 자바 오브젝트
	    //변환 ( 웹브라우저가 이해할 수 있는 데이터) -> json (Gson라이브러리)
	    //스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
	    // 만약 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서 
	    // user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한페이지당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User>pageList(@PageableDefault(size=2,sort="id",direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		if(pagingUser.isLast()) {
			
		}
		List<User> users = pagingUser.getContent();
		return users;
		
	}
	
	// http://localhost:8080/blog/dummy/user
	// http의 bodydp username, password, email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(User user) { // key=value (약속된 규칙) form 형태로 오는 것은  key value의 형태로 오는데 스프링이 함수의 파라미터로 파싱해줌 
		System.out.println("username:"+user.getId());
		System.out.println("password:"+user.getPassword());
		System.out.println("email:"+user.getEmail());
		
	
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	
	}
}
