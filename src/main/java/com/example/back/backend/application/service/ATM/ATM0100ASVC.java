package com.example.back.backend.application.service.ATM;

import com.example.back.backend.application.dto.atm.ATM0100AInput;
import com.example.back.backend.application.dto.atm.ATM0100AOutput;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.AtmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : ATM0100ASVC
 * @author   : dodocool
 * @description : Rekening creation service / Register 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ATM0100ASVC {

    private final AtmRepository atmRepository;

    private static class CtxSVC{

        ATM0100AInput input;
        ATM0100AOutput output;

    }

    public ATM0100AOutput execute(ATM0100AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new ATM0100AOutput();

        insertIntoRekening(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void putOutput(CtxSVC ctxSVC) {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("ALL DATA INSERTED");

    }


    private void insertIntoRekening(CtxSVC ctxSVC) throws Exception {

        GlobalModel globalModel = new GlobalModel();
        globalModel.setBranchId(ctxSVC.input.getBranchId());
        globalModel.setNomorRekening(ctxSVC.input.getNomorRekening());
        globalModel.setBank(ctxSVC.input.getBank());
        globalModel.setOwner(ctxSVC.input.getOwner());
        globalModel.setAmount(ctxSVC.input.getAmount());

        log.debug("Global Input : [{}]",globalModel);

        atmRepository.insertNewAtm(globalModel);

    }

}
