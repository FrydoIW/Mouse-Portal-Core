package com.example.back.backend.common.util;

import com.example.back.backend.domain.model.RekeningModel;
import com.example.back.backend.infrastructure.entity.Rekening;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RekeningMapper {

    public static Rekening toRekeningEntity(RekeningModel model) {

        Rekening rekening = new Rekening();

        rekening.setBranchId(model.getBranchId());
        rekening.setRekeningNm(model.getRekeningNm());
        rekening.setKtpNo(model.getKtpNo());
        rekening.setMotherNm(model.getMotherNm());
        rekening.setBirthPlace(model.getBirthPlace());
        rekening.setHomeAddr(model.getHomeAddr());
        rekening.setRt(model.getRt());
        rekening.setRw(model.getRw());
        rekening.setKelurahan(model.getKelurahan());
        rekening.setKecamatan(model.getKecamatan());
        rekening.setKabupaten(model.getKabupaten());
        rekening.setProvince(model.getProvince());
        rekening.setGender(model.getGender());
        rekening.setExpiredKtpDt(model.getExpiredKtpDt());
        rekening.setRekNo(model.getRekNo());
        rekening.setPinNo(model.getPinNo());
        rekening.setAtmNo(model.getAtmNo());
        rekening.setRekType(model.getRekType());
        rekening.setAtmExpiredDt(model.getAtmExpiredDt());
        rekening.setUserIdMobile(model.getUserIdMobile());
        rekening.setPassMBanking(model.getPassMBanking());
        rekening.setBankNm(model.getBankNm());
        rekening.setRemark(model.getRemark());
        rekening.setNoHp(model.getNoHp());
        rekening.setEmail(model.getEmail());
        rekening.setPassEmail(model.getPassEmail());
        rekening.setMasaSewaBank(model.getMasaSewaBank());
        rekening.setSaldo(model.getSaldo());
        rekening.setBirthDt(model.getBirthDt());
        rekening.setStatus(model.getStatus());

        return rekening;


    }

}
