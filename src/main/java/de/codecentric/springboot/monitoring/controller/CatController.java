package de.codecentric.springboot.monitoring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatController {

    @RequestMapping("/cat")
    public String cat() {
        return "Miau";
    }
}
