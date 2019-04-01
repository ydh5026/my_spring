package com.example.spring02.controller.shop;

import java.io.File;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping("write.do")
	public String wtire() {
		return "shop/product_write";
	}
	
	@RequestMapping("insert.do")
	public String insert(ProductDTO dto) {
		String filename = "-";
		if(!dto.getFile1().isEmpty()) { //첨부파일이 존재하면
			filename = dto.getFile1().getOriginalFilename(); 
			String path="C:\\spring\\sts_pjt\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\spring02\\WEB-INF\\views\\images\\";
			try {
				new File(path).mkdir();
				dto.getFile1().transferTo(new File(path+filename));
			}catch (Exception e) {
				e.printStackTrace();
			}
			dto.setPicture_url(filename);
			productService.insertProduct(dto);
			return "redirect:/shop/list.do";
		}
		
		return null;
	}
	
	@RequestMapping("edit/{product_id}")
		public ModelAndView edit(@PathVariable("product_id") int product_id, ModelAndView mav) {
		mav.setViewName("/shop/product_edit"); //이동할 페이지 이름
		mav.addObject("dto", productService.detailProduct(product_id));
		return mav; //페이지 이동
	}
	
	@RequestMapping("update.do")
	public String update(ProductDTO dto) { 
		String filename="-";
		
		if(!dto.getFile1().isEmpty()) {//첨부파일이 있으면
			filename=dto.getFile1().getOriginalFilename(); 
			String path="C:\\spring\\sts_pjt\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\spring02\\WEB-INF\\views\\images\\";
			try {
				new File(path).mkdir(); 
				dto.getFile1().transferTo(new File(path+filename));
			}catch (Exception e) {
				e.printStackTrace();
			}
			dto.setPicture_url(filename);
		}else { 
			ProductDTO dto2 = productService.detailProduct(dto.getProduct_id());
			dto.setPicture_url(dto2.getPicture_url());
		}
		productService.updateProduct(dto);
		return "redirect:/shop/list.do";
	}
	
	@RequestMapping("delete.do")
	public String delete(@RequestParam int product_id) {
		String filename = productService.fileInfo(product_id); //파일의 정보를 가져오는 메소드 (첨부파일 이름)
		if(filename != null && !filename.equals("-")) {
			String path="C:\\spring\\sts_pjt\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\spring02\\WEB-INF\\views\\images\\";
			File f = new File(path+filename);
			if(f.exists()) { //파일이 존재하면 지움
				f.delete(); //파일삭제
			}
		}
		productService.deleteProduct(product_id);
		return "redirect:/shop/list.do";
	}
	
}
