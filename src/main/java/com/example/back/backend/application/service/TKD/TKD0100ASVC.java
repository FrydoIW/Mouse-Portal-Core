package com.example.back.backend.application.service.TKD;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.tkd.TKD0100AInput;
import com.example.back.backend.application.dto.tkd.TKD0100AOutput;
import com.example.back.backend.common.Enum.AccountEnum;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.common.util.CreateRefNo;
import com.example.back.backend.common.util.GenereateSecretKey;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.RegisterRepository;
import com.example.back.backend.infrastructure.entity.Member;
import com.example.back.backend.infrastructure.jpa.DaoMemberJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @fileName : TKD0100ASVC
 * @author   : dodocool
 * @description : Member creation service / Register 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class TKD0100ASVC {

    private final DaoMemberJpa daoMemberJpa;
    private final RegisterRepository registerRepository;
    private final CreateRefNo createRef;

    private static class CtxSVC{

        TKD0100AInput input;
        TKD0100AOutput output;

    }

    public TKD0100AOutput execute (TKD0100AInput input) throws Exception {

        CtxSVC ctxSVC = new TKD0100ASVC.CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new TKD0100AOutput();

        checkExistingUser(ctxSVC);
        insertProcess(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void checkExistingUser(CtxSVC ctxSVC) throws BizException{

        Member member = daoMemberJpa.findUserByEmail(ctxSVC.input.getEmail());

        if (member != null){

            throw new BizException(SysErrCode.USER_FOUNT);

        }

    }

    private void insertProcess(CtxSVC ctxSVC) throws Exception{

        GlobalModel globInput = new GlobalModel();

        // MEMBER
        globInput.setRefNo(createRef.makeRef());
        globInput.setName(ctxSVC.input.getName());
        globInput.setAddress(ctxSVC.input.getAddress());
        globInput.setGender(ctxSVC.input.getGender());
        globInput.setEmail(ctxSVC.input.getEmail());

        // MEMBER_INFO
        globInput.setPosition(ctxSVC.input.getPosition());
        globInput.setJoinWorkDt(ctxSVC.input.getJoinWorkDt());
        globInput.setReligion(ctxSVC.input.getReligion());
        globInput.setWorkingWeb(ctxSVC.input.getWorkingWeb());
        globInput.setCuti(ctxSVC.input.getCuti());

        // PAYROLL
        globInput.setSalaryAmt(ctxSVC.input.getSalaryAmt());
        globInput.setRemark(ctxSVC.input.getRemark());
        globInput.setFoodAmount(ctxSVC.input.getFoodAmount());
        globInput.setThr(ctxSVC.input.getThr());
        globInput.setBonus(ctxSVC.input.getBonus());
        globInput.setNoRekening(ctxSVC.input.getNoRekening());
        globInput.setLastSalaryIncreaseDt(ctxSVC.input.getLastSalaryIncreaseDt());

        // BRANCH INFORMATION
        globInput.setBranchId(ctxSVC.input.getBranchId());

        // ADMIN ENTRY
        globInput.setAdminId(ctxSVC.input.getAdminEntry());

        log.debug("Global Input : [{}]",globInput);

        registerRepository.insertMember(globInput);

    }

    private void putOutput(CtxSVC ctxSVC) {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("ALL DATA INSERTED");

    }


}
