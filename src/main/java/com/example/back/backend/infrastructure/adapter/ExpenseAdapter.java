package com.example.back.backend.infrastructure.adapter;

import com.example.back.backend.common.Enum.AccountEnum;
import com.example.back.backend.common.util.ExpenseMapper;
import com.example.back.backend.domain.model.ExpenseModel;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.ExpenseRepository;
import com.example.back.backend.infrastructure.entity.AuditLog;
import com.example.back.backend.infrastructure.entity.Expense;
import com.example.back.backend.infrastructure.jpa.DaoAdminJpa;
import com.example.back.backend.infrastructure.jpa.DaoAuditLog;
import com.example.back.backend.infrastructure.jpa.DaoExpenseJpa;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Repository
@Slf4j
public class ExpenseAdapter implements ExpenseRepository {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final DaoExpenseJpa expenseJpa;

    private final DaoAuditLog daoAuditLog;
    private final DaoAdminJpa daoAdminJpa;

    @Override
    @Transactional
    public void insertNewExpense(GlobalModel globalModel) throws Exception {

        ExpenseModel expenseModel = new ExpenseModel();
        expenseModel.setExpenseName(globalModel.getExpenseName());
        expenseModel.setCost(globalModel.getCost());
        expenseModel.setDueDate(globalModel.getDueDate());
        expenseModel.setBranchId(globalModel.getBranchId());
        expenseModel.setMemo(globalModel.getMemo());

        Expense expense = ExpenseMapper.toExpenseEntity(expenseModel);
        expense.setUpdDt(LocalDate.now());
        expense.setRegDt(LocalDate.now());

        log.debug("Insert Expense : [{}]", expense);

        Expense savedExpense = expenseJpa.saveAndFlush(expense);

        AuditLog audit = new AuditLog();
        audit.setTableName("expense");
        audit.setPkValue(String.valueOf(savedExpense.getId()));
        audit.setAction(AccountEnum.HistoryType.INSERT.getValue());
        audit.setChangedAt(LocalDateTime.now());
        audit.setChangedBy(globalModel.getAdminId());
        audit.setChangedByName(daoAdminJpa.getAdminName(globalModel.getAdminId()));
        audit.setWorkspaceId(globalModel.getWorkspaceId()); // kalau belum ada di GlobalModel, bilang ya
        String toJson = OBJECT_MAPPER.writeValueAsString(savedExpense);
        audit.setChangesJson("{\"from\":null,\"to\":" + toJson + "}");

        daoAuditLog.save(audit);
    }

    @Override
    @Transactional
    public void editExpense(GlobalModel globalModel) throws Exception {

        Expense expense = expenseJpa.findById(globalModel.getExpenseId())
                .orElseThrow(() -> new Exception("Data not Found"));

        String fromJson = OBJECT_MAPPER.writeValueAsString(expense);

        expense.setExpenseName(globalModel.getExpenseName());
        expense.setCost(globalModel.getCost());
        expense.setBranchId(globalModel.getBranchId());
        expense.setMemo(globalModel.getMemo());
        expense.setDueDate(globalModel.getDueDate());
        expense.setUpdDt(LocalDate.now());

        Expense savedExpense = expenseJpa.saveAndFlush(expense);

        String toJson = OBJECT_MAPPER.writeValueAsString(savedExpense);

        AuditLog audit = new AuditLog();
        audit.setTableName("expense");
        audit.setPkValue(String.valueOf(savedExpense.getId()));
        audit.setAction(AccountEnum.HistoryType.UPDATE.getValue());
        audit.setChangedAt(LocalDateTime.now());
        audit.setChangedBy(globalModel.getAdminId());
        audit.setChangedByName(daoAdminJpa.getAdminName(globalModel.getAdminId()));
        audit.setWorkspaceId(globalModel.getWorkspaceId());
        audit.setChangesJson("{\"from\":" + fromJson + ",\"to\":" + toJson + "}");

        daoAuditLog.save(audit);
    }
}