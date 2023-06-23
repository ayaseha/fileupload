package com.green.nowon.domain.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@ToString(exclude = "itemList")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Table(name = "categoryex")
//@Entity
public class CategoryEntityEx {
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;//카테고리 번호
	private String name;//카테고리 이름
	
	//셀프조인 하기위한 관계설정 FK(parent_no) 생성 
	/*
	@ManyToOne
	private CategoryEntityEx parent;//상위카테고리
	*/
	//화목금 저녁수업 있어서 월수만 가능
	
	/*
	@ManyToMany
	private List<ItemEntity> itemList;
	//*/

}
