package com.pbp333.springbootrest.springBootRest.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Value("${welcome.message}")
    private String welcomeMessage;

    @RequestMapping("/welcome")
    public String welcome(){
        return welcomeMessage;
    }
}
