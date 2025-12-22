package com.example.back.backend.presentation.controller;

import com.example.back.backend.application.dto.ATM0100AInput;
import com.example.back.backend.application.dto.ATM0100AOutput;
import com.example.back.backend.application.dto.ATM0200AInput;
import com.example.back.backend.application.dto.ATM0200AOutput;
import com.example.back.backend.application.service.ATM.ATM0100ASVC;
import com.example.back.backend.application.service.ATM.ATM0200ASVC;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://lightfootedly-booted-phebe.ngrok-free.dev"
})
public class ATMController {

    private final ATM0100ASVC atm0100ASVC;

    private final ATM0200ASVC atm0200ASVC;

    @PostMapping(
            "/addAtm/atm0100"
    )
    public ATM0100AOutput AddAtm(@RequestBody ATM0100AInput input) throws Exception {

        return atm0100ASVC.execute(input);

    }

    @PostMapping(
            "/getAllAtmData/atm0200"
    )
    public ATM0200AOutput AddAtm(@RequestBody ATM0200AInput input) throws Exception {

        return atm0200ASVC.execute(input);

    }

}

