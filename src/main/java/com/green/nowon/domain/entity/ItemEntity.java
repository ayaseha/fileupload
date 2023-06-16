package com.green.nowon.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@SequenceGenerator(name = "gen_item",sequenceName = "seq_item",
								initialValue = 1, allocationSize = 1)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item")
@Entity
public class ItemEntity extends BaseDateEntity {
	
	@Id
	@GeneratedValue(generator = "gen_item", strategy = GenerationType.SEQUENCE)
	private long no;
	
	@Column(nullable = false)
	private String title; //상품명
	@Column(nullable = false)
	private int price; //가격
	@Column(nullable = false)
	private int stock; //재고
	@Column(nullable = false)
	private String content; //상세
}
