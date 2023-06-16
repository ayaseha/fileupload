package com.green.nowon.domain.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.MyUserEntity;

import lombok.Setter;

@Setter
public class UserSaveDTO {
	
	private String email;
	private String pass;
	private String nickName;
	
	public MyUserEntity toEntity(PasswordEncoder pe) {
		return MyUserEntity.builder()
				.email(email).nickName(nickName)
				.pass(pe.encode(pass))
				.build();
	}

}
