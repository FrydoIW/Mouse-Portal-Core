package com.example.back.backend.domain.repository;

import com.example.back.backend.domain.model.GlobalModel;

public interface ExpenseRepository {

    void insertNewExpense(GlobalModel globalModel) throws Exception;

}
