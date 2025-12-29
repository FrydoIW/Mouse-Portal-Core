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
    private final GenereateSecretKey genereateSecretKey;

    private static class CtxSVC{

        TKD0100AInput input;
        TKD0100AOutput output;

        String qrBase64;
        String secret;

    }

    public TKD0100AOutput execute (TKD0100AInput input) throws Exception {

        CtxSVC ctxSVC = new TKD0100ASVC.CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new TKD0100AOutput();

        checkExistingUser(ctxSVC);
        generateTwoFactorSecret(ctxSVC);
        insertProcess(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void insertProcess(CtxSVC ctxSVC) throws Exception{

        GlobalModel globInput = new GlobalModel();

        globInput.setRefNo(createRef.makeRef());
        globInput.setName(ctxSVC.input.getName());
        globInput.setAddress(ctxSVC.input.getAddress());
        globInput.setBirthDate(ctxSVC.input.getBirthDate());
        globInput.setGender(ctxSVC.input.getGender());
        globInput.setPosition(ctxSVC.input.getPosition());
        globInput.setStatus(AccountEnum.AccountStatus.ACTIVE.getValue());
        globInput.setEmail(ctxSVC.input.getEmail());
        globInput.setPasswordHash(ctxSVC.input.getPasswordCredential());
        globInput.setTrxAmt(ctxSVC.input.getSalaryAmount());
        globInput.setTwoFactorSecret(ctxSVC.secret);
        globInput.setJoinWorkDt(ctxSVC.input.getJoinWorkDt());
        globInput.setReligion(ctxSVC.input.getReligion());
        globInput.setWorkingWeb(ctxSVC.input.getWorkingWeb());
        globInput.setFoodAmount(ctxSVC.input.getFoodAmount());
        globInput.setThr(ctxSVC.input.getThr());
        globInput.setBonus(ctxSVC.input.getBonus());
        globInput.setTicketAmt(ctxSVC.input.getTicketAmt());
        globInput.setTicketBuyDt(ctxSVC.input.getTicketBuyDt());
        globInput.setNoRekening(ctxSVC.input.getNoRekening());
        globInput.setLastSalaryIncreaseDt(ctxSVC.input.getLastSalaryIncreaseDt());
        globInput.setBranchId(ctxSVC.input.getBranchId());
        globInput.setCuti(ctxSVC.input.getCuti());

        if (ctxSVC.input.getOpenPurpose().equals(AccountEnum.AddPurpose.REGISTER.getValue())){
            globInput.setUserMaster("TRUE");
        }else{
            globInput.setUserMaster("FALSE");
        }

        log.debug("Global Input : [{}]",globInput);

        registerRepository.insertMember(globInput);

    }

    private void putOutput(CtxSVC ctxSVC) {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("ALL DATA INSERTED");

        if (StringUtils.equals(ctxSVC.input.getOpenPurpose(),AccountEnum.AddPurpose.ADD_USER.getValue())) return;

        ctxSVC.output.setQrBase64(ctxSVC.qrBase64);

    }

    private void checkExistingUser(CtxSVC ctxSVC) throws BizException{

        if (AccountEnum.AddPurpose.REGISTER.getValue().equals(ctxSVC.input.getOpenPurpose())
                && daoMemberJpa.existsByEmail(ctxSVC.input.getEmail())
                    && daoMemberJpa.existByUserMaster(ctxSVC.input.getEmail())){

            throw new BizException(SysErrCode.USER_FOUNT);

        }

    }

    private void generateTwoFactorSecret(CtxSVC ctxSVC) throws Exception {

        if (StringUtils.equals(ctxSVC.input.getOpenPurpose(),AccountEnum.AddPurpose.ADD_USER.getValue())) return;

        ctxSVC.secret = genereateSecretKey.generateSecret();
        ctxSVC.qrBase64 = genereateSecretKey.generateQrBase64(ctxSVC.input.getEmail(), ctxSVC.secret);

    }



}
