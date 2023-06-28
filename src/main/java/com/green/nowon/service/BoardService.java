package com.green.nowon.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.BoardDTO;

public interface BoardService {

	void findAllProcess(int page, Model model);
	void findAllProcess(Integer page, Model model);

	void saveProcess(BoardDTO dto, HttpServletRequest request);
	void detailProcess(long no, Model model);

}
