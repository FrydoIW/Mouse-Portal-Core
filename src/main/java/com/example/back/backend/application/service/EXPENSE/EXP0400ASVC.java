package com.example.back.backend.application.service.EXPENSE;

import com.example.back.backend.application.dto.expense.EXP0400AInput;
import com.example.back.backend.application.dto.expense.EXP0400AOutput;
import com.example.back.backend.infrastructure.entity.Expense;
import com.example.back.backend.infrastructure.jpa.DaoExpenseJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @fileName : TKD0900ASVC
 * @author   : dodocool
 * @description : Get all Data Expense / getAllData 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class EXP0400ASVC {

    private final DaoExpenseJpa daoExpenseJpa;

    public class CtxSVC {

        EXP0400AInput input;
        EXP0400AOutput output;

    }

    public EXP0400AOutput execute(EXP0400AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new EXP0400AOutput();

        getAllData(ctxSVC);

        return ctxSVC.output;

    }

    private void getAllData(CtxSVC ctxSVC) throws Exception {

        List<Expense> expense;
        List<HashMap<String,Object>> outputMap = new ArrayList<>();
        int number = 1;

        expense = daoExpenseJpa.findAll();

        for (Expense e : expense) {

            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("number",number);
            hashMap.put("id",e.getId());
            hashMap.put("expenseName",e.getExpenseName());
            hashMap.put("cost",e.getCost());
            hashMap.put("dueDate",e.getDueDate());
            hashMap.put("memo",e.getMemo());
            hashMap.put("branchId",e.getBranchId());

            outputMap.add(hashMap);

            number++;
        }

        ctxSVC.output.setResultList(outputMap);

    }

}
