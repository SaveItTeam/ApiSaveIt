package com.api.Repository;

import com.api.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Address, Long> {
}
