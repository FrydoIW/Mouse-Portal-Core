package com.example.back.backend.domain.repository;

import com.example.back.backend.domain.model.GlobalModel;

public interface RegisterRepository {

    void insertMember(GlobalModel member) throws Exception;

}
