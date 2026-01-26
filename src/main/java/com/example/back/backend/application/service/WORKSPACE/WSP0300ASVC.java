package com.example.back.backend.application.service.WORKSPACE;

import com.example.back.backend.application.dto.workspace.WSP0300AInput;
import com.example.back.backend.application.dto.workspace.WSP0300AOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : WSP0300ASVC
 * @author   : dodocool
 * @description : Get all related workspace account 🗿
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class WSP0300ASVC {

    private static class CtxSVC {

        WSP0300AInput input;
        WSP0300AOutput output;

    }

    public WSP0300AOutput execute(WSP0300AInput input) throws Exception{

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new WSP0300AOutput();

        return ctxSVC.output;

    }

}
