package com.example.Practice.Service;

import com.example.Practice.Model.Product;
import com.example.Practice.Repository.ProductReposistory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class ProductService {
    @Autowired
    private final ProductReposistory productReposistory;


    public ProductService(ProductReposistory productReposistory) {
        this.productReposistory = productReposistory;
    }

    public Product createProduct(Product product) {
        if (productReposistory.existsByName(product.getName())) {
            throw new IllegalArgumentException("Product already exists");
        }
        return productReposistory.save(product);
    }
    public List<Product>getAllProducts(){
        return productReposistory.findAll();
    }

    public Product getProductById(Long id){
        return productReposistory.findById(id).orElse(null);
    }

    public Product updateProduct(Long id,Product productDetails){
      Product product=getProductById(id);

      product.setName(productDetails.getName());
      product.setPrice(productDetails.getPrice());
      product.setQuantity(productDetails.getQuantity());

      return productReposistory.save(product);
    }

    public void deleteProduct(Long id){
        Product product=getProductById(id);
        productReposistory.delete(product);
    }

    public List<Product>searchByName(String name){
        return productReposistory.findByName(name);
    }
}
