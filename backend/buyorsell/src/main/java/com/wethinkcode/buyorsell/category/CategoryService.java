package com.wethinkcode.buyorsell.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        super();
        this.categoryRepo = categoryRepo;
    }

    public Category addToCategory(Category category) {
        return categoryRepo.save(category);
    }

    public Category getProductCategory(int id) {
        return categoryRepo.getById(id);
    }

    public List<Category> listCategories() {
        List<Category> categories = new ArrayList<Category>();
        for(Category category : categoryRepo.findAll()) {
            categories.add(category);
        }
        return categories;
    }

    public void removeCategory(int id) {
        categoryRepo.deleteById(id);
    }
    
}
