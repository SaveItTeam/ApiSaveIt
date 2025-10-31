package com.api.repository;

import com.api.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    Enterprise findByEmail(String email);

    @Procedure(procedureName = "entry_register")
    void entryRegister(@Param("user_id") Integer userId);

    @Procedure(procedureName = "register_exit")
    void registerExit(@Param("p_usuario_id") Integer userId);
}
