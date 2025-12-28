package com.example.back.backend.common.util;

import com.example.back.backend.domain.model.BranchModel;
import com.example.back.backend.infrastructure.entity.Branch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchMapper {

    public static Branch toBranchEntity (BranchModel branchModel) {

        Branch branch = new Branch();

        branch.setBranchName(branchModel.getBranchName());

        return branch;


    }

}
