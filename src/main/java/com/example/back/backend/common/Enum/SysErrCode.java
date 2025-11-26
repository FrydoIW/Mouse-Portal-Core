package com.example.back.backend.common.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SysErrCode {

    USER_FOUNT("0001","User is existing in database"),
    PASSWORD_INVALID("0002","Your password is incorrect"),
    EMAIL_NOT_FOUNT("0003","Your email is wrong");

    private final String code;
    private final String desc;

}
