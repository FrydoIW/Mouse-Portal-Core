package com.example.back.backend.common.util;

import com.example.back.backend.domain.model.MemberInfoModel;
import com.example.back.backend.infrastructure.entity.MemberInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfoMapper {

    public static MemberInfo toMemberInfoEntity(MemberInfoModel m) {

        MemberInfo memberInfo = new MemberInfo();

        memberInfo.setPosition(m.getPosition());
        memberInfo.setStatus(m.getStatus());
        memberInfo.setRefNo(m.getRefNo());

        return memberInfo;
    }

}
