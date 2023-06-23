package com.green.nowon.domain.entity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.nowon.domain.dto.category.CategoryListDTO;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long>{

	Optional<CategoryEntity> findByNameAndParentIsNull(String cateName);

	Optional<CategoryEntity> findByName(String cateName);
	
	List<CategoryEntity> findAllByParent(CategoryEntity parent);

	List<CategoryEntity> findAllByParent(long no);


}
