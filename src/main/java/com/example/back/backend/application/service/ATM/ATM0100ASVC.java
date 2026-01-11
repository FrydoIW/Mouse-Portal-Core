package com.example.back.backend.application.service.ATM;

import com.example.back.backend.application.dto.atm.ATM0100AInput;
import com.example.back.backend.application.dto.atm.ATM0100AOutput;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.AtmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : ATM0100ASVC
 * @author   : dodocool
 * @description : Rekening creation service / Register 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ATM0100ASVC {

    private final AtmRepository atmRepository;

    private static class CtxSVC{

        ATM0100AInput input;
        ATM0100AOutput output;

    }

    public ATM0100AOutput execute(ATM0100AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new ATM0100AOutput();

        insertIntoRekening(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void putOutput(CtxSVC ctxSVC) {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("ALL DATA INSERTED");

    }


    private void insertIntoRekening(CtxSVC ctxSVC) throws Exception {

        GlobalModel globalModel = new GlobalModel();
        globalModel.setBranchIdRekening(ctxSVC.input.getBranchId());
        globalModel.setRekeningNm(ctxSVC.input.getRekeningNm());
        globalModel.setKtpNo(ctxSVC.input.getKtpNo());
        globalModel.setMotherNm(ctxSVC.input.getMotherNm());
        globalModel.setBirthPlace(ctxSVC.input.getBirthPlace());
        globalModel.setHomeAddr(ctxSVC.input.getHomeAddr());
        globalModel.setRt(ctxSVC.input.getRt());
        globalModel.setRw(ctxSVC.input.getRw());
        globalModel.setKelurahan(ctxSVC.input.getKelurahan());
        globalModel.setKecamatan(ctxSVC.input.getKecamatan());
        globalModel.setKabupaten(ctxSVC.input.getKabupaten());
        globalModel.setProvince(ctxSVC.input.getProvince());
        globalModel.setGenderRekening(ctxSVC.input.getGender());
        globalModel.setExpiredKtpDt(ctxSVC.input.getExpiredKtpDt());
        globalModel.setRekNo(ctxSVC.input.getRekNo());
        globalModel.setPinNo(ctxSVC.input.getPinNo());
        globalModel.setAtmNo(ctxSVC.input.getAtmNo());
        globalModel.setRekType(ctxSVC.input.getRekType());
        globalModel.setAtmExpiredDt(ctxSVC.input.getAtmExpiredDt());
        globalModel.setUserIdMobile(ctxSVC.input.getUserIdMobile());
        globalModel.setPassMBanking(ctxSVC.input.getPassMBanking());
        globalModel.setBankNm(ctxSVC.input.getBankNm());
        globalModel.setRemarkRekening(ctxSVC.input.getRemark());
        globalModel.setNoHp(ctxSVC.input.getNoHp());
        globalModel.setEmailRekening(ctxSVC.input.getEmail());
        globalModel.setPassEmail(ctxSVC.input.getPassEmail());
        globalModel.setMasaSewaBank(ctxSVC.input.getMasaSewaBank());
        globalModel.setSaldo(ctxSVC.input.getSaldo());
        globalModel.setBirthDt(ctxSVC.input.getBirthDt());
        globalModel.setStatus(ctxSVC.input.getStatus());

        log.debug("Global Input : [{}]",globalModel);

        atmRepository.insertNewAtm(globalModel);

    }

}
