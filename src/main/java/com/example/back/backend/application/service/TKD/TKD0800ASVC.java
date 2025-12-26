package com.example.back.backend.application.service.TKD;


import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.TKD0800AInput;
import com.example.back.backend.application.dto.TKD0800AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.ExpenseRepository;
import com.example.back.backend.infrastructure.entity.Expense;
import com.example.back.backend.infrastructure.jpa.DaoExpenseJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * @fileName : TKD0800ASVC
 * @author   : dodocool
 * @description : Edit Data Expense / Edit 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class TKD0800ASVC {

    private final DaoExpenseJpa expenseJpa;

    private final ExpenseRepository expenseRepository;

    public class CtxSVC {
        TKD0800AInput input;
        TKD0800AOutput output;
        Expense expense;

    }

    public TKD0800AOutput execute(TKD0800AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new TKD0800AOutput();

        checkInput(ctxSVC);
        editExpense(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;
    }

    private void checkInput(CtxSVC ctxSVC) throws Exception {

        if (ctxSVC.input.getId() == null){
            throw new BizException(SysErrCode.EXPENSE_ID_NULL);
        }

        ctxSVC.expense = expenseJpa.findById(ctxSVC.input.getId()).orElseThrow(() -> new Exception("Data not Found"));

    }

    private void editExpense(CtxSVC ctxSVC) throws Exception {

        GlobalModel globalModel = new GlobalModel();

        globalModel.setExpenseId(ctxSVC.input.getId());
        globalModel.setExpenseName(getValueOrDefault(ctxSVC.input.getExpenseName(),ctxSVC.expense.getExpenseName()));
        globalModel.setCost(getValueOrDefault(ctxSVC.input.getCost(),ctxSVC.expense.getCost()));
        globalModel.setDueDate(getValueOrDefault(ctxSVC.input.getDueDate(),ctxSVC.expense.getDueDate()));

        expenseRepository.editExpense(globalModel);
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

    private void putOutput(CtxSVC ctxSVC) {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("ALL DATA UPDATED");

    }

}
