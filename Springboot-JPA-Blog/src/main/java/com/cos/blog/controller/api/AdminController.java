package com.cos.blog.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * 어드민 페이지 작성
 * -회원 목록 화면
 * -주문 정보 화면
 * -상품 등록 화면
 * */

@Controller
public class AdminController {

	@GetMapping("/admin/userlist")
	public String userList() {
		return "";
	}
	@GetMapping("/admin/orderlist")
	public String orderList() {
		return "";
	}
	@GetMapping("admin/addproduct")
	public String productMenu() {
		return "";
	}

}
