package com.pbp333.springbootrest.springBootRest.Controller;

import com.pbp333.springbootrest.springBootRest.configuration.BasicConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WelcomeController {

    @Autowired
    private BasicConfiguration configuration;


    @Value("${welcome.message}")
    private String welcomeMessage;

    @RequestMapping("/welcome")
    public String welcome(){
        return welcomeMessage;
    }

    @RequestMapping("/dynamic-configuration")
    public Map dynamicConfiguration(){

        Map map = new HashMap();

        map.put("message", configuration.getMessage());
        map.put("number", configuration.getNumber());
        map.put("value", configuration.isValue());
        return map;
    }
}
