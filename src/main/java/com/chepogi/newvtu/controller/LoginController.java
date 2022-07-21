package com.chepogi.newvtu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/loging")
@Controller
public class LoginController {

    @GetMapping("login")
    public String showLoginPage(){
        return "login";
    }
}
