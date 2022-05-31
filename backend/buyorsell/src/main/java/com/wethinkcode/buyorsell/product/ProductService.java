package com.wethinkcode.buyorsell.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        super();
        this.productRepo = productRepo;
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public Product getProduct(int id) {
        return productRepo.getById(id);
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();
        for(Product product : productRepo.findAll()) {
            products.add(product);
        }

        return products;
    }

    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }


}
