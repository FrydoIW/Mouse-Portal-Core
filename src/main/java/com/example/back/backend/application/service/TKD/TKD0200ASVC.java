package com.example.back.backend.application.service.TKD;

import com.example.back.backend.application.dto.tkd.TKD0200AInput;
import com.example.back.backend.application.dto.tkd.TKD0200AOutput;
import com.example.back.backend.infrastructure.persistence.GetAllUserDataJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class TKD0200ASVC {

    private final GetAllUserDataJpa getAllUserDataJpa;

    private static class CtxSVC{

        TKD0200AInput input;
        TKD0200AOutput output;

    }

    public TKD0200AOutput execute(TKD0200AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new TKD0200AOutput();

        inquiryAllData(ctxSVC);

        return ctxSVC.output;

    }

    private void inquiryAllData(CtxSVC ctxSVC) throws Exception {

        List<HashMap<String,Object>> inquiryResult;

        inquiryResult = getAllUserDataJpa.getAllUserInformation();

        ctxSVC.output.setResultList(inquiryResult);

    }

}
