package com.green.nowon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.dto.UserSaveDTO;
import com.green.nowon.domain.entity.MyUserEntity;
import com.green.nowon.domain.entity.MyUserEntityRepository;
import com.green.nowon.security.MyRole;

@SpringBootTest
class SpringSecurity6ProcApplicationTests {
	
	@Autowired
	private MyUserEntityRepository dao;
	@Autowired
	private PasswordEncoder pe;
	
	
	//@Test
	void contextLoads() {
		
		dao.save(MyUserEntity.builder()
				.email("admin")
				.pass(pe.encode("1234"))
				.nickName("관리자")
				.build()
				.addRole(MyRole.USER)
				.addRole(MyRole.ADMIN)
				.addRole(MyRole.SELLER)
				);
	}
	

}
