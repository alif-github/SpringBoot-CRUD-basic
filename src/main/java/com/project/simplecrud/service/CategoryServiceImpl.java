package com.project.simplecrud.service;

import com.project.simplecrud.model.Category;
import com.project.simplecrud.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void saveCategory(Category category) {
        synchronized (this) { //Critical section synchronized
            categoryRepository.saveCategory(category);
        }
    }

    @Override
    public List<Category> findAllCategory() {
        List<Category> categories = categoryRepository.findAllCategory();
        return categories;
    }

    @Override
    public List<Category> findAllCategorySave() {
        List<Category> categories = categoryRepository.findAllCategorySave();
        return categories;
    }

    @Override
    public Category findByIdCategory(long idCategory) {
        Category category1;
        try {
            category1 = categoryRepository.findByIdCategory(idCategory);
        } catch (Exception e) {
            category1 = null;
        }
        return category1;
    }

    @Override
    public Category findByNameCategory(String category) {
        Category category1;
        try {
            category1 = (Category) categoryRepository.findByNameCategory(category).get(0);
        } catch (Exception e) {
            category1 = null;
        }
        return category1;
    }

    @Override
    public Category findByIdAndNameCategory(long idCategory, String category) {
        Category category1;
        try {
            category1 = categoryRepository.findByIdAndNameCategory(idCategory, category);
        } catch (Exception e) {
            category1 = null;
        }
        return category1;
    }

    @Override
    public void updateDataCategory(long idCategory,Category category) {
        synchronized (this) {
            categoryRepository.updateDataCategory(idCategory,category);
        }
    }

    @Override
    public void deleteAllCategory() {
        categoryRepository.deleteAllCategory();
    }

    @Override
    public void deleteByIdCategory(long idCategory) {
        synchronized (this) {
            categoryRepository.deleteByIdCategory(idCategory);
        }
    }

    @Override
    public void deleteByNameCategory(String category) {
        synchronized (this) {
            categoryRepository.deleteByNameCategory(category);
        }
    }

    @Override
    public boolean isCategoryExist(Category category) {
        return categoryRepository.findByNameCategory(category.getCategory()).size() != 0;
    }
}
