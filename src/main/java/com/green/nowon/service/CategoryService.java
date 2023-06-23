package com.green.nowon.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.green.nowon.domain.dto.category.CategoryListDTO;

public interface CategoryService {

	List<CategoryListDTO> getCategoryProcess();

	List<CategoryListDTO> getCategoryProcess(@PathVariable long no);

}
	