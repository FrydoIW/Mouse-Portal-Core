package com.example.back.backend.common.util;

import com.example.back.backend.domain.model.AdminModel;
import com.example.back.backend.infrastructure.entity.Admin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminMapper {

    public static Admin toAdminEntity (AdminModel adminModel) {

        Admin admin = new Admin();

        admin.setName(adminModel.getName());
        admin.setAddress(adminModel.getAddress());
        admin.setBirthDt(adminModel.getBirthDt());
        admin.setGender(adminModel.getGender());
        admin.setEmail(adminModel.getEmail());
        admin.setPasswordHash(adminModel.getPasswordHash());
        admin.setTwoFactorSecret(adminModel.getTwoFactorSecret());
        admin.setProfilePicture(adminModel.getProfilePicture());

        return admin;

    }

}
