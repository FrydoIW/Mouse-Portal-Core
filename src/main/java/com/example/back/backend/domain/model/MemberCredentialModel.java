package com.example.back.backend.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberCredentialModel {

    private String passwordHash;
    private String twoFactorSecret;
    private String role;
    private String status;
    private String refNo;

}
