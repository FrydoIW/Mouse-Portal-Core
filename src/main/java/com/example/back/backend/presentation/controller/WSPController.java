package com.example.back.backend.presentation.controller;

import com.example.back.backend.application.service.WORKSPACE.WSP0100ASVC;
import com.example.back.backend.application.service.WORKSPACE.WSP0200ASVC;
import com.example.back.backend.application.service.WORKSPACE.WSP0300ASVC;
import com.example.back.backend.application.service.WORKSPACE.WSP0400ASVC;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://tikusdashboard.ichmarlabs.com",
        "https://lightfootedly-booted-phebe.ngrok-free.dev"
})
public class WSPController {

    private final WSP0100ASVC wsp0100ASVC;
    private final WSP0200ASVC wsp0200ASVC;
    private final WSP0300ASVC wsp0300ASVC;
    private final WSP0400ASVC wsp0400ASVC;
}
