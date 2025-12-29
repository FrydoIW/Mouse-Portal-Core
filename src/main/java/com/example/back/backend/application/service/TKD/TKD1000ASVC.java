package com.example.back.backend.application.service.TKD;

import com.example.back.backend.application.dto.tkd.TKD1000AInput;
import com.example.back.backend.application.dto.tkd.TKD1000AOutput;
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
public class TKD1000ASVC {

    private final DaoExpenseJpa daoExpenseJpa;

    public class CtxSVC {

        TKD1000AInput input;
        TKD1000AOutput output;

    }

    public TKD1000AOutput execute(TKD1000AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new TKD1000AOutput();

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

            outputMap.add(hashMap);

            number++;
        }

        ctxSVC.output.setResultList(outputMap);

    }

}
