package com.kafka.kafkademo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }
}
