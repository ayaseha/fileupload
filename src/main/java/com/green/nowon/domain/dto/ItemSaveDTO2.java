package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.ItemEntity;
import com.green.nowon.domain.entity.ItemImageEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ItemSaveDTO2 {
	
	private String title;
	private int price;
	private int stock;
	private String content;
	
	public ItemEntity toItemEntity() {
		// TODO Auto-generated method stub
		return ItemEntity.builder()
				.title(title).price(price).stock(stock).content(content)
				.build();
	}
	
	
	
}
