package com.example.back.backend.application.service.EMAIL;

import com.example.back.backend.application.dto.email.EMA0400AInput;
import com.example.back.backend.application.dto.email.EMA0400AOutput;
import com.example.back.backend.infrastructure.entity.Admin;
import com.example.back.backend.infrastructure.jpa.DaoAdminJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : EMA0400ASVC
 * @author   : dodocool
 * @description : Email Check Verified / Email Verified 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class EMA0400ASVC {

    private final DaoAdminJpa adminJpa;

    public static class CtxSVC {

        EMA0400AInput input;
        EMA0400AOutput output;

    }

    public EMA0400AOutput execute(EMA0400AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new EMA0400AOutput();

        checkEmailVerified(ctxSVC);

        return ctxSVC.output;

    }

    private void checkEmailVerified(CtxSVC ctxSVC) {

        Admin admin = adminJpa.findAdminByEmailVerified(ctxSVC.input.getEmailAdmin());

        if (admin == null){

            ctxSVC.output.setStatus("09");
            ctxSVC.output.setRemark("Email is not verified yet");

        }else {

            ctxSVC.output.setStatus("00");
            ctxSVC.output.setRemark("Email is already verified");

        }

    }

}
