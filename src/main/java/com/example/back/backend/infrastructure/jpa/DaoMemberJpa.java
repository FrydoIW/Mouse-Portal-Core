package com.example.back.backend.infrastructure.jpa;

import com.example.back.backend.infrastructure.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DaoMemberJpa extends JpaRepository<Member,String> {

    @Query(value = """
       SELECT * FROM MEMBER WHERE EMAIL = :email AND USER_MASTER = :userMaster
        """,
            nativeQuery = true)
    Member findByEmailAndUserMaster(@Param("email") String email, @Param("userMaster") String userMaster);

}
