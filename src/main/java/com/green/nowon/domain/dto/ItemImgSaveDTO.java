package com.green.nowon.domain.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ItemImgSaveDTO {
	
	/////////////////////
	private String[] bucketKey;
	private String[] orgName;
	private String[] newName;
	
}
