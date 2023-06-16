package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.ItemEntity;
import com.green.nowon.domain.entity.ItemImageEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemSaveDTO {
	
	private String title;
	private int price;
	private int stock;
	private String content;
	
	public ItemEntity toItemEntity() {
		return ItemEntity.builder()
				.title(title).price(price).stock(stock).content(content)
				.build();
	}
	
	//////////////////////////////////////////////
	private String bucketKey;
	private String orgName;
	

}
