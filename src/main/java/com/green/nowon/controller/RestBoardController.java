package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.green.nowon.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class RestBoardController {
	
	private final BoardService service;
	
	@GetMapping("/rest-boards")
	public String board() {
		return "board/rest-list";
	}
	
	//html페이지를 리턴데이터로 사용시 꼭 ModelAndView 객체를 쓰는것은아니다
	@GetMapping("/rest-boards/all")
	public String listAll(Model model) {
		//service.findAllProcess(model);
		return "board/list-data";
	}

}
