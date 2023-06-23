package com.green.nowon.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.querydsl.binding.QuerydslPredicate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@SequenceGenerator(name = "gen_item_img",
		sequenceName = "seq_item_img", initialValue = 1, allocationSize = 1)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "itemImage")//my_user
@Entity
public class ItemImageEntity{
	
	@Id
	@GeneratedValue(generator = "gen_item_img", strategy = GenerationType.SEQUENCE)
	private long no;
	@Column(nullable = false)
	private String url; //s3경로
	@Column(nullable = false)
	private String orgName; //s3경로
	@Column(nullable = false)
	private String newName; //s3경로
	private String bucketKey; //파일명
	private boolean isList ; //false:content-img
	private boolean isDef ; //true:def-img
	
	@ManyToOne
	private ItemEntity item;
}
