package com.green.nowon.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.category.CategoryListDTO;
import com.green.nowon.domain.entity.CategoryEntity;
import com.green.nowon.domain.entity.CategoryEntityRepository;
import com.green.nowon.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceProcess implements CategoryService{

	private final CategoryEntityRepository repo;
	@Override
	public List<CategoryListDTO> getCategoryProcess() {
		return repo.findAllByParent(null).stream()
				.map(CategoryListDTO::new)
				.collect(Collectors.toList());
	}
	@Override
	public List<CategoryListDTO> getCategoryProcess(long no) {
		
		return repo.findAllByParent(repo.findById(no).orElse(null)).stream()
				.map(CategoryListDTO::new)
				.collect(Collectors.toList());
	}

}
