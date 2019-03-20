package com.example.spring02.controller.shop;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring02.model.shop.dto.CartDTO;
import com.example.spring02.service.shop.CartService;

@Controller
@RequestMapping("/shop/cart/*")
public class CartController {
	private static final Logger logger = LoggerFactory.getLogger(CartService.class);  
	
	@Inject
	CartService cartService;
	
	@RequestMapping(value="insert.do") //세부적인 url mapping
	public String insert(@ModelAttribute CartDTO dto, HttpSession session) { 
		
		logger.info("cartcontroller start!!");
		
		String userid = (String)session.getAttribute("userid");  //로그인 여부를 체크하기 위해 세션에 저장된 아이디 확인
		
		if(userid == null) {  //로그인상태가 아니면 로그인 페이지로 이동
			return "redirect:/member/login.do";
		}
		dto.setUserid(userid);
		cartService.insert(dto);//장바구니 테이블에 저장됨
		
		return "redirect:/shop/list.do"; //장바구니 목록으로 이동
	}
}
