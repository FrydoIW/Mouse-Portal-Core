package com.example.back.backend.application.service.ADMIN;

import com.example.back.backend.application.dto.admin.ADM0600AInput;
import com.example.back.backend.application.dto.admin.ADM0600AOutput;
import com.example.back.backend.infrastructure.entity.Admin;
import com.example.back.backend.infrastructure.jpa.DaoAdminJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @fileName : ADM0600ASVC
 * @author   : dodocool
 * @description : Get all Admin Data / GetAll 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ADM0600ASVC {

    private final DaoAdminJpa adminJpa;

    public static class CtxSVC {

        ADM0600AInput input;
        ADM0600AOutput output;

    }

    public ADM0600AOutput execute(ADM0600AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new ADM0600AOutput();

        getAllData(ctxSVC);

        return ctxSVC.output;

    }

    private void getAllData(CtxSVC ctxSVC){

        HashMap<String,Object> hashMap = new HashMap<>();
        int number = 1;

        Admin admin = adminJpa.findAdminByEmailVerified(ctxSVC.input.getEmail());

        hashMap.put("id", admin.getId());
        hashMap.put("name", admin.getName());
        hashMap.put("address", admin.getAddress());
        hashMap.put("gender", admin.getGender());
        hashMap.put("birthDt", admin.getBirthDt());
        hashMap.put("email", admin.getEmail());
        hashMap.put("profilePicture", admin.getProfilePicture());

        ctxSVC.output.setOutput(hashMap);

    }

}
