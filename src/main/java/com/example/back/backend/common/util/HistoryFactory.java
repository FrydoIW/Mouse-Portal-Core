package com.example.back.backend.common.util;

import com.example.back.backend.common.Enum.AccountEnum;
import com.example.back.backend.infrastructure.entity.*;
import com.example.back.backend.infrastructure.jpa.DaoHistoryJpa;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Slf4j
@AllArgsConstructor
public class HistoryFactory {

    private final DaoHistoryJpa historyJpa;

    /* ===========================================================
       MEMBER
    ============================================================ */

    public void insertToMember(Member member) {

        // History pertama untuk refNo ini
        History h = new History();

        // PK
        h.setHisNo(member.getHisNo());
        h.setHisType(AccountEnum.HistoryType.MEMBER.getValue());
        h.setRefNo(member.getRefNo());

        // Data dari MEMBER
        h.setName(member.getName());
        h.setAddress(member.getAddress());
        h.setBirthDate(member.getBirthDate());
        h.setSex(member.getSex());
        h.setEmail(member.getEmail());
        h.setTypeDesc(AccountEnum.HistoryDesc.MEMBER.getValue());

        setAuditFields(h);
        historyJpa.save(h);

        log.debug("INSERT HISTORY MEMBER [{}]", h);
    }

    /* ===========================================================
       MEMBER INFO
    ============================================================ */

    public void insertIntoMemberInfo(MemberInfo info) {

        History base = historyJpa.findTopByRefNoOrderByHisNoDesc(info.getRefNo());

        History h = new History();

        // PK
        h.setHisNo(info.getHisNo());
        h.setHisType(AccountEnum.HistoryType.MEMBER_INFO.getValue());
        h.setRefNo(info.getRefNo());

        // Copy field dari history terakhir (kalau ada)
        copyBaseFields(h, base);

        // Override dengan data baru dari MEMBER_INFO
        h.setPosition(info.getPosition());
        h.setStatus(info.getStatus());
        h.setTypeDesc(AccountEnum.HistoryDesc.MEMBER_INFO.getValue());

        setAuditFields(h);
        historyJpa.save(h);

        log.debug("INSERT HISTORY MEMBER_INFO [{}]", h);
    }

    /* ===========================================================
       CREDENTIAL
    ============================================================ */

    public void insertIntoCredential(MemberCredential credential) {

        History base = historyJpa.findTopByRefNoOrderByHisNoDesc(credential.getRefNo());

        History h = new History();

        // PK
        h.setHisNo(credential.getHisNo());
        h.setHisType(AccountEnum.HistoryType.MEMBER_CREDENTIAL.getValue());
        h.setRefNo(credential.getRefNo());

        // Copy field lama
        copyBaseFields(h, base);

        // Override field credential
        h.setPosition(credential.getRole());
        h.setStatus(credential.getStatus());
        h.setPasswordHash(credential.getPasswordHash());
        h.setTwoFactorSecret(credential.getTwoFactorSecret());
        h.setTypeDesc(AccountEnum.HistoryDesc.MEMBER_CREDENTIAL.getValue());

        setAuditFields(h);
        historyJpa.save(h);

        log.debug("INSERT HISTORY MEMBER_CREDENTIAL [{}]", h);
    }

    /* ===========================================================
       PAYROLL
    ============================================================ */

    public void insertIntoPayroll(Payroll payroll) {

        History base = historyJpa.findTopByRefNoOrderByHisNoDesc(payroll.getRefNo());

        History h = new History();

        // PK
        h.setHisNo(payroll.getHisNo());
        h.setHisType(AccountEnum.HistoryType.PAYROLL.getValue());
        h.setRefNo(payroll.getRefNo());

        // Copy field lama
        copyBaseFields(h, base);

        // Override dengan data payroll
        h.setTrxAmt(payroll.getTrxAmt());
        h.setRemark(payroll.getRemark());
        h.setTypeDesc(AccountEnum.HistoryDesc.PAYROLL.getValue());

        setAuditFields(h);
        historyJpa.save(h);

        log.debug("INSERT HISTORY PAYROLL [{}]", h);
    }

    /* ===========================================================
       HELPER: COPY DATA DARI HISTORY TERAKHIR
    ============================================================ */

    private void copyBaseFields(History target, History base) {
        if (base == null) {
            return;
        }

        target.setName(base.getName());
        target.setAddress(base.getAddress());
        target.setBirthDate(base.getBirthDate());
        target.setSex(base.getSex());
        target.setEmail(base.getEmail());

        target.setTrxAmt(base.getTrxAmt());
        target.setRemark(base.getRemark());
        target.setStatus(base.getStatus());
        target.setPosition(base.getPosition());
        target.setPasswordHash(base.getPasswordHash());
        // typeDesc akan di-set spesifik di masing-masing fungsi
    }

    /* ===========================================================
       AUDIT
    ============================================================ */

    private void setAuditFields(History h) {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        h.setRegDt(today);
        h.setRegTm(now);
        h.setUpdDt(today);
        h.setUpdTm(now);
    }
}
