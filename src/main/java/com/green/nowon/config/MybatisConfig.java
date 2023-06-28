package com.green.nowon.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class MybatisConfig {
	
	@Bean
	DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}
		
	@Bean
	@ConfigurationProperties(prefix ="spring.datasource.hikari")
	HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	
	private final ApplicationContext ap;
	@Bean
	SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		System.out.println(">>>>:"+dataSource());
		factoryBean.setDataSource(dataSource());
		factoryBean.setConfiguration(mybatisConfiguration());
		
		String locationPattern="classpath:mapper/**/*-mapper.xml";
		Resource[] mapperLocations=ap.getResources(locationPattern);
		//Resource[] mapperLocations=new PathMatchingResourcePatternResolver().getResources(locationPattern);
		factoryBean.setMapperLocations(mapperLocations);//xml파일위치 설정
		
		return factoryBean.getObject();
	}
	
	
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	org.apache.ibatis.session.Configuration mybatisConfiguration() {
		return new org.apache.ibatis.session.Configuration();
	}

	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}

}
