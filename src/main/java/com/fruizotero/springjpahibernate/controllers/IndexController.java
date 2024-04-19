package com.fruizotero.springjpahibernate.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String home() {
        return "redirect:/index/index.html";
    }
}
