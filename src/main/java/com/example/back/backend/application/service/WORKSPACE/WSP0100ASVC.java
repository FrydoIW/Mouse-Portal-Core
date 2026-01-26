package com.example.back.backend.application.service.WORKSPACE;

import com.example.back.backend.application.dto.workspace.WSP0100AInput;
import com.example.back.backend.application.dto.workspace.WSP0100AOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : WSP0100ASVC
 * @author   : dodocool
 * @description : Invite workspace / Invite workspace account 🗿
 */


@Slf4j
@RequiredArgsConstructor
@Service
public class WSP0100ASVC {

    public static class CtxSVC {
        WSP0100AInput input;
        WSP0100AOutput output;
    }

    public WSP0100AOutput execute(WSP0100AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new WSP0100AOutput();

        return ctxSVC.output;

    }

}