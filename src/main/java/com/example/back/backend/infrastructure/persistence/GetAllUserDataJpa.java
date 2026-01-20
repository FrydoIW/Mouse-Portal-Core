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
               A.BRANCH_ID AS branchId,
               A.NAME AS name,
               A.ADDRESS AS address,
               A.GENDER AS gender,
               A.EMAIL AS email,
               -- MEMBER_INFO
               B.POSITION AS position,
               B.JOIN_WORK_DT AS joinWorkDt,
               B.RELIGION AS religion,
               B.WORKING_WEB AS workingWeb,
               B.CUTI AS cuti,
               -- PAYROLL
               C.SALARY_AMT AS salaryAmt,
               C.REMARK AS remark,
               C.FOOD_AMOUNT AS foodAmount,
               C.THR AS thr,
               C.BONUS AS bonus,
               C.NO_REKENING AS noRekening,
               C.LAST_SALARY_INCREASE_DT AS lastSalaryIncreaseDt
           FROM member A
                    JOIN member_info B ON A.REF_NO = B.REF_NO
                    JOIN payroll C ON A.REF_NO = C.REF_NO
           """;

        @SuppressWarnings("unchecked")
        List<Object[]> rows = em.createNativeQuery(sql).getResultList();

        List<HashMap<String, Object>> result = new ArrayList<>();

        for (Object[] row : rows) {
            HashMap<String, Object> map = new HashMap<>();
            // MEMBER fields
            map.put("refNo", row[0]);
            map.put("branchId", row[1]);
            map.put("name", row[2]);
            map.put("address", row[3]);
            map.put("gender", row[4]);
            map.put("email", row[5]);

            // MEMBER_INFO fields
            map.put("position", row[6]);
            map.put("joinWorkDt", row[7]);
            map.put("religion", row[8]);
            map.put("workingWeb", row[9]);
            map.put("cuti", row[10]);

            // PAYROLL fields
            map.put("salaryAmt", row[11]);
            map.put("remark", row[12]);
            map.put("foodAmount", row[13]);
            map.put("thr", row[14]);
            map.put("bonus", row[15]);
            map.put("noRekening", row[16]);
            map.put("lastSalaryIncreaseDt", row[17]);

            result.add(map);
        }

        return result;
    }

}
