package com.example.back.backend.infrastructure.adapter;

import com.example.back.backend.common.util.ExpenseMapper;
import com.example.back.backend.domain.model.ExpenseModel;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.ExpenseRepository;
import com.example.back.backend.infrastructure.entity.Expense;
import com.example.back.backend.infrastructure.jpa.DaoExpenseJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@RequiredArgsConstructor
@Repository
@Slf4j
public class ExpenseAdapter implements ExpenseRepository {

    private final DaoExpenseJpa expenseJpa;
    @Override
    public void insertNewExpense(GlobalModel globalModel) throws Exception {

        ExpenseModel expenseModel = new ExpenseModel();

        expenseModel.setExpenseName(globalModel.getExpenseName());
        expenseModel.setCost(globalModel.getCost());
        expenseModel.setDueDate(globalModel.getDueDate());

        Expense expense = ExpenseMapper.toExpenseEntity(expenseModel);

        expense.setUpdDt(LocalDate.now());
        expense.setRegDt(LocalDate.now());

        log.debug("Insert Expense : [{}]",expense);

        expenseJpa.save(expense);

    }

    @Override
    public void editExpense(GlobalModel globalModel) throws Exception {

        Expense expense = expenseJpa.findById(globalModel.getExpenseId()).orElseThrow(() -> new Exception("Data not Found"));

        expense.setExpenseName(globalModel.getExpenseName());
        expense.setCost(globalModel.getCost());
        expense.setDueDate(globalModel.getDueDate());
        expense.setUpdDt(LocalDate.now());

        expenseJpa.save(expense);

    }
}
