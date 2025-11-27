package com.example.back.backend.domain.repository;

import com.example.back.backend.domain.model.GlobalModel;

public interface AuthRepository {

    public void updateAuth(GlobalModel globalModel) throws Exception;

}
