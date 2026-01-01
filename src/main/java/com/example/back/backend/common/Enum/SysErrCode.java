package com.example.back.backend.common.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SysErrCode {

    USER_FOUNT("0001","Admin already have an account please go to Forget password"),
    PASSWORD_INVALID("0002","Your password is incorrect"),
    EMAIL_NOT_FOUNT("0003","Your email is wrong"),
    INVALID_OTP("0004","Your OTP input is wrong"),
    ATM_ID_NULL("0005","Input ID for edit atm may not be null"),
    EXPENSE_ID_NULL("0006","Input ID for edit may not be null"),
    DEL_ID_NULL("0007","Input ID for delete may not be null"),
    EDIT_ID_NULL("0008","Edit ID should not be null"),
    TOKEN_NOT_FOUND("0009","Verification token Should not be null"),
    EMAIL_INPUT_NULL("0010","Input email may not be null"),
    ACCOUNT_NOT_FOUND("0011","Account inquiry not found");

    private final String code;
    private final String desc;

}
