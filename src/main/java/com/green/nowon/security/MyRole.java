package com.green.nowon.security;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MyRole {
	
	USER("ROLE_USER","일반유저"),
	SELLER("ROLE_SELLER","판매자"),
	ADMIN("ROLE_ADMIN","관리자");
	
	private final String roleName;
	private final String koName;
	public String roleName() {return roleName;}
	public String koName() {return koName;}
}
