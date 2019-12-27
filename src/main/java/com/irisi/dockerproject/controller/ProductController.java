package com.irisi.dockerproject.controller;

import com.irisi.dockerproject.dao.ProductRepository;
import com.irisi.dockerproject.exception.ResourceNotFoundException;
import com.irisi.dockerproject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @PostMapping("/products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable(value = "id") Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", productId));
    }

    @PutMapping("/products/{id}")
    public Product updateNote(@PathVariable(value = "id") Long productId,@Valid @RequestBody Product productDetails) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        product.setDesignation(productDetails.getDesignation());
        product.setPrix(productDetails.getPrix());

        Product updatedNote = productRepository.save(product);
        return updatedNote;
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        productRepository.delete(product);

        return ResponseEntity.ok().build();
    }
}
