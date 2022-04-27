package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.dto.ProductRequestBodyDto;
import com.example.sprintbootdemo.exception.MissingFieldsException;
import com.example.sprintbootdemo.mapper.ProductMapper;
import com.example.sprintbootdemo.model.Product;
import com.example.sprintbootdemo.service.ProductService;
import com.example.sprintbootdemo.service.TaxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    private final TaxService taxService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, TaxService taxService, ProductMapper productMapper) {
        this.productService = productService;
        this.taxService = taxService;
        this.productMapper = productMapper;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) Long pageNr,
                                                        @RequestParam(required = false) String field,
                                                        @RequestParam(required = false) Boolean asc) {
        if (pageNr == null) {
            pageNr = 1L;
        }
        if (asc == null) {
            asc = true;
        }
        if (field == null) {
            field = "productName";
        }
        return ResponseEntity.ok().body(productService.getAllProducts(pageNr, field, asc).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @PostMapping("")
    public ResponseEntity<Product> saveNewProduct(@RequestBody ProductRequestBodyDto requestBody,
                                                  @RequestParam(required = false) Long taxId) {
        Product product = productMapper.productRequestBodyDtoToProduct(requestBody);

        if (taxId != null) {
            product.setTax(taxService.getTax(taxId));
        }
        if (product.getProductDetails() == null) {
            throw new MissingFieldsException("Incomplete product data");
        }

        Product savedProduct = productService.saveNewProduct(product, taxId);

        return ResponseEntity.created(URI.create("/api/product/" + savedProduct.getProductId())).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody(required = false) Product product,
                                                 @PathVariable Long id,
                                                 @RequestParam(required = false) Long taxId) {
        if (product == null) {
            product = productService.getProduct(id);
        }

        if (taxId != null) {
            product.setTax(taxService.getTax(taxId));
        }

        return ResponseEntity.ok().body(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
