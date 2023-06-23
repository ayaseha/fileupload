package com.green.nowon.domain.entity;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ImgRepositoryImpl implements CustomImgRepository<ItemImageEntity>{
	
	private final JPAQueryFactory queryFactory;
	
	
	

}
