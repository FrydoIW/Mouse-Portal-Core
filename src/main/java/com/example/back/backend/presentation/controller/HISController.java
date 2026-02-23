package com.example.back.backend.presentation.controller;

import com.example.back.backend.application.dto.history.HIS0100AInput;
import com.example.back.backend.application.dto.history.HIS0100AOutput;
import com.example.back.backend.application.service.HISTORY.HIS0100ASVC;
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
public class HISController {

    private final HIS0100ASVC his0100ASVC;

    @PostMapping("/getHistory/his0100")
    public HIS0100AOutput getHistory(@RequestBody HIS0100AInput input) throws Exception {

        return his0100ASVC.execute(input);

    }

}
