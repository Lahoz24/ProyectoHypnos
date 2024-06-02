package com.hypnos.Hypnos.services.category;


import com.hypnos.Hypnos.models.Category;


import java.util.List;


public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);


    Category save(Category category);

    List<Category> findCategoriesByName(String name);

    List<Category> findCategoriesByDescription(String description);

    void deleteById(Long id);

    Category update(Long id, Category model);

    boolean existsByName(String name);
}

