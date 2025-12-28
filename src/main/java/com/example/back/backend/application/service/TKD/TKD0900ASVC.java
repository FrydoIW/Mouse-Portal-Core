package com.example.back.backend.application.service.TKD;


import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.tkd.TKD0900AInput;
import com.example.back.backend.application.dto.tkd.TKD0900AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.infrastructure.jpa.DaoExpenseJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : TKD0900ASVC
 * @author   : dodocool
 * @description : Delete Data Expense / Delete 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class TKD0900ASVC {

    private final DaoExpenseJpa expenseJpa;

    public class CtxSVC{
        TKD0900AInput input;
        TKD0900AOutput output;
    }

    public TKD0900AOutput execute(TKD0900AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new TKD0900AOutput();

        checkInput(ctxSVC);
        deleteProcess(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void checkInput(CtxSVC ctxSVC) throws BizException {

        if (ctxSVC.input.getId() == null) throw new BizException(SysErrCode.DEL_ID_NULL);

    }

    private void deleteProcess(CtxSVC ctxSVC) {

        expenseJpa.deleteById(ctxSVC.input.getId());

    }

    private void putOutput(CtxSVC ctxSVC) {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("ALL DATA DELETED");

    }

}
