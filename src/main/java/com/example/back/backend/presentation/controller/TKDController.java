package com.example.back.backend.presentation.controller;

import com.example.back.backend.application.dto.TKD0100AInput;
import com.example.back.backend.application.dto.TKD0100AOutput;
import com.example.back.backend.application.service.TKD0100ASVC;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tikus")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TKDController {

    private final TKD0100ASVC tkd0100ASVC;

    @PostMapping("/tkd0100")
    public TKD0100AOutput output(@RequestBody TKD0100AInput input) throws Exception {

        return tkd0100ASVC.execute(input);

    }

}
