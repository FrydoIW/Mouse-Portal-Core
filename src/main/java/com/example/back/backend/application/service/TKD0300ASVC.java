package com.example.back.backend.application.service;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.TKD0300AInput;
import com.example.back.backend.application.dto.TKD0300AOutput;
import com.example.back.backend.common.Enum.AccountEnum;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.AuthRepository;
import com.example.back.backend.infrastructure.entity.Member;
import com.example.back.backend.infrastructure.jpa.DaoMemberJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TKD0300ASVC {

    private final DaoMemberJpa daoMemberJpa;
    private final AuthRepository authRepository;

    private static class CtxSVC{

        TKD0300AInput input;
        TKD0300AOutput output;
        Member member;

    }

    public TKD0300AOutput execute(TKD0300AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new TKD0300AOutput();

        loadInputData(ctxSVC);
        passwordChanges(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private boolean isCheckEmail(CtxSVC ctx) {
        return ctx.input.getProcType()
                .equals(AccountEnum.AuthProcType.CHECK_EMAIL.getValue());
    }

    private void loadInputData(CtxSVC ctx) throws Exception {
        ctx.member = daoMemberJpa.findMemberByEmail(ctx.input.getEmail());

        if (ctx.member == null)
            throw new BizException(SysErrCode.EMAIL_NOT_FOUNT);
    }

    private void passwordChanges(CtxSVC ctx) throws Exception {
        if (isCheckEmail(ctx)) return;

        GlobalModel gm = new GlobalModel();
        gm.setRefNo(ctx.member.getRefNo());
        gm.setPasswordHash(ctx.input.getNewPassword());

        authRepository.updateAuth(gm);
    }

    private void putOutput(CtxSVC ctx) throws Exception {
        ctx.output.setStatus("00");
        ctx.output.setRemark(
                isCheckEmail(ctx) ? "Email Correct" : "Success Change Password"
        );

    }

}
