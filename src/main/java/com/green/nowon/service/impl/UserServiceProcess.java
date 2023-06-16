package com.green.nowon.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.UserSaveDTO;
import com.green.nowon.domain.entity.MyUserEntityRepository;
import com.green.nowon.security.MyRole;
import com.green.nowon.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceProcess implements UserService{
	
	private final MyUserEntityRepository dao;
	private final PasswordEncoder pe;
	
	@Override
	public void saveProcess(UserSaveDTO dto) {
		dao.save(dto.toEntity(pe).addRole(MyRole.USER));
	}

}
