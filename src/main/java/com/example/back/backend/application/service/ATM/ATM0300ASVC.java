package com.example.back.backend.application.service.ATM;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.atm.ATM0300AInput;
import com.example.back.backend.application.dto.atm.ATM0300AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.common.util.CompareUtil;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.AtmRepository;
import com.example.back.backend.infrastructure.entity.Rekening;
import com.example.back.backend.infrastructure.jpa.DaoRekeningJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : ATM0300ASVC
 * @author   : dodocool
 * @description : Edit Data Rekening / Edit 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ATM0300ASVC {

    private final DaoRekeningJpa rekeningJpa;

    private final AtmRepository atmRepository;

    private static class CtxSVC{

        ATM0300AInput input;
        ATM0300AOutput output;

        Rekening rek;

    }

    public ATM0300AOutput execute(ATM0300AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new ATM0300AOutput();

        checkInput(ctxSVC);
        editExistingData(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void checkInput(CtxSVC ctxSVC) throws Exception {

        if(ctxSVC.input.getId() == null) {
            throw new BizException(SysErrCode.ATM_ID_NULL);
        }

        ctxSVC.rek = rekeningJpa.findById(ctxSVC.input.getId())
                .orElseThrow(() -> new Exception("Data Not Found"));

    }

    private void editExistingData(CtxSVC ctxSVC) throws Exception {

        GlobalModel globalModel = new GlobalModel();

        globalModel.setRekeningId(ctxSVC.rek.getId());
        globalModel.setBranchIdRekening(CompareUtil.getValueOrDefault(ctxSVC.input.getBranchId(), ctxSVC.rek.getBranchId()));
        globalModel.setRekeningNm(CompareUtil.getValueOrDefault(ctxSVC.input.getRekeningNm(), ctxSVC.rek.getRekeningNm()));
        globalModel.setKtpNo(CompareUtil.getValueOrDefault(ctxSVC.input.getKtpNo(), ctxSVC.rek.getKtpNo()));
        globalModel.setMotherNm(CompareUtil.getValueOrDefault(ctxSVC.input.getMotherNm(), ctxSVC.rek.getMotherNm()));
        globalModel.setBirthPlace(CompareUtil.getValueOrDefault(ctxSVC.input.getBirthPlace(), ctxSVC.rek.getBirthPlace()));
        globalModel.setHomeAddr(CompareUtil.getValueOrDefault(ctxSVC.input.getHomeAddr(), ctxSVC.rek.getHomeAddr()));
        globalModel.setRt(CompareUtil.getValueOrDefault(ctxSVC.input.getRt(), ctxSVC.rek.getRt()));
        globalModel.setRw(CompareUtil.getValueOrDefault(ctxSVC.input.getRw(), ctxSVC.rek.getRw()));
        globalModel.setKelurahan(CompareUtil.getValueOrDefault(ctxSVC.input.getKelurahan(), ctxSVC.rek.getKelurahan()));
        globalModel.setKecamatan(CompareUtil.getValueOrDefault(ctxSVC.input.getKecamatan(), ctxSVC.rek.getKecamatan()));
        globalModel.setKabupaten(CompareUtil.getValueOrDefault(ctxSVC.input.getKabupaten(), ctxSVC.rek.getKabupaten()));
        globalModel.setProvince(CompareUtil.getValueOrDefault(ctxSVC.input.getProvince(), ctxSVC.rek.getProvince()));
        globalModel.setGenderRekening(CompareUtil.getValueOrDefault(ctxSVC.input.getGender(), ctxSVC.rek.getGender()));
        globalModel.setExpiredKtpDt(CompareUtil.getValueOrDefault(ctxSVC.input.getExpiredKtpDt(), ctxSVC.rek.getExpiredKtpDt()));
        globalModel.setRekNo(CompareUtil.getValueOrDefault(ctxSVC.input.getRekNo(), ctxSVC.rek.getRekNo()));
        globalModel.setPinNo(CompareUtil.getValueOrDefault(ctxSVC.input.getPinNo(), ctxSVC.rek.getPinNo()));
        globalModel.setAtmNo(CompareUtil.getValueOrDefault(ctxSVC.input.getAtmNo(), ctxSVC.rek.getAtmNo()));
        globalModel.setRekType(CompareUtil.getValueOrDefault(ctxSVC.input.getRekType(), ctxSVC.rek.getRekType()));
        globalModel.setAtmExpiredDt(CompareUtil.getValueOrDefault(ctxSVC.input.getAtmExpiredDt(), ctxSVC.rek.getAtmExpiredDt()));
        globalModel.setUserIdMobile(CompareUtil.getValueOrDefault(ctxSVC.input.getUserIdMobile(), ctxSVC.rek.getUserIdMobile()));
        globalModel.setPassMBanking(CompareUtil.getValueOrDefault(ctxSVC.input.getPassMBanking(), ctxSVC.rek.getPassMBanking()));
        globalModel.setBankNm(CompareUtil.getValueOrDefault(ctxSVC.input.getBankNm(), ctxSVC.rek.getBankNm()));
        globalModel.setRemarkRekening(CompareUtil.getValueOrDefault(ctxSVC.input.getRemark(), ctxSVC.rek.getRemark()));
        globalModel.setNoHp(CompareUtil.getValueOrDefault(ctxSVC.input.getNoHp(), ctxSVC.rek.getNoHp()));
        globalModel.setEmailRekening(CompareUtil.getValueOrDefault(ctxSVC.input.getEmail(), ctxSVC.rek.getEmail()));
        globalModel.setPassEmail(CompareUtil.getValueOrDefault(ctxSVC.input.getPassEmail(), ctxSVC.rek.getPassEmail()));
        globalModel.setMasaSewaBank(CompareUtil.getValueOrDefault(ctxSVC.input.getMasaSewaBank(), ctxSVC.rek.getMasaSewaBank()));

        atmRepository.editAtm(globalModel);

    }

    private void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("ALL DATA UPDATED");

    }

}
