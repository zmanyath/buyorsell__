package com.wethinkcode.buyorsell.category;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Category>> listCategories() {

        return new ResponseEntity<>(categoryService.listCategories(), HttpStatus.OK);
        
    }

    @PostMapping(value = "add-category")
    public Category addToCategory(@RequestParam String name,@RequestParam Date created_at,@RequestParam Date modified_at,@RequestParam Date deleted_at) {
        Category category = new Category(name,created_at, modified_at, deleted_at);
        categoryService.addToCategory(category);
        return category;
    }

    @DeleteMapping(value = "/category/{id}") 
    public void removeCategory(@PathVariable int id) { 

        categoryService.removeCategory(id);
    }

    

    
}
