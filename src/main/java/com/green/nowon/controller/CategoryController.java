package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.green.nowon.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/common")
@Controller
public class CategoryController {

	
	private final CategoryService service;
	
	@ResponseBody // ModelAndView : html을 을 리턴
	@GetMapping("category")
	public ModelAndView category() {
		ModelAndView mv=new ModelAndView("category/list");
		mv.addObject("list", service.getCategoryProcess());
		return mv;
	}
	
//	@ResponseBody // ModelAndView : html을 을 리턴
//	@GetMapping("category-select")
//	public ModelAndView categorySelect() {
//		ModelAndView mv=new ModelAndView("category/list-select");
//		mv.addObject("list", service.getCategoryProcess());
//		return mv;
//	}
//	
	
	@ResponseBody // ModelAndView : html을 을 리턴
	@GetMapping("category-select/{no}")
	public ModelAndView categorySelect2(@PathVariable long no) {
		ModelAndView mv=new ModelAndView("category/list-select");
		mv.addObject("list", service.getCategoryProcess(no));
		return mv;
	}
}
