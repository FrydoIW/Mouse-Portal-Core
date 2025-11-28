package com.example.back.backend.domain.repository;

import com.example.back.backend.domain.model.GlobalModel;

public interface UpdateRepository {

    public void updateData(GlobalModel globalModel) throws Exception;

}
