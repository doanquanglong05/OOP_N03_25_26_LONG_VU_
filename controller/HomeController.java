package com.quanly.quanly_dangky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/", "/home"})
    public String index() {
        return "index"; // trả về tên file template: index.html
    }
}
