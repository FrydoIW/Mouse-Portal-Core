package com.example.back.backend.infrastructure.jpa;

import com.example.back.backend.infrastructure.entity.MemberCredential;
import com.example.back.backend.infrastructure.entity.PK.MemberCredentialPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoMemberCredentialJpa extends JpaRepository<MemberCredential, MemberCredentialPk> {


}
