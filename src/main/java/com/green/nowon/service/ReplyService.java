package com.green.nowon.service;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.ReplyDTO;

public interface ReplyService {

	void saveProcess(long bno, ReplyDTO dto);

	void listProcess(long bno, Model model);

}
