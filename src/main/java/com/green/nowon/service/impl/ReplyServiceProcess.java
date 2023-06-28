package com.green.nowon.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dao.ReplyMapper;
import com.green.nowon.domain.dto.ReplyDTO;
import com.green.nowon.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyServiceProcess implements ReplyService {
	
	private final ReplyMapper mapper;
	
	@Override
	public void saveProcess(long bno, ReplyDTO dto) {
		dto.setBoardNo(bno);
		mapper.save(dto);
		
	}

	@Override
	public void listProcess(long bno, Model model) {
		model.addAttribute("list", mapper.findAllbyBoardNo(bno));
		
	}

}
