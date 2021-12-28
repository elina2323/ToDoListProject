package com.example.todolistproject.controller.v1;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin
public class NurController {

    @PostMapping("")
    public String index() {
        return "Являетесь ли вы клиентом М-Банк?";
    }
}
