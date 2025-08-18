package com.api.Repository;

import com.api.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Product, Long> {
}
