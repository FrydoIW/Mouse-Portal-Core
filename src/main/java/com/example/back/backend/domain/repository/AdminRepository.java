package com.example.back.backend.domain.repository;

import com.example.back.backend.domain.model.GlobalModel;

public interface AdminRepository {

    public void insertAdminRegis(GlobalModel globalModel) throws Exception;

    public void insertAdmin2FA(GlobalModel globalModel) throws Exception;

    public void insertTokenVerificaiton(GlobalModel globalModel) throws Exception;

    public void editPassword(GlobalModel globalModel) throws Exception;

    public void editAdminAccount(GlobalModel globalModel) throws Exception;

    public void editAdminEmail(GlobalModel globalModel) throws Exception;
}
