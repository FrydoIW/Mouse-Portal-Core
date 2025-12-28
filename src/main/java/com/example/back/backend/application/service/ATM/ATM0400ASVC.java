package com.example.back.backend.application.service.ATM;


import com.example.back.backend.application.dto.atm.ATM0400AInput;
import com.example.back.backend.application.dto.atm.ATM0400AOutput;
import com.example.back.backend.infrastructure.jpa.DaoRekeningJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : ATM0400ASVC
 * @author   : dodocool
 * @description : Delete Data Rekening / Delete Rekening 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ATM0400ASVC {

    private final DaoRekeningJpa rekeningJpa;

    private static class CtxSVC {

        ATM0400AInput input;
        ATM0400AOutput output;

    }

    public ATM0400AOutput execute(ATM0400AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new ATM0400AOutput();

        deleteProcess(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    public void deleteProcess(CtxSVC ctxSVC) throws Exception {

        rekeningJpa.deleteById(ctxSVC.input.getAtmId());

    }

    public void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("SUCCESS DELETE");

    }

}
