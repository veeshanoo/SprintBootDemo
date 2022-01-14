package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.dto.ProductRequestBodyDto;
import com.example.sprintbootdemo.exception.MissingFieldsException;
import com.example.sprintbootdemo.mapper.ProductMapper;
import com.example.sprintbootdemo.model.Product;
import com.example.sprintbootdemo.model.Tax;
import com.example.sprintbootdemo.service.ProductService;
import com.example.sprintbootdemo.service.TaxService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@Validated
@Api(description = "Exposes product operations", tags = "Product")
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
    @ApiOperation(value = "Gets all products")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Gets product with ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @PostMapping("")
    @ApiOperation(value = "Creates new product")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 201, message = "Successful creation", response = Tax.class),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public ResponseEntity<Product> saveNewProduct(@Valid @RequestBody ProductRequestBodyDto requestBody,
                                                  @ApiParam(value = "Tax group", required = false) @RequestParam(required = false) Long taxId) {
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
    @ApiOperation(value = "Updates product with ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public ResponseEntity<Product> updateProduct(@RequestBody(required = false) Product product,
                                                 @PathVariable Long id,
                                                 @ApiParam(value = "Tax group", required = false) @RequestParam(required = false) Long taxId) {
        if (product == null) {
            product = productService.getProduct(id);
        }

        if (taxId != null) {
            product.setTax(taxService.getTax(taxId));
        }

        return ResponseEntity.ok().body(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes product with ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
