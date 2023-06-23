package com.green.nowon.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.green.nowon.domain.entity.MyUserEntity;
import com.green.nowon.domain.entity.MyUserEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService{

	//사용자 DB에서 user존재하면 UserDetails 정보를 넘겨주면 됨
	private final MyUserEntityRepository dao;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//System.out.println(">>>>>: "+email);
		//MyUserEntity result=dao.findByEmailAndIsSocail(email,false).orElseThrow(()->new UsernameNotFoundException("Bad User"));
		
		return new MyUserDetails(dao.findByEmailAndIsSocial(email,false)
								.orElseThrow(()->new UsernameNotFoundException("Bad User")));
		//Set<MyRole>
		//Collection<? extends GrantedAuthority> authorities
	}

}
