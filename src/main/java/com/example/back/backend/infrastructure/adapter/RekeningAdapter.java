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

        rekModel.setNomorRekening(globalModel.getNomorRekening());
        rekModel.setBank(globalModel.getBank());
        rekModel.setOwner(globalModel.getOwner());
        rekModel.setAmount(globalModel.getAmount());

        Rekening rekening = RekeningMapper.toRekeningEntity(rekModel);

        rekening.setRegDt(LocalDate.now());
        rekening.setUpdDt(LocalDate.now());

        log.debug("Insert Rekening : [{}]",rekening);

        rekeningJpa.save(rekening);

    }
}
