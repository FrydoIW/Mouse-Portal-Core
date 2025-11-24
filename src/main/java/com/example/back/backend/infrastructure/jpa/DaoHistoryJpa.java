package com.example.back.backend.infrastructure.jpa;

import com.example.back.backend.infrastructure.entity.History;
import com.example.back.backend.infrastructure.entity.PK.HistoryPk;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@PersistenceContext
public interface DaoHistoryJpa extends JpaRepository<History, HistoryPk> {

    @Query(value = """
        SELECT IFNULL(MAX(HIS_NO), 0) + 1 AS maxHisNo
        FROM HISTORY WHERE REF_NO = :refNo
        """,
            nativeQuery = true)
    int findMaxHisNo(@Param("refNo") String refNo);

    History findTopByRefNoOrderByHisNoDesc(String refNo);

}
