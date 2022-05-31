package com.wethinkcode.buyorsell.product;

import java.sql.Date;
import java.util.List;

import com.wethinkcode.buyorsell.category.Category;
import com.wethinkcode.buyorsell.category.CategoryService;
import com.wethinkcode.buyorsell.inventory.Inventory;
import com.wethinkcode.buyorsell.inventory.InventoryService;

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
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    InventoryService inventoryService;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Product>> getAllProducts() {

        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        
    }

    @GetMapping(value = "/product/category/{id}", produces = "application/json")
    public ResponseEntity<Category> getProductCategory(@PathVariable int id) {
        return new ResponseEntity<>(categoryService.getProductCategory(id), HttpStatus.OK);
    }

    @GetMapping(value = "/product/{id}", produces = "application/json")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @PostMapping(value = "/product/add-product/")
    public Product addProduct(@RequestParam String name,@RequestParam int SKU,@RequestParam float price,@RequestParam Date created_at,@RequestParam Date modified_at,@RequestParam Date deleted_at) {
        Product product = new Product(name,SKU, price, created_at, modified_at, deleted_at);
        productService.addProduct(product);
        return product;
    }

    @GetMapping(value = "/product/inventory/{id}",  produces = "application/json")
    public ResponseEntity<Inventory> getProductInventory(@PathVariable int id) {
        return new ResponseEntity<>(inventoryService.getProductInventory(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/product/{id}") 
    public void deleteProduct(@PathVariable int id) { 
        productService.deleteProduct(id);
    }

    @DeleteMapping(value = "/product/inventory/{id}")
    public void removeinventory(@PathVariable int id) { 
        inventoryService.removeInventory(id);
    }

    
}
