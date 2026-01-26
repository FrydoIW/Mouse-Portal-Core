package com.example.back.backend.application.service.WORKSPACE;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.workspace.WSP0300AInput;
import com.example.back.backend.application.dto.workspace.WSP0300AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.infrastructure.entity.Admin;
import com.example.back.backend.infrastructure.jpa.DaoAdminJpa;
import com.example.back.backend.infrastructure.persistence.GetAllUserDataJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @fileName : WSP0300ASVC
 * @author   : dodocool
 * @description : Get all related workspace account 🗿
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class WSP0300ASVC {

    private final DaoAdminJpa adminJpa;
    private final GetAllUserDataJpa getAllUserDataJpa;

    private static class CtxSVC {
        Admin admin;
        WSP0300AInput input;
        WSP0300AOutput output;

    }

    public WSP0300AOutput execute(WSP0300AInput input) throws Exception{

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new WSP0300AOutput();

        checkInput(ctxSVC);
        getAllWorkspaceData(ctxSVC);

        return ctxSVC.output;

    }

    private void checkInput(CtxSVC ctxSVC) throws BizException {

        ctxSVC.admin = adminJpa.findAdminByEmailVerified(ctxSVC.input.getAdminEmail());

        if (ctxSVC.admin == null){
            throw new BizException(SysErrCode.ACCOUNT_NOT_FOUND);
        }

    }

    public void getAllWorkspaceData(CtxSVC ctxSVC){

        List<HashMap<String,Object>> inquiryResult;

        inquiryResult = getAllUserDataJpa.getAllWorkspaceInformation(ctxSVC.admin.getEmail());

        ctxSVC.output.setResultList(inquiryResult);

    }

}
