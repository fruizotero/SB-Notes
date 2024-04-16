package com.fruizotero.springjpahibernate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocsController {

    @GetMapping("/docs")
    public String home() {
        return "redirect:/docs/index.html";
    }

}
