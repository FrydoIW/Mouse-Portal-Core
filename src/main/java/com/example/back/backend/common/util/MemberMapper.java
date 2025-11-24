package com.example.back.backend.common.util;

import com.example.back.backend.domain.model.MemberModel;
import com.example.back.backend.infrastructure.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberMapper {

    public static Member toMemberEntity(MemberModel m) {

        Member member = new Member();

        member.setRefNo(m.getRefNo());
        member.setName(m.getName());
        member.setAddress(m.getAddress());
        member.setBirthDate(m.getBirthDate());
        member.setSex(m.getGender());
        member.setEmail(m.getEmail());

        return member;
    }


}

