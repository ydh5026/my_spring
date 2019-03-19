package com.example.spring03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.model.dto.GuestbookDTO;
import com.example.spring03.service.GuestbookService;

@Controller
public class GuestbookController {

	@Autowired
	GuestbookService guestbookService; 
	
	@RequestMapping("list.do")
	public ModelAndView main(ModelAndView mav) { 
		mav.setViewName("list");
		List<GuestbookDTO> list = guestbookService.list(); 
		System.out.println(list);
		mav.addObject("list", list);
		return mav; 
	}
	
}
