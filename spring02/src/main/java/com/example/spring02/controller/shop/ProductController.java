package com.example.spring02.controller.shop;

import java.io.File;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.ProductDTO;
import com.example.spring02.service.shop.ProductService;

@Controller
@RequestMapping("/shop/*") //공통적인 url mapping
public class ProductController {

	@Inject //의존관계 주입(DI)
	ProductService productService; 
	
	@RequestMapping("list.do") 
	public ModelAndView list (ModelAndView mav) { 
		mav.setViewName("/shop/product_list"); //이동할 페이지 이름
		mav.addObject("list", productService.listproduct());
		return mav; //페이지 이동
	}
	
	@RequestMapping("/detail/{product_id}")
	public ModelAndView detail(@PathVariable("product_id") int product_id, ModelAndView mav) {
		mav.setViewName("/shop/product_detail");
		mav.addObject("dto", productService.detailProduct(product_id)); 
		return mav; 
	}
	
	@RequestMapping("insert.do")
	public String insert(ProductDTO dto) {
		String filename = "-";
		if(!dto.getFile1().isEmpty()) { //첨부파일이 존재하면
			filename = dto.getFile1().getOriginalFilename(); 
			String path="C:\\Users\\UM-D40-104\\git\\my_spring\\spring02\\src\\main\\webapp\\WEB-INF\\views\\images";
			try {
				new File(path).mkdir();
				dto.getFile1().transferTo(new File(path+filename));
			}catch (Exception e) {
				e.printStackTrace();
			}
			dto.setPicture_url(filename);
			productService.insertProduct(dto);
			return "redirect:/shop/product/list.do";
		}
		
		return null;
	}
	
	
}
