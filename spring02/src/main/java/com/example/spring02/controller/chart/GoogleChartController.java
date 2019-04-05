package com.example.spring02.controller.chart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController //jason을 리턴하는 메소드가 있는 경우
@RequestMapping("/chart/*")
public class GoogleChartController {
	
	@RequestMapping("chart1.do")
	public ModelAndView chart1() {
		return new ModelAndView("chart/chart01");
	}
}
