package com.example.Practice.Repository;

import com.example.Practice.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductReposistory extends JpaRepository<Product,Long> {
    boolean existsByName(String name);

    List<Product> findByName(String name);
}
