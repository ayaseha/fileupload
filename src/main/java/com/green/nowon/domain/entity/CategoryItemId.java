package com.green.nowon.domain.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryItemId implements Serializable{
	private static final long serialVersionUID = 1L;
	private ItemEntity item;
	private CategoryEntityEx category;
	
	// equals() 메서드 구현
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryItemId that = (CategoryItemId) o;
        return Objects.equals(item, that.item) &&
                Objects.equals(category, that.category);
    }

    // hashCode() 메서드 구현
    @Override
    public int hashCode() {
        return Objects.hash(item, category);
    }

}
