package com.example.back.backend.infrastructure.jpa;

import com.example.back.backend.infrastructure.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DaoMemberInfoJpa extends JpaRepository<MemberInfo,String> {



}
