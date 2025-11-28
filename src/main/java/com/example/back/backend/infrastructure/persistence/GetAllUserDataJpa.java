package com.example.back.backend.infrastructure.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class GetAllUserDataJpa {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<HashMap<String,Object>> getAllUserInformation() {

        String sql = """
                SELECT
                    -- MEMBER
                    A.REF_NO       AS refNo,
                    A.NAME         AS name,
                    A.ADDRESS      AS address,
                    A.SEX          AS gender,
                    A.EMAIL        AS email,
                
                    -- MEMBER_INFO
                    B.POSITION     AS position,
                
                    -- PAYROLL
                    C.TRX_AMT      AS trxAmt
                
                FROM MEMBER A
                         JOIN MEMBER_INFO B
                              ON A.REF_NO = B.REF_NO
                         JOIN PAYROLL C
                              ON A.REF_NO = C.REF_NO
                WHERE B.STATUS = '00'
                """;

        @SuppressWarnings("unchecked")
        List<Object[]> rows = em.createNativeQuery(sql).getResultList();

        List<HashMap<String,Object>> result = new ArrayList<>();

        for (Object[] row : rows) {
            HashMap<String,Object> map = new HashMap<>();
            map.put("refNo",   row[0]);
            map.put("name",    row[1]);
            map.put("address", row[2]);
            map.put("gender",  row[3]);
            map.put("email",   row[4]);
            map.put("position",row[5]);
            map.put("trxAmt",  row[6]);

            result.add(map);
        }

        return result;

    }

}
