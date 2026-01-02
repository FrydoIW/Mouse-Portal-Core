package com.example.back.backend.presentation.controller;

import com.example.back.backend.application.dto.email.*;
import com.example.back.backend.application.service.EMAIL.EMA0100ASVC;
import com.example.back.backend.application.service.EMAIL.EMA0200ASVC;
import com.example.back.backend.application.service.EMAIL.EMA0300ASVC;
import com.example.back.backend.application.service.EMAIL.EMA0400ASVC;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://lightfootedly-booted-phebe.ngrok-free.dev"
})
public class EMAController {

    private final EMA0100ASVC ema0100ASVC;
    private final EMA0200ASVC ema0200ASVC;
    private final EMA0300ASVC ema0300ASVC;
    private final EMA0400ASVC ema0400ASVC;

    @PostMapping("/sendMail/ema0100")
    public EMA0100AOutput sendMail(@RequestBody EMA0100AInput input) throws Exception {

        return ema0100ASVC.execute(input);

    }

    @GetMapping("/verifyMail/ema0200")
    public EMA0200AOutput verifyEmail(@RequestParam("token") String token) throws Exception {

        EMA0200AInput input = new EMA0200AInput();
        input.setToken(token);

        return ema0200ASVC.execute(input);

    }

    @PostMapping("/editMail/ema0300")
    public EMA0300AOutput editMail(@RequestBody EMA0300AInput input) throws Exception {

        return ema0300ASVC.execute(input);

    }

    @PostMapping("/emailVerified/ema0400")
    public EMA0400AOutput emailVerified(@RequestBody EMA0400AInput input) throws Exception {

        return ema0400ASVC.execute(input);

    }


}
