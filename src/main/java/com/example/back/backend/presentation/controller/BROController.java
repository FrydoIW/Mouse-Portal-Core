package com.example.back.backend.presentation.controller;

import com.example.back.backend.application.dto.branch.*;
import com.example.back.backend.application.service.BRANCH.BRO0100ASVC;
import com.example.back.backend.application.service.BRANCH.BRO0200ASVC;
import com.example.back.backend.application.service.BRANCH.BRO0300ASVC;
import com.example.back.backend.application.service.BRANCH.BRO0400ASVC;
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
    private final BRO0300ASVC bro0300ASVC;
    private final BRO0400ASVC bro0400ASVC;

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

    @PostMapping(
            "/deleteBranch/bro0300"
    )
    public BRO0300AOutput deleteBranch(@RequestBody BRO0300AInput input) throws Exception {

        return bro0300ASVC.execute(input);

    }

    @PostMapping(
            "/getAllBranch/bro0400"
    )
    public BRO0400AOutput getAllBranch(@RequestBody BRO0400AInput input) throws Exception {

        return bro0400ASVC.execute(input);

    }



}
