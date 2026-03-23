package com.example.back.backend.application.service.EMAIL;


import com.example.back.backend.application.dto.email.EMA0100AInput;
import com.example.back.backend.application.dto.email.EMA0100AOutput;
import com.example.back.backend.application.service.ADMIN.ADM0200ASVC;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @fileName : EMA0100ASVC
 * @author   : dodocool
 * @description : Email send / Email send 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class EMA0100ASVC {

    private final JavaMailSender javaMailSender;

    private final AdminRepository adminRepository;

    public static class CtxSVC {
        String token;
        EMA0100AInput input;
        EMA0100AOutput output;

    }

    public EMA0100AOutput execute(EMA0100AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new EMA0100AOutput();

        generateVerificationToken(ctxSVC);
        sendEmail(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void generateVerificationToken(CtxSVC ctxSVC) throws Exception {

        ctxSVC.token = UUID.randomUUID().toString();

        GlobalModel globalModel = new GlobalModel();

        globalModel.setAdminEmail(ctxSVC.input.getAdminEmail());
        globalModel.setVerificationToken(ctxSVC.token);
        globalModel.setEmailVerification(false);

        adminRepository.insertTokenVerificaiton(globalModel);

    }

    private void sendEmail(CtxSVC ctxSVC) throws Exception {

        String publicBaseUrl = System.getenv("PUBLIC_BASE_URL");
        log.debug("PUBLIC URL : [{}]",publicBaseUrl);
        if (publicBaseUrl == null || publicBaseUrl.isBlank()) {
            publicBaseUrl = "https://tikusdashboard.ichmarlabs.com";
        }
        publicBaseUrl = publicBaseUrl.replaceAll("/+$", "");

        String verificationUrl = publicBaseUrl + "/api/verifyMail/ema0200?token=" + ctxSVC.token;




        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nusabangun4@gmail.com");
        message.setTo(ctxSVC.input.getAdminEmail());
        message.setSubject("Email Verification");
        message.setText("Please verify your email by clicking the following link: " + verificationUrl);

        javaMailSender.send(message);

    }

    private void putOutput(CtxSVC ctxSVC){

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("SUCCESS SEND EMAIL");

    }



}
