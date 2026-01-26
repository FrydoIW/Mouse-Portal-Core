package com.example.back.backend.infrastructure.jpa;

import com.example.back.backend.infrastructure.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DaoAdminJpa extends JpaRepository<Admin,Integer> {

    @Query(value = """
            SELECT * FROM admin WHERE EMAIL = :email
        """,
            nativeQuery = true)
    Admin findAdminByEmail(@Param("email") String email);

    @Query(value = """
            SELECT * FROM admin WHERE EMAIL = :email AND EMAIL_VERIFICATION = 1
        """,
            nativeQuery = true)
    Admin findAdminByEmailVerified(@Param("email") String email);

    @Query(value = """
            SELECT * FROM admin WHERE VERIFICATION_TOKEN = :verificationToken
        """,
            nativeQuery = true)
    Admin findByVerificationToken(@Param("verificationToken") String verificationToken);


}
