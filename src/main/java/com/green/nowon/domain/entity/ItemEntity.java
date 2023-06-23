package com.green.nowon.domain.entity;

import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


//@ToString(exclude = "categoryList")
@Getter
@SequenceGenerator(name = "gen_item",
		sequenceName = "seq_item", initialValue = 1001, allocationSize = 1)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item")//my_user
@Entity
public class ItemEntity extends BaseDateEntity{
	
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
	
	//*
	@Builder.Default
	@JoinTable(name = "item_category"
			,joinColumns = @JoinColumn(name="item_no")
			,inverseJoinColumns = @JoinColumn(name="category_no"))
	@ManyToMany//joinTable 이 생성되어 연결
	private List<CategoryEntity> categoryList=new Vector<>();
	//*/
	//일반적으로는 다대다는 실무에서 잘 적용하지 않습니다
	//특히나 연계테이블에 추가컬럼을 구성되는 경우는 연계테이블을 직접생성하고 1:M, M:1 관계구성해야합니다.
	//연계테이블은 fk 2개를 조합해서 pk를 구성할수 있으나 별도의pk를 만들어사용하면 더 효율적
	
	public ItemEntity addCategory(CategoryEntity category) {
		categoryList.add(category);
		return this;
	}
}
