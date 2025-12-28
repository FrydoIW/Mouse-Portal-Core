package com.example.back.backend.presentation.controller;

import com.example.back.backend.application.dto.branch.BRO0100AInput;
import com.example.back.backend.application.dto.branch.BRO0100AOutput;
import com.example.back.backend.application.dto.branch.BRO0200AInput;
import com.example.back.backend.application.dto.branch.BRO0200AOutput;
import com.example.back.backend.application.service.BRANCH.BRO0100ASVC;
import com.example.back.backend.application.service.BRANCH.BRO0200ASVC;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://lightfootedly-booted-phebe.ngrok-free.dev"
})
public class BROController {

    private final BRO0100ASVC bro0100ASVC;
    private final BRO0200ASVC bro0200ASVC;

    @PostMapping(
            "/insertBranch/bro0100"
    )
    public BRO0100AOutput insertBranch(@RequestBody BRO0100AInput input) throws Exception {

        return bro0100ASVC.execute(input);

    }

    @PostMapping(
            "/editBranch/bro0200"
    )
    public BRO0200AOutput editBranch(@RequestBody BRO0200AInput input) throws Exception {

        return bro0200ASVC.execute(input);

    }



}
