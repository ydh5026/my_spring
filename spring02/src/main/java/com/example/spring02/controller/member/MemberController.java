package com.example.spring02.controller.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.member.dto.MemberDTO;
import com.example.spring02.service.member.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class); 
	
	@Inject
	MemberService memberService; 
	
	@RequestMapping("login.do")
	public String login() { 
		return "member/login"; 
	}
	
	@RequestMapping("/login_check.do")
	public ModelAndView login_check(@ModelAttribute MemberDTO dto, HttpSession session) {
		//로그인 성공=>이름이 넘어옴, 실패=>null이 넘어옴 
		String name = memberService.loginCheck(dto, session); 
		
		String user_id = dto.getUserid();
		
		logger.info("user name : " + name);
		logger.info("userid : " + user_id);
		
		ModelAndView mav = new ModelAndView(); 
		if(name != null) {  //로그인 성공하면 시작페이지로 이동
			mav.setViewName("home");
		}else {  //로그인 실패하면 로그인페이지로 이동
			mav.setViewName("member/login");
			mav.addObject("message", "error");
		}
		return mav;
	}
	
	@RequestMapping("/logout.do")
	public ModelAndView logout(ModelAndView mav, HttpSession session) { 
		memberService.logout(session);//세션 초기화 작업
		mav.setViewName("member/login");//이동할 페이지 
		mav.addObject("message", "logout"); //변수저장
		return mav; 
	}

}
