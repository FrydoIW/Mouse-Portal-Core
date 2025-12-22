package com.example.back.backend.application.service.ATM;

import com.example.back.backend.application.dto.ATM0200AInput;
import com.example.back.backend.application.dto.ATM0200AOutput;
import com.example.back.backend.infrastructure.entity.Rekening;
import com.example.back.backend.infrastructure.jpa.DaoRekeningJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @fileName : ATM0200ASVC
 * @author   : dodocool
 * @description : Inquiry Rekening service / GetAllData 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ATM0200ASVC {

    private final DaoRekeningJpa rekeningJpa;

    private static class CtxSVC{
        ATM0200AInput input;
        ATM0200AOutput output;
    }

    public ATM0200AOutput execute(ATM0200AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new ATM0200AOutput();

        getAllAtmData(ctxSVC);

        return ctxSVC.output;

    }

    private void getAllAtmData(CtxSVC ctxSVC) throws Exception {

        List<Rekening> rekening;
        List<HashMap<String,Object>> outputMap = new ArrayList<>();

        rekening = rekeningJpa.findAll();

        for(Rekening n : rekening){

            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("id",n.getId());
            hashMap.put("nomorRekening",n.getNomorRekening());
            hashMap.put("bank",n.getBank());
            hashMap.put("owner",n.getOwner());
            hashMap.put("amount",n.getAmount());

            outputMap.add(hashMap);

        }

        ctxSVC.output.setResultList(outputMap);

    }



}
