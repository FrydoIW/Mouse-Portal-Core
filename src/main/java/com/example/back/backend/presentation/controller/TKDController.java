package com.example.back.backend.presentation.controller;

import com.example.back.backend.application.dto.*;
import com.example.back.backend.application.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TKDController {

    private final TKD0100ASVC tkd0100ASVC;

    private final TKD0200ASVC tkd0200ASVC;

    private final TKD0300ASVC tkd0300ASVC;

    private final TKD0400ASVC tkd0400ASVC;

    private final TKD0500ASVC tkd0500ASVC;

    private final TKD0600ASVC tkd0600ASVC;

    @PostMapping("/register/tkd0100")
    public TKD0100AOutput register(@RequestBody TKD0100AInput input) throws Exception {

        return tkd0100ASVC.execute(input);

    }

    @PostMapping("/login/tkd0200")
    public TKD0200AOutput login(@RequestBody TKD0200AInput input) throws Exception {

        return tkd0200ASVC.execute(input);

    }

    @PostMapping("/reset/tkd0300")
    public TKD0300AOutput reset(@RequestBody TKD0300AInput input) throws Exception {

        return tkd0300ASVC.execute(input);

    }

    @PostMapping("/getAllData/tkd0400")
    public TKD0400AOutput getAllData(@RequestBody TKD0400AInput input) throws Exception {

        return tkd0400ASVC.execute(input);

    }

    @PostMapping("/getAllData/tkd0500")
    public TKD0500AOutput updateData(@RequestBody TKD0500AInput input) throws Exception {

        return tkd0500ASVC.execute(input);

    }

    @PostMapping("/getAllData/tkd0600")
    public TKD0600AOutput deleteAccount(@RequestBody TKD0600AInput input) throws Exception {

        return tkd0600ASVC.execute(input);

    }

}
