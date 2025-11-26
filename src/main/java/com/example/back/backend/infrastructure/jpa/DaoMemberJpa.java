package com.example.back.backend.infrastructure.jpa;

import com.example.back.backend.infrastructure.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoMemberJpa extends JpaRepository<Member,String> {

    boolean existsByEmail(String email);

}
