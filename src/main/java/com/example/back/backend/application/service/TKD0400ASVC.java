package com.example.back.backend.application.service;

import com.example.back.backend.application.dto.TKD0400AInput;
import com.example.back.backend.application.dto.TKD0400AOutput;
import com.example.back.backend.infrastructure.persistence.GetAllUserDataJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @fileName : TKD0400ASVC
 * @author   : dodocool
 * @description : Get All Data / All users data 🗿
 */


@Slf4j
@RequiredArgsConstructor
@Service
public class TKD0400ASVC {

    private final GetAllUserDataJpa getAllUserDataJpa;

    private static class CtxSVC{

        TKD0400AInput input;
        TKD0400AOutput output;

    }

    public TKD0400AOutput execute(TKD0400AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new TKD0400AOutput();

        inquiryAllData(ctxSVC);

        return ctxSVC.output;

    }

    private void inquiryAllData(CtxSVC ctxSVC) throws Exception {

        List<HashMap<String,Object>> inquiryResult = new ArrayList<>();

        inquiryResult = getAllUserDataJpa.getAllUserInformation();

        ctxSVC.output.setResultList(inquiryResult);

    }

}
