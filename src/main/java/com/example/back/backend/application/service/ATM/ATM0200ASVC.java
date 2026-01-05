package com.example.back.backend.application.service.ATM;

import com.example.back.backend.application.dto.atm.ATM0200AInput;
import com.example.back.backend.application.dto.atm.ATM0200AOutput;
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
        int number = 1;

        rekening = rekeningJpa.findAll();

        for(Rekening n : rekening){

            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("number", number);
            hashMap.put("id", n.getId());
            hashMap.put("branchId", n.getBranchId());
            hashMap.put("rekeningNm", n.getRekeningNm());
            hashMap.put("ktpNo", n.getKtpNo());
            hashMap.put("motherNm", n.getMotherNm());
            hashMap.put("birthPlace", n.getBirthPlace());
            hashMap.put("homeAddr", n.getHomeAddr());
            hashMap.put("rt", n.getRt());
            hashMap.put("rw", n.getRw());
            hashMap.put("kelurahan", n.getKelurahan());
            hashMap.put("kecamatan", n.getKecamatan());
            hashMap.put("kabupaten", n.getKabupaten());
            hashMap.put("province", n.getProvince());
            hashMap.put("gender", n.getGender());
            hashMap.put("expiredKtpDt", n.getExpiredKtpDt());
            hashMap.put("rekNo", n.getRekNo());
            hashMap.put("pinNo", n.getPinNo());
            hashMap.put("atmNo", n.getAtmNo());
            hashMap.put("rekType", n.getRekType());
            hashMap.put("atmExpiredDt", n.getAtmExpiredDt());
            hashMap.put("userIdMobile", n.getUserIdMobile());
            hashMap.put("passMBanking", n.getPassMBanking());
            hashMap.put("bankNm", n.getBankNm());
            hashMap.put("remark", n.getRemark());
            hashMap.put("noHp", n.getNoHp());
            hashMap.put("email", n.getEmail());
            hashMap.put("passEmail", n.getPassEmail());
            hashMap.put("masaSewaBank", n.getMasaSewaBank());
            hashMap.put("regDt", n.getRegDt());
            hashMap.put("updDt", n.getUpdDt());

            outputMap.add(hashMap);

            number++;

        }

        ctxSVC.output.setResultList(outputMap);

    }



}
