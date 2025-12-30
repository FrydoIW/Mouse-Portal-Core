package com.example.back.backend.infrastructure.jpa;

import com.example.back.backend.infrastructure.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DaoAdminJpa extends JpaRepository<Admin,Long> {

    @Query(value = """
            SELECT * FROM ADMIN WHERE EMAIL = :email
        """,
            nativeQuery = true)
    Admin findAdminByEmail(@Param("email") String email);

}
