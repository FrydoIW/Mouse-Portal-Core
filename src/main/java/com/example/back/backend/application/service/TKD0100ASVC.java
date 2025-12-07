package com.example.back.backend.application.service;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.TKD0100AInput;
import com.example.back.backend.application.dto.TKD0100AOutput;
import com.example.back.backend.common.Enum.AccountEnum;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.common.util.CreateRefNo;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.RegisterRepository;
import com.example.back.backend.infrastructure.jpa.DaoMemberJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @fileName : TKD0100ASVC
 * @author   : dodocool
 * @description : Member creation service / Register 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class TKD0100ASVC {

    private final DaoMemberJpa daoMemberJpa;
    private final RegisterRepository registerRepository;
    private final CreateRefNo createRef;

    private static class CtxSVC{

        TKD0100AInput input;
        TKD0100AOutput output;

    }

    public TKD0100AOutput execute (TKD0100AInput input) throws Exception {

        CtxSVC ctxSVC = new TKD0100ASVC.CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new TKD0100AOutput();

        checkExistingUser(ctxSVC);
        insertProcess(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void insertProcess(CtxSVC ctxSVC) throws Exception{

        GlobalModel globInput = new GlobalModel();

        globInput.setRefNo(createRef.makeRef());
        globInput.setName(ctxSVC.input.getName());
        globInput.setAddress(ctxSVC.input.getAddress());
        globInput.setBirthDate(ctxSVC.input.getBirthDate());
        globInput.setGender(ctxSVC.input.getGender());
        globInput.setPosition(ctxSVC.input.getPosition());
        globInput.setStatus(AccountEnum.AccountStatus.ACTIVE.getValue());
        globInput.setEmail(ctxSVC.input.getEmail());
        globInput.setPasswordHash(ctxSVC.input.getPasswordCredential());
        globInput.setTrxAmt(ctxSVC.input.getSalaryAmount());
        globInput.setPayrollRemark("REGISTER");

        registerRepository.insertMember(globInput);

    }

    private void putOutput(CtxSVC ctxSVC) {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("ALL DATA INSERTED");

    }

    private void checkExistingUser(CtxSVC ctxSVC) throws BizException{

        if (daoMemberJpa.existsByEmail(ctxSVC.input.getEmail())){
            throw new BizException(SysErrCode.USER_FOUNT);
        }

    }

}
