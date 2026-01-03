package com.example.back.backend.common.util;

import com.example.back.backend.domain.model.MemberCredentialModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialMapper {

    public static MemberCredential toMemberCredentialEntity(MemberCredentialModel m) {

        MemberCredential memberCredential = new MemberCredential();

        memberCredential.setPasswordHash(m.getPasswordHash());
        memberCredential.setTwoFactorSecret(m.getTwoFactorSecret());
        memberCredential.setStatus(m.getStatus());
        memberCredential.setRole(m.getRole());
        memberCredential.setRefNo(m.getRefNo());


        return memberCredential;

    }

}
