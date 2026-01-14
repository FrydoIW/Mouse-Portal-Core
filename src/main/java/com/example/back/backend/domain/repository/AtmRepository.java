package com.example.back.backend.domain.repository;

import com.example.back.backend.domain.model.GlobalModel;

public interface AtmRepository {

    void insertNewAtm(GlobalModel globalModel) throws Exception;

    void editAtm(GlobalModel globalModel) throws Exception;

    void uploadKtp(GlobalModel globalModel) throws Exception;

}
