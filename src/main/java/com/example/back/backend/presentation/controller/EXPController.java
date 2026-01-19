package com.example.back.backend.presentation.controller;

import com.example.back.backend.application.dto.expense.*;
import com.example.back.backend.application.service.EXPENSE.EXP0100ASVC;
import com.example.back.backend.application.service.EXPENSE.EXP0200ASVC;
import com.example.back.backend.application.service.EXPENSE.EXP0300ASVC;
import com.example.back.backend.application.service.EXPENSE.EXP0400ASVC;
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
public class EXPController {

    private final EXP0100ASVC exp0100ASVC;

    private final EXP0200ASVC exp0200ASVC;

    private final EXP0300ASVC exp0300ASVC;

    private final EXP0400ASVC exp0400ASVC;

    @PostMapping("/insertExpense/exp0100")
    public EXP0100AOutput insertExpense(@RequestBody EXP0100AInput input) throws Exception {

        return exp0100ASVC.execute(input);

    }

    @PostMapping("/editExpense/exp0200")
    public EXP0200AOutput editExpense(@RequestBody EXP0200AInput input) throws Exception {

        return exp0200ASVC.execute(input);

    }

    @PostMapping("/deleteExpense/exp0300")
    public EXP0300AOutput deleteExpense(@RequestBody EXP0300AInput input) throws Exception {

        return exp0300ASVC.execute(input);

    }

    @PostMapping("/getAllExpense/exp0400")
    public EXP0400AOutput deleteExpense(@RequestBody EXP0400AInput input) throws Exception {

        return exp0400ASVC.execute(input);

    }

}
