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
    public List<HashMap<String, Object>> getAllUserInformation() {

        String sql = """
            SELECT
                -- MEMBER
                A.REF_NO AS refNo,
                A.NAME AS name,
                A.ADDRESS AS address,
                A.SEX AS gender,
                A.EMAIL AS email,
                A.BIRTH_DATE AS birthDt,
                -- MEMBER_INFO
                B.POSITION AS position,
                B.JOIN_WORK_DT AS joinWorkDt,
                B.RELIGION AS religion,
                B.WORKING_WEB AS workingWeb,
                -- PAYROLL
                C.TRX_AMT AS trxAmt,
                C.FOOD_AMOUNT AS foodAmt,
                C.THR AS thr,
                C.BONUS AS bonus,
                C.TIKET_AMT AS tiketAmt,
                C.TIKET_BUY_DT AS tiketBuyDt,
                C.NO_REKENING AS noRekening,
                C.LAST_SALARY_INCREASE_DT AS lastSalaryIncreaseDt
            FROM MEMBER A
            JOIN MEMBER_INFO B ON A.REF_NO = B.REF_NO
            JOIN PAYROLL C ON A.REF_NO = C.REF_NO
            WHERE B.STATUS = '00'
            """;

        @SuppressWarnings("unchecked")
        List<Object[]> rows = em.createNativeQuery(sql).getResultList();

        List<HashMap<String, Object>> result = new ArrayList<>();

        for (Object[] row : rows) {
            HashMap<String, Object> map = new HashMap<>();
            // MEMBER fields
            map.put("refNo", row[0]);
            map.put("name", row[1]);
            map.put("address", row[2]);
            map.put("gender", row[3]);
            map.put("email", row[4]);
            map.put("birthDate", row[5]);

            // MEMBER_INFO fields
            map.put("position", row[6]);
            map.put("joinWorkDt", row[7]);
            map.put("religion", row[8]);
            map.put("workingWeb", row[9]);

            // PAYROLL fields
            map.put("trxAmt", row[10]);
            map.put("foodAmt", row[11]);
            map.put("thr", row[12]);
            map.put("bonus", row[13]);
            map.put("tiketAmt", row[14]);
            map.put("tiketBuyDt", row[15]);
            map.put("noRekening", row[16]);
            map.put("lastSalaryIncreaseDt", row[17]);

            result.add(map);
        }

        return result;
    }

}
