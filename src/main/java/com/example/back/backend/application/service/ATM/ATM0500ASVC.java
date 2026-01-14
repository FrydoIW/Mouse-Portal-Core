package com.example.back.backend.application.service.ATM;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.atm.ATM0500AInput;
import com.example.back.backend.application.dto.atm.ATM0500AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.AtmRepository;
import com.example.back.backend.infrastructure.entity.Rekening;
import com.example.back.backend.infrastructure.jpa.DaoRekeningJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : ATM0400ASVC
 * @author   : dodocool
 * @description : Upload Rekening KTP / KTP 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ATM0500ASVC {

    private final DaoRekeningJpa rekeningJpa;
    private final AtmRepository atmRepository;

    private static class CtxSVC{

        ATM0500AInput input;
        ATM0500AOutput output;

    }

    public ATM0500AOutput execute(ATM0500AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new ATM0500AOutput();

        checkInput(ctxSVC);
        insertKtpPicture(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void checkInput(CtxSVC ctxSVC) throws BizException {

        Rekening rekening = rekeningJpa.findById(ctxSVC.input.getAtmId())
                .orElseThrow(() -> new BizException(
                        SysErrCode.ATM_ID_NULL
                ));

    }

    private void insertKtpPicture(CtxSVC ctxSVC) throws Exception {

        GlobalModel globalModel = new GlobalModel();
        globalModel.setRekeningId(ctxSVC.input.getAtmId());
        globalModel.setKtpImage(ctxSVC.input.getKtpImage());

        atmRepository.uploadKtp(globalModel);


    }

    private void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("SUCCESS UPLOAD KTP");

    }

}
