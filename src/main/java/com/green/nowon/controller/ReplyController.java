package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.nowon.domain.dto.ReplyDTO;
import com.green.nowon.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReplyController {
	
	private final ReplyService service;
	
	@PostMapping("/boards/{bno}/replies")
	public String save(@PathVariable long bno, ReplyDTO dto) {
		service.saveProcess(bno,dto);
		return "redirect:/boards/{bno}";
	}
	
	//ajax-request
	@GetMapping("/boards/{boardNo}/replies")
	public String list(@PathVariable("boardNo") long bno,Model model) {
		service.listProcess(bno, model);
		return "reply/list"; //html이 응답해주는 데이터
	}
}
