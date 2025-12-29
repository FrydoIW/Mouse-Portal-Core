package com.example.back.backend.application.service.BRANCH;

import com.example.back.backend.application.dto.branch.BRO0400AInput;
import com.example.back.backend.application.dto.branch.BRO0400AOutput;
import com.example.back.backend.infrastructure.entity.Branch;
import com.example.back.backend.infrastructure.jpa.DaoBranchJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @fileName : BRO0100ASVC
 * @author   : dodocool
 * @description : GetAll Branch / GetAll 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BRO0400ASVC {

    private final DaoBranchJpa branchJpa;

    public static class CtxSVC {
        BRO0400AInput input;
        BRO0400AOutput output;

    }

    public BRO0400AOutput execute(BRO0400AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new BRO0400AOutput();

        getAllData(ctxSVC);

        return ctxSVC.output;

    }

    private void getAllData(CtxSVC ctxSVC) {

        List<Branch> branch;
        List<HashMap<String,Object>> outputMap = new ArrayList<>();

        branch = branchJpa.findAll();

        for(Branch b : branch){

            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("branchId",b.getBranchId());
            hashMap.put("branchName",b.getBranchName());

            outputMap.add(hashMap);

        }

        ctxSVC.output.setResultList(outputMap);

    }

}
