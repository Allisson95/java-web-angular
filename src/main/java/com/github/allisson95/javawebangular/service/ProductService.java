package com.github.allisson95.javawebangular.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.github.allisson95.javawebangular.model.Product;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductService {

    private final Map<UUID, Product> products;

    public ProductService() {
        this.products = new HashMap<>();

        Product tv = new Product("TV", BigDecimal.valueOf(2799));
        Product smartphone = new Product("Smartphone", BigDecimal.valueOf(2399));
        Product notebook = new Product("Notebook", BigDecimal.valueOf(3999));

        this.products.put(tv.getId(), tv);
        this.products.put(smartphone.getId(), smartphone);
        this.products.put(notebook.getId(), notebook);
    }

    public void save(final Product product) {
        products.putIfAbsent(product.getId(), product);
    }

    public Product find(final UUID productId) throws Exception {
        Product product = products.get(productId);
        if (product == null) {
            throw new Exception("Product not found");
        }
        return product;
    }

    public List<Product> findAll() {
        return List.copyOf(this.products.values());
    }

}
