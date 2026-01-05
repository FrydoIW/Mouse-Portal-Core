package com.example.back.backend.infrastructure.adapter;

import com.example.back.backend.common.util.RekeningMapper;
import com.example.back.backend.domain.model.RekeningModel;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.AtmRepository;
import com.example.back.backend.infrastructure.entity.Rekening;
import com.example.back.backend.infrastructure.jpa.DaoRekeningJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Repository
@Slf4j
public class RekeningAdapter implements AtmRepository {

    private final DaoRekeningJpa rekeningJpa;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertNewAtm(GlobalModel globalModel) throws Exception {

        RekeningModel rekModel = new RekeningModel();

        rekModel.setBranchId(globalModel.getBranchIdRekening());
        rekModel.setRekeningNm(globalModel.getRekeningNm());
        rekModel.setKtpNo(globalModel.getKtpNo());
        rekModel.setMotherNm(globalModel.getMotherNm());
        rekModel.setBirthPlace(globalModel.getBirthPlace());
        rekModel.setHomeAddr(globalModel.getHomeAddr());
        rekModel.setRt(globalModel.getRt());
        rekModel.setRw(globalModel.getRw());
        rekModel.setKelurahan(globalModel.getKelurahan());
        rekModel.setKecamatan(globalModel.getKecamatan());
        rekModel.setKabupaten(globalModel.getKabupaten());
        rekModel.setProvince(globalModel.getProvince());
        rekModel.setGender(globalModel.getGenderRekening());
        rekModel.setExpiredKtpDt(globalModel.getExpiredKtpDt());
        rekModel.setRekNo(globalModel.getRekNo());
        rekModel.setPinNo(globalModel.getPinNo());
        rekModel.setAtmNo(globalModel.getAtmNo());
        rekModel.setRekType(globalModel.getRekType());
        rekModel.setAtmExpiredDt(globalModel.getAtmExpiredDt());
        rekModel.setUserIdMobile(globalModel.getUserIdMobile());
        rekModel.setPassMBanking(globalModel.getPassMBanking());
        rekModel.setBankNm(globalModel.getBankNm());
        rekModel.setRemark(globalModel.getRemarkRekening());
        rekModel.setNoHp(globalModel.getNoHp());
        rekModel.setEmail(globalModel.getEmailRekening());
        rekModel.setPassEmail(globalModel.getPassEmail());
        rekModel.setMasaSewaBank(globalModel.getMasaSewaBank());

        Rekening rekening = RekeningMapper.toRekeningEntity(rekModel);

        rekening.setRegDt(LocalDate.now());
        rekening.setUpdDt(LocalDate.now());

        log.debug("Insert Rekening : [{}]",rekening);

        rekeningJpa.save(rekening);

    }


    @Override
    public void editAtm(GlobalModel globalModel) throws Exception {

        Rekening rekening = rekeningJpa.findById(globalModel.getRekeningId()).orElseThrow(() -> new Exception("Data Not Found"));

        rekening.setBranchId(globalModel.getBranchIdRekening());
        rekening.setRekeningNm(globalModel.getRekeningNm());
        rekening.setKtpNo(globalModel.getKtpNo());
        rekening.setMotherNm(globalModel.getMotherNm());
        rekening.setBirthPlace(globalModel.getBirthPlace());
        rekening.setHomeAddr(globalModel.getHomeAddr());
        rekening.setRt(globalModel.getRt());
        rekening.setRw(globalModel.getRw());
        rekening.setKelurahan(globalModel.getKelurahan());
        rekening.setKecamatan(globalModel.getKecamatan());
        rekening.setKabupaten(globalModel.getKabupaten());
        rekening.setProvince(globalModel.getProvince());
        rekening.setGender(globalModel.getGenderRekening());
        rekening.setExpiredKtpDt(globalModel.getExpiredKtpDt());
        rekening.setRekNo(globalModel.getRekNo());
        rekening.setPinNo(globalModel.getPinNo());
        rekening.setAtmNo(globalModel.getAtmNo());
        rekening.setRekType(globalModel.getRekType());
        rekening.setAtmExpiredDt(globalModel.getAtmExpiredDt());
        rekening.setUserIdMobile(globalModel.getUserIdMobile());
        rekening.setPassMBanking(globalModel.getPassMBanking());
        rekening.setBankNm(globalModel.getBankNm());
        rekening.setRemark(globalModel.getRemarkRekening());
        rekening.setNoHp(globalModel.getNoHp());
        rekening.setEmail(globalModel.getEmailRekening());
        rekening.setPassEmail(globalModel.getPassEmail());
        rekening.setMasaSewaBank(globalModel.getMasaSewaBank());
        rekening.setUpdDt(LocalDate.now());

        log.debug("Update Rekening : [{}]",rekening);

        rekeningJpa.save(rekening);

    }
}
