package com.green.nowon.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
public class QuerydslConfig {
	
	@PersistenceContext
	private EntityManager em;
	
	@Bean
	JPAQueryFactory jPAQueryFactory() {
		return new JPAQueryFactory(em);
	}
}
