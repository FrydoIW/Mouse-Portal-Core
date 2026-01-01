package com.example.back.backend.application.service.ADMIN;

import com.example.back.backend.application.dto.admin.ADM0400AInput;
import com.example.back.backend.application.dto.admin.ADM0400AOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : ADM0400ASVC
 * @author   : dodocool
 * @description : Verification Password / Verification Pass 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ADM0400ASVC {

    public static class CtxSVC {
        ADM0400AInput input;
        ADM0400AOutput output;

    }

    public ADM0400AOutput execute(ADM0400AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new ADM0400AOutput();

        return ctxSVC.output;

    }

}
