package com.xivs.weblab44.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping(value = "/home")
    public String index() {
        return "index";
    }
    @RequestMapping(value = "/shoot")
    public String shot() {
        return "shoot";
    }
}