package com.green.nowon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
				.antMatchers("/images/**","/css/**","/js/**","/webjars/**","/favicon.ico*").permitAll()
				.antMatchers("/","/signup").permitAll()
				.antMatchers("/admin").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin(form->form
					.loginPage("/signin")
					.usernameParameter("email")
					.passwordParameter("pass")
					.defaultSuccessUrl("/",true)
					.permitAll()
					)
			.logout(logout->logout
					.logoutUrl("/logout") //csrf적용시 post로 요청해야 로그아웃가능
					.logoutSuccessUrl("/")
			)
			;
		return http.build();
	}

}
