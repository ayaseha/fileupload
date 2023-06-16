package com.green.nowon.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.green.nowon.security.MyRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@SequenceGenerator(name = "gen_user",sequenceName = "seq_user",
								initialValue = 1001, allocationSize = 1)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MyUser")
@Entity
public class MyUserEntity extends BaseDateEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_user")
	private long no;
	@Column(nullable = false,unique = true)
	private String email;
	private String pass;
	private String nickName;
	private boolean isSocial;
	
	@Builder.Default
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "role")
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<MyRole> roleSet=new HashSet<>();
	
	public MyUserEntity addRole(MyRole role) {
		roleSet.add(role);
		return this;
	}
}
