package com.example.todolistproject.controller.v1;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin
public class NurController {

    @GetMapping("")
    public String index() {
        return "Являетесь ли вы клиентом М-Банк?";
    }
}
