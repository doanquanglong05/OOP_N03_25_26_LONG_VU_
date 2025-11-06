package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
  @GetMapping("/greeting")
  public String greeting(@RequestParam(defaultValue="Sinh viên") String name, Model model){
    model.addAttribute("name", name);
    model.addAttribute("message", "Trang chào riêng");
    return "index"; // hoặc "greeting" nếu bạn có greeting.html
  }
}
