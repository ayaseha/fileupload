package com.green.nowon.domain.dto.category;

import com.green.nowon.domain.entity.CategoryEntity;

import lombok.Getter;

@Getter
public class CategoryListDTO {

	private long no;//카테고리 번호
	private String name;//카테고리 이름
	
	public CategoryListDTO(CategoryEntity entity){
		no=entity.getNo();
		name=entity.getName();
	}
}
