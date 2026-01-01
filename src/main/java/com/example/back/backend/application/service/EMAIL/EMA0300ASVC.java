package com.example.back.backend.application.service.EMAIL;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.email.EMA0300AInput;
import com.example.back.backend.application.dto.email.EMA0300AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.AdminRepository;
import com.example.back.backend.infrastructure.entity.Admin;
import com.example.back.backend.infrastructure.jpa.DaoAdminJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @fileName : EMA0200ASVC
 * @author   : dodocool
 * @description : Email Change / Email Change 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class EMA0300ASVC {

    private final JavaMailSender javaMailSender;
    private final DaoAdminJpa adminJpa;
    private final AdminRepository adminRepository;

    public static class CtxSVC {
        String token;
        EMA0300AInput input;
        EMA0300AOutput output;

    }

    public EMA0300AOutput execute(EMA0300AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new EMA0300AOutput();

        editEmail(ctxSVC);
        sendEmail(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void editEmail(CtxSVC ctxSVC) throws Exception {

        Admin admin = adminJpa.findById(ctxSVC.input.getAdminId())
                .orElseThrow(() -> new BizException(SysErrCode.ACCOUNT_NOT_FOUND));

        GlobalModel globalModel = new GlobalModel();

        globalModel.setAdminEmail(ctxSVC.input.getNewAdminEmail());
        globalModel.setAdminOldEmail(admin.getEmail());
        globalModel.setVerificationToken(ctxSVC.token);
        globalModel.setEmailVerification(false);


        adminRepository.editAdminEmail(globalModel);

    }

    private void sendEmail(CtxSVC ctxSVC) throws Exception {

        String verificationUrl = "http://localhost:8080/verifyMail/ema0200?token=" + ctxSVC.token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sologankerfrydo99@gmail.com");
        message.setTo(ctxSVC.input.getNewAdminEmail());
        message.setSubject("Email Verification");
        message.setText("Please verify your email by clicking the following link: " + verificationUrl);

        javaMailSender.send(message);

    }

    private void putOutput(CtxSVC ctxSVC){

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("SUCCESS EDIT EMAIL");

    }

}
