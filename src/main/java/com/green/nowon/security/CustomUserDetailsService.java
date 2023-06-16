package com.green.nowon.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.green.nowon.domain.entity.MyUserEntityRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	//사용자 DB에서 user가 존재하면 UserDetails 정보를 넘겨주면됨
	private final MyUserEntityRepository dao;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//System.out.println(">>>>>>>"+email);
		return new MyUserDetails(dao.findByEmailAndIsSocial(email,false)
				.orElseThrow(()->new UsernameNotFoundException("존재하지않는 사용자"))
		);
	}

}
