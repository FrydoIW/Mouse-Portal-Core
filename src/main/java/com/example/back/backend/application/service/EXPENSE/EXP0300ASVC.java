package com.example.back.backend.application.service.EXPENSE;


import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.expense.EXP0300AInput;
import com.example.back.backend.application.dto.expense.EXP0300AOutput;
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
public class EXP0300ASVC {

    private final DaoExpenseJpa expenseJpa;

    public class CtxSVC{
        EXP0300AInput input;
        EXP0300AOutput output;
    }

    public EXP0300AOutput execute(EXP0300AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new EXP0300AOutput();

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
