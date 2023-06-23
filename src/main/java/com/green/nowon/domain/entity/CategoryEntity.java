package com.green.nowon.domain.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "category")
@Entity
public class CategoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;//카테고리 번호
	private String name;//카테고리 이름
	
	@ManyToOne(fetch = FetchType.LAZY)//parent_no FK
	private CategoryEntity parent;//상위카테고리 여부
	
	@ManyToMany(mappedBy = "categoryList")
	private List<ItemEntity> itemList;

}
