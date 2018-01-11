package com.pbp333.springbootrest.springBootRest.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @RequestMapping("/welcome")
    public String welcome(){
        return "Welcome";
    }
}
