package com.example.knittingback.converter;

import com.example.knittingback.entity.CategoryEntity;
import com.example.knittingback.model.Category;
import org.jetbrains.annotations.NotNull;

public class CategoryToCategoryEntity {
    public CategoryEntity convert (@NotNull Category category){
        CategoryEntity  categoryEntity= CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .image(category.getImage())
                .number(category.getNumber()).build();

        return categoryEntity;
    }

}
