package com.example.back.backend.infrastructure.adapter;

import com.example.back.backend.common.util.BranchMapper;
import com.example.back.backend.domain.model.BranchModel;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.BranchRepository;
import com.example.back.backend.infrastructure.entity.Branch;
import com.example.back.backend.infrastructure.jpa.DaoBranchJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@RequiredArgsConstructor
@Repository
@Slf4j
public class BranchAdapter implements BranchRepository {

    private final DaoBranchJpa branchJpa;

    @Override
    public void insertBranchInfo(GlobalModel globalModel) {

        BranchModel branchModel = new BranchModel();

        branchModel.setBranchName(globalModel.getBranchName());

        Branch branch = BranchMapper.toBranchEntity(branchModel);

        branch.setRegDt(LocalDate.now());
        branch.setUpdDt(LocalDate.now());

        log.debug("Register Branch : [{}]",branch);

        branchJpa.save(branch);

    }

    @Override
    public void editBranch(GlobalModel globalModel) throws Exception {

        Branch branch = branchJpa.findById(globalModel.getBranchId()).orElseThrow(() -> new Exception("Data Not Found"));
        branch.setBranchName(globalModel.getBranchName());
        branch.setUpdDt(LocalDate.now());

        log.debug("Edit Branch : [{}]",branch);

        branchJpa.save(branch);

    }
}
