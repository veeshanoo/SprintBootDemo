package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.exception.MissingFieldsException;
import com.example.sprintbootdemo.model.Product;
import com.example.sprintbootdemo.service.ProductService;
import com.example.sprintbootdemo.service.TaxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    private final TaxService taxService;

    public ProductController(ProductService productService, TaxService taxService) {
        this.productService = productService;
        this.taxService = taxService;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @PostMapping("")
    public ResponseEntity<Product> saveNewProduct(@RequestBody Product product,
                                                  @RequestParam(required = false) Long taxId) {
        if (taxId != null) {
            product.setTax(taxService.getTax(taxId));
        }
        if (product.getProductDetails() == null) {
            throw new MissingFieldsException("Incomplete product data");
        }

        return ResponseEntity.ok().body(productService.saveNewProduct(product, taxId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody(required = false) Product product,
                                                 @PathVariable Long id,
                                                 @RequestParam(required = false) Long taxId) {
        if (product == null) {
            product = productService.getProduct(id);
        }

        if (taxId != null) {
            System.out.println(taxId);
            System.out.println(taxService.getTax(taxId).toString());
            product.setTax(taxService.getTax(taxId));
        }

        return ResponseEntity.ok().body(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
