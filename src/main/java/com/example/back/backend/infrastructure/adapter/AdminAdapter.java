package com.example.back.backend.infrastructure.adapter;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.common.Enum.SysErrCode;
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

    @Override
    public void insertAdmin2FA(GlobalModel globalModel) throws Exception {

        Admin admin = adminJpa.findAdminByEmail(globalModel.getAdminEmail());

        admin.setTwoFactorSecret(globalModel.getAdminTwoFactorSecret());
        admin.setUpdDt(LocalDate.now());

        log.debug("Admin : [{}]",admin);

        adminJpa.save(admin);

    }

    @Override
    public void insertTokenVerificaiton(GlobalModel globalModel) throws Exception {

        Admin admin = adminJpa.findAdminByEmail(globalModel.getAdminEmail());

        admin.setVerificationToken(globalModel.getVerificationToken());
        admin.setEmailVerification(globalModel.getEmailVerification());
        admin.setUpdDt(LocalDate.now());

        log.debug("Admin : [{}]",admin);

        adminJpa.save(admin);

    }

    @Override
    public void editPassword(GlobalModel globalModel) throws Exception {

        Admin admin = adminJpa.findAdminByEmailVerified(globalModel.getAdminEmail());

        admin.setPasswordHash(globalModel.getAdminPasswordHash());
        admin.setUpdDt(LocalDate.now());

        log.debug("Admin : [{}]",admin);

        adminJpa.save(admin);

    }

    @Override
    public void editAdminAccount(GlobalModel globalModel) throws Exception {

        Admin admin = adminJpa.findById(globalModel.getAdminId()).orElseThrow(() -> new BizException(SysErrCode.ACCOUNT_NOT_FOUND));

        admin.setName(globalModel.getAdminName());
        admin.setGender(globalModel.getAdminGender());
        admin.setBirthDt(globalModel.getAdminBrithDt());
        admin.setProfilePicture(globalModel.getAdminProfilePict());
        admin.setUpdDt(LocalDate.now());

        log.debug("Admin : [{}]",admin);

        adminJpa.save(admin);


    }
}
