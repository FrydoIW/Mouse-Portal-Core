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

        rekening.setNomorRekening(model.getNomorRekening());
        rekening.setBank(model.getBank());
        rekening.setOwner(model.getOwner());
        rekening.setAmount(model.getAmount() == null ? BigDecimal.ZERO : model.getAmount());
        rekening.setBranchId(model.getBranchId());

        return rekening;


    }

}
