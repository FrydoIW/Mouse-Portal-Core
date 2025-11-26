package com.example.back.backend.Exception;

import com.example.back.backend.common.Enum.SysErrCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(BizException ex){

        SysErrCode sysErrCode = ex.getErrCode();

        Map<String,Object> body = new HashMap<>();
        body.put("Code", sysErrCode.getCode());
        body.put("Message", sysErrCode.getDesc());

        return ResponseEntity.badRequest().body(body);

    }

}
