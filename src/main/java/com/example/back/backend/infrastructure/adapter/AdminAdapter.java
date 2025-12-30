package com.example.back.backend.infrastructure.adapter;

import com.example.back.backend.common.util.AdminMapper;
import com.example.back.backend.domain.model.AdminModel;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.AdminRepository;
import com.example.back.backend.infrastructure.entity.Admin;
import com.example.back.backend.infrastructure.jpa.DaoAdminJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@RequiredArgsConstructor
@Repository
@Slf4j
public class AdminAdapter implements AdminRepository {

    private final DaoAdminJpa adminJpa;

    @Override
    public void insertAdminRegis(GlobalModel globalModel) throws Exception {

        AdminModel adminModel = new AdminModel();

        adminModel.setName(globalModel.getAdminName());
        adminModel.setAddress(globalModel.getAdminAddress());
        adminModel.setBirthDt(globalModel.getAdminBrithDt());
        adminModel.setGender(globalModel.getAdminGender());
        adminModel.setEmail(globalModel.getAdminEmail());
        adminModel.setPasswordHash(globalModel.getAdminPasswordHash());

        Admin admin = AdminMapper.toAdminEntity(adminModel);

        admin.setUpdDt(LocalDate.now());
        admin.setRegDt(LocalDate.now());

        log.debug("Admin : [{}]",admin);

        adminJpa.save(admin);

    }


}
