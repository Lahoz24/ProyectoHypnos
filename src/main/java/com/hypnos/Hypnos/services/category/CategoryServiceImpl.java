package com.hypnos.Hypnos.services.category;

import com.hypnos.Hypnos.models.Category;
import com.hypnos.Hypnos.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoriesByName(String name) {
        return categoryRepository.findCategoriesByNameContainsIgnoreCase(name);
    }

    @Override
    public List<Category> findCategoriesByDescription(String description) {
        return categoryRepository.findCategoriesByDescriptionContainsIgnoreCase(description);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Long id, Category category) {
        Category updated = this.findById(id);

        updated.setName(category.getName());
        updated.setDescription(category.getDescription());

        return categoryRepository.save(updated);
    }
}
