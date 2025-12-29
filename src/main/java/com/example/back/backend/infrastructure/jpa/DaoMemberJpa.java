package com.example.back.backend.infrastructure.jpa;

import com.example.back.backend.infrastructure.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoMemberJpa extends JpaRepository<Member,String> {

    Member findMemberByEmail(String email);

    boolean existsByEmail(String email);

    boolean existByUserMaster(String email);
}
