package com.green.nowon.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dao.BoardMapper;
import com.green.nowon.domain.dao.ReplyMapper;
import com.green.nowon.domain.dto.BoardDTO;
import com.green.nowon.service.BoardService;
import com.green.nowon.util.ClientIP;
import com.green.nowon.util.PageData;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceProcess implements BoardService{

	private final BoardMapper mapper; //dao
	//private final ReplyMapper replyMapper;
	
	@Override
	public void findAllProcess(int page, Model model) {
		//int page=2;
		if(page<1)page=1;
		int limit=10; //한 화면에 보이는 개수
		int offset=(page-1)*limit; //0:1p, 5:2p
		
		List<BoardDTO> result=mapper.findAll(limit,offset);
		model.addAttribute("list", result);
		
		//총페이지수 = 총게시글수/limit 나머지가 있으면 +1
		///////////////////////////////////////////
		int rowCount=mapper.countAll();
		
		//PageData pageData=new PageData(page, limit, rowCount);
		
		model.addAttribute("pd", PageData.create(page, limit, rowCount, 6));
		
		/*
		int pageTotal=rowCount/limit;
		if(rowCount%limit >0 ) pageTotal++;//나머지가 존재하면 1증가
		
		int tot=pageTotal;
		model.addAttribute("tot", pageTotal);
		
		//페이지번호에 의해 변화하면 됨
		//p01~p10 1:  from=1, to=10
		//p11~p20 2:  from=11, to=20
		//p21~p30 3:  from=21, to=30
		int range=10;
		int rangeNo=page/range; //1~9:0 10:1
		if(page%range > 0)rangeNo++; //1~10 : 1
		
		int to=range*rangeNo;//페이지 마지막번호
		int from=to-range+1;//페이지시작번호
		
		if(to>tot)to=tot;
		
		model.addAttribute("from", from); 
		model.addAttribute("to", to);
		*/
		
	}

	@Override
	public void saveProcess(BoardDTO dto, HttpServletRequest request) {
		dto.setUserIp(ClientIP.getClientIP(request));
		
		mapper.save(dto);
		
	}

	@Override
	public void findAllProcess(Integer page, Model model) {
		if(page==null)page=1;
		int limit=5; //한 화면에 보이는 개수
		int offset=(page-1)*limit; //0:1p, 5:2p
		List<BoardDTO> result=mapper.findAll(limit,offset);
		model.addAttribute("list", result);
		
	}

	@Override
	public void detailProcess(long no, Model model) {
		model.addAttribute("detail", mapper.findByNo(no).orElseThrow());
		//model.addAttribute("replyList",replyMapper.findAllbyBoardNo(no));
	}

}
