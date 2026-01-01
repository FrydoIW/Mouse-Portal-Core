package com.example.back.backend.presentation.controller;

import com.example.back.backend.application.dto.admin.*;
import com.example.back.backend.application.service.ADMIN.ADM0100ASVC;
import com.example.back.backend.application.service.ADMIN.ADM0200ASVC;
import com.example.back.backend.application.service.ADMIN.ADM0300ASVC;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://lightfootedly-booted-phebe.ngrok-free.dev"
})
public class ADMController {

    private final ADM0100ASVC adm0100ASVC;
    private final ADM0200ASVC adm0200ASVC;
    private final ADM0300ASVC adm0300ASVC;

    @PostMapping("/register/adm0100")
    public ADM0100AOutput register(@RequestBody ADM0100AInput input) throws Exception {

        return adm0100ASVC.execute(input);

    }

    @PostMapping("/register2fa/adm0200")
    public ADM0200AOutput register2fa(@RequestBody ADM0200AInput input) throws Exception {

        return adm0200ASVC.execute(input);

    }

    @PostMapping("/resetPass/adm0300")
    public ADM0300AOutput resetPass(@RequestBody ADM0300AInput input) throws Exception {

        return adm0300ASVC.execute(input);

    }

}
