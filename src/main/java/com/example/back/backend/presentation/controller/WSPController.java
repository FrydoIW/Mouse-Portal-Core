package com.example.back.backend.presentation.controller;

import com.example.back.backend.application.dto.workspace.*;
import com.example.back.backend.application.service.WORKSPACE.WSP0100ASVC;
import com.example.back.backend.application.service.WORKSPACE.WSP0200ASVC;
import com.example.back.backend.application.service.WORKSPACE.WSP0300ASVC;
import com.example.back.backend.application.service.WORKSPACE.WSP0400ASVC;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/insert/wsp0100")
    public WSP0100AOutput addNewWorkspace(@RequestBody WSP0100AInput input) throws Exception {

        return wsp0100ASVC.execute(input);

    }

    @PostMapping("/edit/wsp0200")
    public WSP0200AOutput addNewWorkspace(@RequestBody WSP0200AInput  input) throws Exception {

        return wsp0200ASVC.execute(input);

    }

    @PostMapping("/getAllData/wsp0300")
    public WSP0300AOutput addNewWorkspace(@RequestBody WSP0300AInput  input) throws Exception {

        return wsp0300ASVC.execute(input);

    }

    @PostMapping("/delete/wsp0400")
    public WSP0400AOutput addNewWorkspace(@RequestBody WSP0400AInput  input) throws Exception {

        return wsp0400ASVC.execute(input);

    }




}
