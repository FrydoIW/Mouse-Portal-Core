package com.example.back.backend.Exception;

import com.example.back.backend.common.Enum.SysErrCode;

public class BizException extends RuntimeException{

    private final SysErrCode sysErrCode;

    public BizException(SysErrCode sysErrCode){
        super(sysErrCode.getCode() +  " - " + sysErrCode.getDesc());
        this.sysErrCode = sysErrCode;
    }

    public SysErrCode getErrCode(){

        return sysErrCode;

    }

}
