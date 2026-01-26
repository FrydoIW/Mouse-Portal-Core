package com.example.back.backend.application.service.WORKSPACE;

import com.example.back.backend.application.dto.workspace.WSP0200AInput;
import com.example.back.backend.application.dto.workspace.WSP0200AOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : WSP0200ASVC
 * @author   : dodocool
 * @description : Edit workspace / Edit workspace account 🗿
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class WSP0200ASVC {

    public static class CtxSVC {
        WSP0200AInput input;
        WSP0200AOutput output;
    }

    public WSP0200AOutput execute(WSP0200AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new WSP0200AOutput();


        return ctxSVC.output;
    }

}
