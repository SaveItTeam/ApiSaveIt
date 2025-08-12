package com.api.Repository;

import com.api.Model.Endereco;
import com.api.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
