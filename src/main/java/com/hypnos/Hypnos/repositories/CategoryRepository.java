package com.hypnos.Hypnos.repositories;

import com.hypnos.Hypnos.models.Category;
import com.hypnos.Hypnos.models.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryById(Long id);
    List<Category> findCategoriesByNameContainingIgnoreCase(String name);
    List<Category> findCategoriesByDescriptionContainingIgnoreCase(String description);

}
