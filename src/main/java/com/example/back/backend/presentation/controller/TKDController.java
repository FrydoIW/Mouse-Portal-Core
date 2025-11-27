package com.example.back.backend.presentation.controller;

import com.example.back.backend.application.dto.*;
import com.example.back.backend.application.service.TKD0100ASVC;
import com.example.back.backend.application.service.TKD0200ASVC;
import com.example.back.backend.application.service.TKD0300ASVC;
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

    @PostMapping("/register/tkd0100")
    public TKD0100AOutput output(@RequestBody TKD0100AInput input) throws Exception {

        return tkd0100ASVC.execute(input);

    }

    @PostMapping("/login/tkd0100")
    public TKD0200AOutput output(@RequestBody TKD0200AInput input) throws Exception {

        return tkd0200ASVC.execute(input);

    }

    @PostMapping("/reset/tkd0100")
    public TKD0300AOutput output(@RequestBody TKD0300AInput input) throws Exception {

        return tkd0300ASVC.execute(input);

    }

}
