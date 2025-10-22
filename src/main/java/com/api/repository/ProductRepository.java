package com.api.repository;

import com.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
