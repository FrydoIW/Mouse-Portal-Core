package com.example.back.backend.application.service.TKD;

import com.example.back.backend.application.dto.TKD0700AInput;
import com.example.back.backend.application.dto.TKD0700AOutput;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TKD0700ASVC {

    private final ExpenseRepository expenseRepository;

    public class CtxSVC {

        TKD0700AInput input;
        TKD0700AOutput output;

    }

    public TKD0700AOutput execute(TKD0700AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new TKD0700AOutput();

        insertNewProcess(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;
    }

    private void insertNewProcess (CtxSVC ctxSVC) throws Exception {

        GlobalModel globalModel = new GlobalModel();

        globalModel.setExpenseName(ctxSVC.input.getExpenseName());
        globalModel.setCost(ctxSVC.input.getCost());
        globalModel.setDueDate(ctxSVC.input.getDueDate());

        expenseRepository.insertNewExpense(globalModel);

    }

    private void putOutput (CtxSVC ctxSVC){

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("ALL DATA INSERTED");

    }

}
