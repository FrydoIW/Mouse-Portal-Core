package com.example.back.backend.application.service.WORKSPACE;

import com.example.back.backend.application.dto.workspace.WSP0400AInput;
import com.example.back.backend.application.dto.workspace.WSP0400AOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class WSP0400ASVC {

    private static class CtxSVC {

        WSP0400AInput input;
        WSP0400AOutput output;

    }

    public WSP0400AOutput execute(WSP0400AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new WSP0400AOutput();

        return ctxSVC.output;
    }

}
