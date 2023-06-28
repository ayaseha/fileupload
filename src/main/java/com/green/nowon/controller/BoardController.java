package com.green.nowon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.nowon.domain.dto.BoardDTO;
import com.green.nowon.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService service;
	
	//게시글 읽기
	@GetMapping("/boards") // /boards?name=value&name2=value2
	//public String board(Model model,Integer page) {
	public String board(Model model,@RequestParam(defaultValue = "1") int page) {
		// int page : ?page=1 파라미터가 존재하지 않으면 null : wraper클래스 Integer page로 사용하면 해결
		// int page : ?page=1 파라미터가 존재하지 않으면 null : @RequestParam(defaultValue = "1") int page
	
		service.findAllProcess(page,model);
		return "board/list";
	}
	
	//글쓰기 페이지이동
	@GetMapping("/boards/new")
	public String board() {
		return "board/write";
	}
	
	//글쓰기 저장
	@PostMapping("/boards")
	public String board(BoardDTO dto, HttpServletRequest request) {
		service.saveProcess(dto, request);
		return "redirect:/boards";
	}
	
	//상세페이지 조회-data갖고 페이지로 이동
	@GetMapping("/boards/{no}")
	public String detail(@PathVariable long no, Model model) {
		service.detailProcess(no, model);
		return "board/detail";
	}
	
	
}
