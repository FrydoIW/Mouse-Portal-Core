package com.example.back.backend.application.service.TKD;

import com.example.back.backend.application.dto.tkd.TKD0400AInput;
import com.example.back.backend.application.dto.tkd.TKD0400AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.UpdateRepository;
import com.example.back.backend.infrastructure.entity.Member;
import com.example.back.backend.infrastructure.jpa.DaoMemberJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : TKD0600ASVC
 * @author   : dodocool
 * @description :  Update Status / Update Status Process 🗿
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class TKD0400ASVC {

    private final DaoMemberJpa daoMemberJpa;
    private final UpdateRepository updateRepository;

    public class CtxSVC {

        TKD0400AInput input;
        TKD0400AOutput output;

        Member member;

    }

     public TKD0400AOutput execute(TKD0400AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new TKD0400AOutput();

        checkInput(ctxSVC);
        deleteProcess(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

     }

    private void checkInput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.member = daoMemberJpa.findById(ctxSVC.input.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.ACCOUNT_NOT_FOUND.getCode()));

    }

     private void deleteProcess(CtxSVC ctxSVC) throws Exception {

         GlobalModel globalModel = new GlobalModel();
         globalModel.setRefNo(ctxSVC.member.getRefNo());

         updateRepository.deleteUser(globalModel);

     }

     private void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("Success Delete Account");

     }

}
