package com.example.back.backend.presentation.controller;

import com.example.back.backend.application.dto.admin.ADM0100AInput;
import com.example.back.backend.application.dto.admin.ADM0100AOutput;
import com.example.back.backend.application.service.ADMIN.ADM0100ASVC;
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

    @PostMapping("/register/adm0100asvc")
    public ADM0100AOutput register(@RequestBody ADM0100AInput input) throws Exception {

        return adm0100ASVC.execute(input);

    }

}
