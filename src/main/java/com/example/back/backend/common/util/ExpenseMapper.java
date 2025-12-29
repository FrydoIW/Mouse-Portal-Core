package com.example.back.backend.common.util;

import com.example.back.backend.domain.model.ExpenseModel;
import com.example.back.backend.infrastructure.entity.Expense;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseMapper {

    public static Expense toExpenseEntity(ExpenseModel model) {

        Expense expense = new Expense();

        expense.setExpenseName(model.getExpenseName());
        expense.setCost(model.getCost());
        expense.setDueDate(model.getDueDate());
        expense.setBranchId(model.getBranchId());
        expense.setMemo(model.getMemo());

        return expense;

    }

}
