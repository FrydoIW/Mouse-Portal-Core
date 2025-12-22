package com.example.back.backend.application.service.ATM;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.ATM0300AInput;
import com.example.back.backend.application.dto.ATM0300AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.AtmRepository;
import com.example.back.backend.infrastructure.entity.Rekening;
import com.example.back.backend.infrastructure.jpa.DaoRekeningJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.ExceptionConstants;
import org.hibernate.tool.schema.spi.SqlScriptException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @fileName : ATM0300ASVC
 * @author   : dodocool
 * @description : Edit Data Rekening / Edit 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ATM0300ASVC {

    private final DaoRekeningJpa rekeningJpa;

    private final AtmRepository atmRepository;

    private static class CtxSVC{

        ATM0300AInput input;
        ATM0300AOutput output;

        Rekening rek;

    }

    public ATM0300AOutput execute(ATM0300AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new ATM0300AOutput();

        checkInput(ctxSVC);
        checkExistingData(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void checkInput(CtxSVC ctxSVC) throws Exception {

        if(ctxSVC.input.getId() == null) {
            throw new BizException(SysErrCode.ATM_ID_NULL);
        }

        ctxSVC.rek = rekeningJpa.findById(ctxSVC.input.getId())
                .orElseThrow(() -> new Exception("Data Not Found"));

    }

    private void checkExistingData(CtxSVC ctxSVC) throws Exception {

        GlobalModel globalModel = new GlobalModel();

        globalModel.setRekeningId(ctxSVC.input.getId());
        globalModel.setNomorRekening(getValueOrDefault(ctxSVC.input.getNomorRekening(),ctxSVC.rek.getNomorRekening()));
        globalModel.setBank(getValueOrDefault(ctxSVC.input.getBank(),ctxSVC.rek.getBank()));
        globalModel.setOwner(getValueOrDefault(ctxSVC.input.getOwner(),ctxSVC.rek.getOwner()));
        globalModel.setAmount(getValueOrDefault(ctxSVC.input.getAmount(),ctxSVC.rek.getAmount()));

        atmRepository.editAtm(globalModel);

    }

    private void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("ALL DATA UPDATED");

    }

    private <T> T getValueOrDefault(T inputValue, T defaultValue) {
        if (inputValue == null) {
            return defaultValue;
        }

        if (inputValue instanceof String) {
            String str = (String) inputValue;
            if (str.trim().isEmpty()) {
                return defaultValue;
            }
        }

        if (inputValue instanceof BigDecimal) {
            BigDecimal bd = (BigDecimal) inputValue;
            if (bd.compareTo(BigDecimal.ZERO) == 0) {
                return defaultValue;
            }
        }

        return inputValue;
    }

}
