package com.example.back.backend.application.service.EXPENSE;

import com.example.back.backend.application.dto.expense.EXP0100AInput;
import com.example.back.backend.application.dto.expense.EXP0100AOutput;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @fileName : TKD0700ASVC
 * @author   : dodocool
 * @description : Insert Expense / Insert Expense 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class EXP0100ASVC {

    private final ExpenseRepository expenseRepository;

    public class CtxSVC {

        EXP0100AInput input;
        EXP0100AOutput output;

    }

    public EXP0100AOutput execute(EXP0100AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new EXP0100AOutput();

        insertNewProcess(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;
    }

    private void insertNewProcess (CtxSVC ctxSVC) throws Exception {

        GlobalModel globalModel = new GlobalModel();

        globalModel.setExpenseName(ctxSVC.input.getExpenseName());
        globalModel.setCost(ctxSVC.input.getCost());
        globalModel.setDueDate(ctxSVC.input.getDueDate());
        globalModel.setMemo(ctxSVC.input.getMemo());
        globalModel.setBranchId(ctxSVC.input.getBranchId());

        // INSERT BY
        globalModel.setAdminId(ctxSVC.input.getAdminEntry());

        expenseRepository.insertNewExpense(globalModel);

    }

    private void putOutput (CtxSVC ctxSVC){

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("ALL DATA INSERTED");

    }

}
