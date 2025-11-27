package com.example.back.backend.infrastructure.adapter;

import com.example.back.backend.common.util.HistoryFactory;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.AuthRepository;
import com.example.back.backend.infrastructure.entity.MemberCredential;
import com.example.back.backend.infrastructure.jpa.DaoHistoryJpa;
import com.example.back.backend.infrastructure.jpa.DaoMemberCredentialJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@RequiredArgsConstructor
@Repository
@Slf4j
public class AuthAdapter implements AuthRepository {

    private final DaoMemberCredentialJpa daoCredentialJpa;

    private final DaoHistoryJpa daoHistoryJpa;

    private final HistoryFactory historyFactory;

    public void updateAuth(GlobalModel input) throws Exception {

        MemberCredential memberCredential = daoCredentialJpa.findByRefNo(input.getRefNo());

        memberCredential.setHisNo(daoHistoryJpa.findMaxHisNo(input.getRefNo()));
        memberCredential.setPasswordHash(input.getPasswordHash());
        memberCredential.setUpdDt(LocalDate.now());
        memberCredential.setUpdTm(LocalTime.now());

        log.debug("Update Member Credential : [{}]", memberCredential);

        daoCredentialJpa.save(memberCredential);

        historyFactory.insertIntoCredential(memberCredential);

    }


}
