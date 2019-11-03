package cn.com.gome.page.category;

import cn.com.gome.page.domain.CategoryEntity;

import java.io.Serializable;


public interface CategoryServiceProvider {

    CategoryEntity getCategoryEntity(String categoryId);

    CategoryEntity[] findAllCategoryEntity();

    Serializable transformToId(String fieldValue);

    boolean isRoot(String categoryId);
}
