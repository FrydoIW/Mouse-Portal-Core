package com.example.back.backend.presentation.controller;

import com.example.back.backend.application.dto.email.EMA0100AInput;
import com.example.back.backend.application.dto.email.EMA0100AOutput;
import com.example.back.backend.application.dto.email.EMA0200AInput;
import com.example.back.backend.application.dto.email.EMA0200AOutput;
import com.example.back.backend.application.service.EMAIL.EMA0100ASVC;
import com.example.back.backend.application.service.EMAIL.EMA0200ASVC;
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

}
