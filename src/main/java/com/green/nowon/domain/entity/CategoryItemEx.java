package com.green.nowon.domain.entity;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
//@Entity
//@IdClass(CategoryItemId.class)
public class CategoryItemEx {
	
	//@Id
	//@JoinColumn(name = "itemNo")
	//@ManyToOne
	private ItemEntity item;
	
	//@Id
	//@JoinColumn(name = "categoryNo")
	//@ManyToOne
	private CategoryEntityEx category;

}
