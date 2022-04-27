package com.example.sprintbootdemo.service;

import com.example.sprintbootdemo.exception.ResourceNotFoundException;
import com.example.sprintbootdemo.model.Product;
import com.example.sprintbootdemo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.xml.bind.SchemaOutputResolver;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    public List<Product> getAllProducts() {
//        return productRepository.findAll().;
//    }

    public Page<Product> getAllProducts(Long pageNr, String field, Boolean order) {
        int pageSize = 2;
        Set<String> allowedFields = new HashSet<>() {{
           add("productName");
           add("productPrice");
        }};

        if (!allowedFields.contains(field)) {
            field = "productName";
        }

        Pageable params = PageRequest.of(pageNr.intValue() - 1, pageSize,
                order ? Sort.by(field).ascending() : Sort.by(field).descending());

        return productRepository.findAll(params);
    }

    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product with id: " + productId + " does not exist")
        );
    }

    public Product updateProduct(Long productId, Product product) {
        Product oldProduct = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product with id: " + productId + " does not exist")
        );

        if (product.getProductName() != null) {
            oldProduct.setProductName(product.getProductName());
        }
        if (product.getProductPrice() != null) {
            oldProduct.setProductPrice(product.getProductPrice());
        }
        if (product.getTax() != null) {
            oldProduct.setTax(product.getTax());
        }

        oldProduct.setProductPrice(product.getProductPrice());

        return productRepository.save(oldProduct);
    }

    public Product saveNewProduct(Product product, Long taxId) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        Product product = getProduct(productId);
        productRepository.delete(product);
    }
}
