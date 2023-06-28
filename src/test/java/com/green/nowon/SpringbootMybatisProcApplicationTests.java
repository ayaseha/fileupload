package com.green.nowon;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.green.nowon.domain.dao.BoardMapper;
import com.green.nowon.domain.dto.BoardDTO;
import com.green.nowon.util.ClientIP;

@SpringBootTest
class SpringbootMybatisProcApplicationTests {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private BoardMapper mapper;
	
	//@Test
	void ipTest() {
		String ip=ClientIP.getClientIP(request);
		System.out.println(ip);
	}
	
	@Test
	@Transactional
	@Rollback
	void contextLoads() {
		
		for(int i=1; i<=100; i++) {
			
			BoardDTO dto=new BoardDTO();
			dto.setSubject("제목 테스트"+i);
			dto.setContent("내용 테스트"+i);
			dto.setWriter("test"+(i%5+1)+"@test");
			dto.setUserIp(ClientIP.getClientIP(request));
			mapper.save(dto);
		}
		
	}

}
