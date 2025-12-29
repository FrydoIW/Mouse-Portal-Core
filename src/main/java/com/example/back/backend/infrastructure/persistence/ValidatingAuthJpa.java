package com.example.back.backend.infrastructure.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ValidatingAuthJpa {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public boolean isValidLogin(String email, String password) {

        String sql = """
            SELECT EXISTS(
                SELECT 1
                FROM MEMBER A
                JOIN MEMBER_CREDENTIAL B ON A.REF_NO = B.REF_NO
                WHERE A.EMAIL = :email
                  AND B.PASSWORD_HASH = :password
                  AND B.STATUS = '00'
                  AND A.USER_MASTER = 'TRUE'
            )
        """;

        Object result = em.createNativeQuery(sql)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();

        return ((Number) result).intValue() == 1;
    }

    @Transactional(readOnly = true)
    public String getSecretKey(String email, String password) {

        String sql = """
        SELECT B.TWO_FACTOR_SECRET
        FROM MEMBER A
        JOIN MEMBER_CREDENTIAL B ON A.REF_NO = B.REF_NO
        WHERE A.EMAIL = :email
          AND B.PASSWORD_HASH = :password
          AND B.STATUS = '00'
    """;

        Object result = em.createNativeQuery(sql)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();

        return result != null ? result.toString() : null;
    }

}
